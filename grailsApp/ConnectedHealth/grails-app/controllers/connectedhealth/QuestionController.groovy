package connectedhealth

class QuestionController {

    def scaffold = Question

    def showView() {
        Questionnaire questionnaire = Questionnaire.findById(params.questionnaireID)
        Question question = Question.findById(params.questionID)
        render(view: "/questions/show", model: [questionnaire: questionnaire, question: question])
    }

    def newView() {
        Questionnaire questionnaire = Questionnaire.findById(params.questionnaireID)
        render(view: "/questions/new", model: [questionnaire: questionnaire])
    }

    def createQuestion() {
        Questionnaire questionnaire = Questionnaire.findById(params.questionnaireID)
        Question question = new Question(content: params.content, answerFormat: params.answerFormat, questionnaire: questionnaire)
        render(view: "/questions/show", model: [questionnaire: questionnaire, question: question])
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
