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

        String jsonString = params.get("submission")
//        JSONObject submissionJson = new JSONObject("{\"answers\":[{\"answer\":\"My text answer.\",\"answerFormat\":2,\"questionId\":51},{\"choiceId\":56,\"answerFormat\":0,\"questionId\":30},{\"choiceIds\":[31,32,33],\"answerFormat\":1,\"questionId\":43}],\"questionnaireId\":29}")
        JSONObject submissionJson = new JSONObject(jsonString)
        Submission submission = new Submission(created: new Date())
        Questionnaire questionnaire = Questionnaire.findById(submissionJson.getInt("questionnaireId"))
        submission.setPatient(p)
        submission.setQuestionnaire(questionnaire)
        submission.save(flush: true)

        Choice choice
        Question question
        JSONArray answersJson = submissionJson.getJSONArray("answers")
        JSONObject answer
        int answerFormat
        for (int i = 0; i < answersJson.length(); i++) {
            answer = answersJson.getJSONObject(i)
            answerFormat = answer.getInt("answerFormat")
            question = Question.findById(answer.getInt("questionId"))

            switch(answerFormat) {
                case ANSWER_FORMAT_RADIOBUTTON:
                    choice = Choice.findById(answer.getInt("choiceId"))

                    SingleSelectionAnswer singleSelectionAnswer = new SingleSelectionAnswer(choice: choice, question: question, submission: submission)
                    singleSelectionAnswer.save(flush: true)
                    submission.addToSingleSelectionAnswers(singleSelectionAnswer)
                    break;
                case ANSWER_FORMAT_CHECKBOX:
                    MultipleSelectionAnswer multipleSelectionAnswer = new MultipleSelectionAnswer(submission: submission,  question: question)
                    JSONArray choiceIds = answer.getJSONArray("choiceIds")

                    for (int j = 0; j < choiceIds.length(); j++) {
                        multipleSelectionAnswer.addToChoice(Choice.findById(choiceIds.getInt(j)))
                    }
                    multipleSelectionAnswer.save(flush: true)
                    submission.addToMultipleSelectionAnswers(multipleSelectionAnswer)
                    break;
                case ANSWER_FORMAT_TEXT:
                    String answerText = answer.getString("answer")

                    TextAnswer textAnswer = new TextAnswer(submission: submission, answer: answerText, question: question)
                    textAnswer.save(flush: true)
                    submission.addToTextAnswers(textAnswer)
            }
        }
        submission.save(flush: true)

        p.addToSubmissions(submission)
        p.save(flush: true)

        render "Submission created."
    }
}
