package connectedhealth

import org.json.JSONArray
import org.json.JSONObject

class QuestionnaireController {

    def scaffold = Questionnaire

    def indexView() {
        Set<Questionnaire> questionnaires = Questionnaire.list(sort:'name')
        render(view: "/questionnaire/indexView", model:
                [questionnaires: questionnaires])
    }

    def showView() {
        Questionnaire questionnaire = Questionnaire.findById(params.questionnaireID)
        render(view: "/questionnaire/showView", model:
                [questionnaire: questionnaire])
    }

    def newQuestionnaire() {
        render(view: "/questionnaire/newView")
    }

    def createQuestionnaire() {
        Questionnaire questionnaire = new Questionnaire(name: params.name, description: params.description)
        questionnaire.save(true)
        redirect(uri:"/questionnaires/${questionnaire.id}/show")
    }

    def editView() {
        Questionnaire questionnaire = Questionnaire.findById(params.questionnaireID)
        render(view: "/questionnaire/editView", model:
                [questionnaire: questionnaire])
    }

    def updateQuestionnaire() {
        Questionnaire questionnaire = Questionnaire.findById(params.questionnaireID)
        questionnaire.name = params.name
        questionnaire.description = params.description
        questionnaire.save(true)
        redirect(uri:"/questionnaires/${questionnaire.id}/show")
    }

    def deleteQuestionnaire() {
    }

    // TODO make a private Questionnaire -> JSON function

    def questionnaires() {
        Set<Questionnaire> questionnaires = Questionnaire.list(sort: 'name')
        ArrayList<JSONObject> questionnaireObjects = new ArrayList<JSONObject>()

        questionnaires.each {
            Question[] questions = it.questions

            // add json key/value pairs to describe questionnaire
            JSONObject questionnaireObject = new JSONObject()
            questionnaireObject.put("id", it.id)
            questionnaireObject.put("name", it.name)

            // make a JSON object for each question
            ArrayList<JSONObject> questionsToSend = new ArrayList<JSONObject>()
            for (int i = 0; i < questions.size(); i++) {
                JSONObject questionObject = new JSONObject()

                questionObject.put("id", questions[i].getId())
                questionObject.put("content", questions[i].getContent())
                questionObject.put("answerFormat", questions[i].getAnswerFormat())

                // get all choices for the current question
                Choice[] choices = questions[i].getChoices()

                // make a JSON object for each choice
                if (choices != null) {      // question has at least 1 choice
                    ArrayList<JSONObject> choicesToSend = new ArrayList<JSONObject>()
                    for (int j = 0; j < choices.size(); j++) {
                        JSONObject choiceObject = new JSONObject()
                        choiceObject.put("id", choices[j].getId())
                        choiceObject.put("content", choices[j].getContent())
                        choicesToSend.add(choiceObject)
                    }

                    // add JSON array of choices to question JSON object
                    JSONArray choicesArray = new JSONArray(choicesToSend)
                    questionObject.put("choices", choicesArray)
                    questionsToSend.add(questionObject)
                }
            }

            // add JSON array of questions to questionnaire JSON object
            questionnaireObject.put("questions", questionsToSend)
            questionnaireObjects.add(questionnaireObject)
        }

        JSONArray questionnairesJson = new JSONArray(questionnaireObjects)
        render questionnairesJson.toString()
    }

    // Respond with JSON object containing questionnaire name/id, questions and question choices (if applicable).
    // {id: int, name: str, questions: [ {id: int, content: str, answerFormat: int, choices: [ {id: int, content: str}, {id: int, content: str}, ... ] } ] }
    def get() {
        if (params.questionnaireID == null) {
            response.status = 404
            render "Questionnaire ID is required"
            return
        }

        // find questionnaire by ID
        if ((Questionnaire.findById(params.questionnaireID)) == null) {
            response.status = 404
            render "Questionnaire ID does not exist"
            return
        }

        // find all questions that belong to the questionnaire
        Questionnaire currentQuestionnaire = Questionnaire.findById(params.questionnaireID)
        Question[] questions = currentQuestionnaire.getQuestions()

        // add json key/value pairs to describe questionnaire
        JSONObject questionnaireObject = new JSONObject()
        questionnaireObject.put("id", currentQuestionnaire.getId())
        questionnaireObject.put("name", currentQuestionnaire.getName())

        // make a JSON object for each question
        ArrayList<JSONObject> questionsToSend = new ArrayList<JSONObject>()
        for (int i = 0; i < questions.size(); i++) {
            JSONObject questionObject = new JSONObject()

            questionObject.put("id", questions[i].getId())
            questionObject.put("content", questions[i].getContent())
            questionObject.put("answerFormat", questions[i].getAnswerFormat())

            // get all choices for the current question
            Choice[] choices = questions[i].getChoices()

            // make a JSON object for each choice
            if (choices != null) {      // question has at least 1 choice
                ArrayList<JSONObject> choicesToSend = new ArrayList<JSONObject>()
                for (int j = 0; j < choices.size(); j++) {
                    JSONObject choiceObject = new JSONObject()
                    choiceObject.put("id", choices[j].getId())
                    choiceObject.put("content", choices[j].getContent())
                    choicesToSend.add(choiceObject)
                }

                // add JSON array of choices to question JSON object
                JSONArray choicesArray = new JSONArray(choicesToSend)
                questionObject.put("choices", choicesArray)
                questionsToSend.add(questionObject)
            }
        }

        // add JSON array of questions to questionnaire JSON object
        questionnaireObject.put("questions", questionsToSend)

        render questionnaireObject.toString()
    }
}
