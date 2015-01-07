package connectedhealth

import org.json.JSONArray
import org.json.JSONObject

class QuestionnaireController {

    def scaffold = Questionnaire

    // Respond with JSON object containing questionnaire name/id, questions and question choices (if applicable).
    // {id: int, name: str, questions: [ {id: int, content: str, answerFormat: int, choices: [ {id: int, content: str}, {id: int, content: str}, ... ] } ] }
    def get() {
        if (params.IDorName == null) {
            render "Questionnaire id or name is required"
            return
        }

        // find questionnaire by ID or name
        int questionnaireId;
        try {
            questionnaireId = Integer.parseInt(params.IDorName)
        } catch (NumberFormatException e) {
            if ((Questionnaire.findByName(params.IDorName)) == null) {
                render "Questionnaire ID not found."
                return
            } else {
                questionnaireId = Questionnaire.findByName(params.IDorName).getId()
            }
        }

        // find all questions that belong to the questionnaire
        Questionnaire currentQuestionnaire = Questionnaire.findById(questionnaireId)
        Question[] questions = currentQuestionnaire.getQuestions()

        // add json key/value pairs to describe questionnaire
        JSONObject questionnaireObject = new JSONObject()
        questionnaireObject.put("id", questionnaireId)
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

        // add JSON array of questions to questionnaire JSON object
        questionnaireObject.put("questions", questionsToSend)

        render questionnaireObject.toString()
    }
}
