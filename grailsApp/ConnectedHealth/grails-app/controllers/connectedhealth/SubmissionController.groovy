package connectedhealth

import org.json.JSONArray
import org.json.JSONObject

class SubmissionController {

    final int ANSWER_FORMAT_RADIOBUTTON = 0;
    final int ANSWER_FORMAT_CHECKBOX = 1;
    final int ANSWER_FORMAT_TEXT = 2;

    def scaffold = Submission

    def createSubmission() {
        if (params.patientID == null) {
            response.status = 404
            render "Patient ID is required"
            return
        }

        Patient p = Patient.findById(params.patientID)
        if (p == null) {
            response.status = 404
            render "Patient with this ID does not exist"
            return
        }

        println(params)

        String jsonString = params.get("submission")
        JSONObject submissionJson = new JSONObject(jsonString)
        Questionnaire questionnaire = Questionnaire.findById(submissionJson.getInt("questionnaireId"))
        Submission submission = new Submission(created: new Date(), patient: p, questionnaire: questionnaire)
        submission.save(flush:true)

        Choice choice
        Question question
        JSONArray answersJson = submissionJson.getJSONArray("answers")
        JSONObject answer
        int answerFormat
        for (int i = 0; i < answersJson.length(); i++) {
            answer = answersJson.getJSONObject(i)
            answerFormat = answer.getInt("answerFormat")

            switch(answerFormat) {
                case ANSWER_FORMAT_RADIOBUTTON:
                    choice = Choice.findById(answer.getInt("choiceId"))
                    question = choice.question

                    SingleSelectionAnswer singleSelectionAnswer = new SingleSelectionAnswer(submission: submission, choice: choice, question: question)
                    singleSelectionAnswer.save(flush: true)
                    break;
                case ANSWER_FORMAT_CHECKBOX:
                    JSONArray choiceIds = answer.getJSONArray("choiceIds")
                    ArrayList<Choice> choices = new ArrayList<Choice>()
                    for (int j = 0; j < choiceIds.length(); j++) {
                        choices.add( Choice.findById(choiceIds.getInt(j)) )
                    }

                    // TODO what if no choices? ADD QUESTION ID!!!
                    MultipleSelectionAnswer multipleSelectionAnswer = new MultipleSelectionAnswer(submission: submission, choices: choices, question: choices.get(0).question)
                    multipleSelectionAnswer.save(flush: true)
                    break;
                case ANSWER_FORMAT_TEXT:
                    // TODO get question ID here!
                    String answerText = Choice.findById(answer.getString("answer"))
                    Question tempQuestion = Question.first()

                    TextAnswer textAnswer = new TextAnswer(submission: submission, answer: answerText, question: tempQuestion)
                    textAnswer.save(flush: true)
            }
        }
    }
}
