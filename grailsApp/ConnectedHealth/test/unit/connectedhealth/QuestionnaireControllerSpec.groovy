package connectedhealth

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.web.json.JSONException
import org.json.JSONArray
import org.json.JSONObject
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(QuestionnaireController)
@Mock([Questionnaire, Question, Choice])
class QuestionnaireControllerSpec extends Specification {

    static Questionnaire questionnaire1
    static Questionnaire questionnaire2

    def setup() {
        questionnaire1 = new Questionnaire(name: "FirstQuestionnaire", description: "qq1desc")
        questionnaire1.save()

        Question q1 = new Question(content: "q1", answerFormat: 0, questionnaire: questionnaire1)
        q1.save()
        Question q2 = new Question(content: "q2", answerFormat: 1, questionnaire: questionnaire1)
        q2.save()
        Question q3 = new Question(content: "q3", answerFormat: 2, questionnaire: questionnaire1)
        q3.save()

        Choice q1c1 = new Choice(content: "choice1 for q1", question: q1)
        q1c1.save()
        Choice q1c2 = new Choice(content: "choice2 for q1", question: q1)
        q1c2.save()
        Choice q1c3 = new Choice(content: "choice3 for q1", question: q1)
        q1c3.save()

        Choice q2c1 = new Choice(content: "choice1 for q2", question: q2)
        q2c1.save()
        Choice q2c2 = new Choice(content: "choice2 for q2", question: q2)
        q2c2.save()
        Choice q2c3 = new Choice(content: "choice3 for q2", question: q2)
        q2c3.save()

        questionnaire2 = new Questionnaire(name: "SecondQuestoinnaire", description: "qq2desc")
        questionnaire2.save()

        Question q4 = new Question(content: "q4", answerFormat: 1, questionnaire: questionnaire2)
        q4.save()

        for (int i = 1; i <= 10; i++) {
            Choice q4choice = new Choice(content: "choice" + i + " for q4", question: q4);
            q4choice.save();
        }

        Question q5 = new Question(content: "q5", answerFormat: 0, questionnaire: questionnaire2)
        q5.save()

        for (int i = 1; i <= 5; i++) {
            Choice q5choice = new Choice(content: "choice" + i + " for q5", question: q5);
            q5choice.save();
        }
    }

    def cleanup() {
    }

    void "missing questionnaire ID"() {
        when:
        //      controller.params.questionnaireID = 1
        controller.get()

        then:
        response.status == 404
        response.text == "Questionnaire ID is required"
    }

    void "valid questionnaire ID"() {
        when:
        controller.params.questionnaireID = (int) questionnaire1.getId()
        controller.get()

        then:
        response.status == 200
        response.text != "Questionnaire ID is required"
    }

    void "valid questionnaire ID that doesn't exist"() {
        when:
        controller.params.questionnaireID = 999999
        controller.get()

        then:
        response.status == 404
        response.text == "Questionnaire ID does not exist"
    }

    void "multiple requests for questionnaire, parse as JSON object"() {
        when:
        controller.params.questionnaireID = (int) questionnaire2.getId()
        controller.get()

        then:
        response.status == 200
        response.text != "Questionnaire ID is required"

        try {
            JSONObject o1 = new JSONObject(response.text)
            JSONObject o2 = new JSONObject(response.text)
            JSONObject o3 = new JSONObject(response.text)
        } catch (JSONException je) {
            fail("Couldn't parse medical notes JSON array")
        }
    }

    void "get Questionnaire/Question/Choice object as JSON, parse and extract values"() {
        when:
        controller.params.questionnaireID = (int) questionnaire1.getId()
        controller.get()

        then:
        response.status == 200
        response.text != "Questionnaire ID is required"

        try {
            JSONObject questionnaire = new JSONObject(response.text)

            questionnaire.getString("name") == questionnaire1.getName()
            questionnaire.getInt("id") == (int) questionnaire1.getId()

            JSONArray questions = questionnaire.getJSONArray("questions")

            // parse each question as a JSON object, and parse their choices as JSON objects
            for (int i = 0; i < questions.length(); i++) {
                JSONObject q = questions.get(i)
                JSONArray choices = q.getJSONArray("choices")
                for (int j = 0; j < choices.length(); j++) {
                    JSONObject c = choices.getJSONObject(j)
                }
            }
        } catch (JSONException je) {
            fail("Couldn't parse questionnaire JSON object")
        }
    }

    void "get Questions from Questionnaire JSON object"() {
        when:
        controller.params.questionnaireID = (int) questionnaire1.getId()
        controller.get()

        then:
        response.status == 200
        response.text != "Questionnaire ID is required"

        // set of questions that belong to this questionnaire
        HashSet<Question> questionSet = questionnaire1.getQuestions()
        HashSet<Integer> questionIdSet = new HashSet<Integer>()
        Iterator<Question> iq = questionSet.iterator()
        while (iq.hasNext()) {
            Question q = iq.next()
            questionIdSet.add(new Integer(q.getId().intValue()))
        }

        try {
            JSONObject qq = new JSONObject(response.text)
            JSONArray questions = qq.getJSONArray("questions")

            // set of question IDs that belong to the JSON version of this questionnaire
            HashSet<Integer> jsonQuestionIdSet = new HashSet<Integer>()
            for (int i = 0; i < questions.length(); i++) {
                JSONObject question = questions.get(i)
                jsonQuestionIdSet.add(new Integer(question.getInt("id")))
            }

            // check that the question IDs are the same
            questionIdSet.contains(jsonQuestionIdSet)
            jsonQuestionIdSet.contains(questionIdSet)
        } catch (JSONException je) {
            fail("Couldn't parse questionnaire JSON object")
        }
    }
}
