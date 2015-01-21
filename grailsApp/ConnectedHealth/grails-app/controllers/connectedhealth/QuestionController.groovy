package connectedhealth

class QuestionController {

    def scaffold = Question

    def showView() {
        Questionnaire questionnaire = Questionnaire.findById(params.questionnaireID)
        Question question = Question.findById(params.questionID)
        render(view: "/questions/show", model: [questionnaire: questionnaire, question: question])
    }

    def newQuestion() {}

    def createQuestion() {
    }

    def editView() {
        Questionnaire questionnaire = Questionnaire.findById(params.questionnaireID)
        Question question = Question.findById(params.questionID)
        render(view: "/questions/edit", model: [questionnaire: questionnaire, question: question])
    }

    def updateQuestion() {
        Questionnaire questionnaire = Questionnaire.findById(params.questionnaireID)
        Question question = Question.findById(params.questionID)
        question.content = params.content
        question.answerFormat = params.answerFormat
        if (params.answerFormat == 2) {
            question.choices.clear()
        }
        question.save(true)      // update question immediately
        render(view: "/question/show", model: [questionnaire: questionnaire, question: question])
    }

    def removeQuestion() {
    }

}
