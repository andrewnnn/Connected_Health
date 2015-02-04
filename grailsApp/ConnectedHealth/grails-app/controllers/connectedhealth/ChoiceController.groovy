package connectedhealth

class ChoiceController {

    def scaffold = Choice

    def indexView() {}

    def showView() {
        Questionnaire questionnaire = Questionnaire.findById(params.questionnaireID)
        Question question = Question.findById(params.questionID)
        Choice choice = Choice.findById(params.choiceID)
        render(view: "/choices/show", model: [questionnaire: questionnaire, question: question, choice: choice])
    }

    def newChoice() {
        Questionnaire questionnaire = Questionnaire.findById(params.questionnaireID)
        Question question = Question.findById(params.questionID)
        render(view: "/choices/new", model: [questionnaire: questionnaire, question: question])
    }

    def createChoice() {
        Questionnaire questionnaire = Questionnaire.findById(params.questionnaireID)
        Question question = Question.findById(params.questionID)
        Choice choice = new Choice(content: params.content, question: question)
        choice.save(true)
        redirect(uri: "/questionnaires/${questionnaire.id}/questions/${question.id}/show")
    }

    def editView() {
        Questionnaire questionnaire = Questionnaire.findById(params.questionnaireID)
        Question question = Question.findById(params.questionID)
        Choice choice = Choice.findById(params.choiceID)
        render(view: "/choices/edit", model: [questionnaire: questionnaire, question: question, choice: choice])
    }

    def updateChoice() {
        Questionnaire questionnaire = Questionnaire.findById(params.questionnaireID)
        Question question = Question.findById(params.questionID)
        Choice choice = Choice.findById(params.choiceID)
        choice.content = params.content
        choice.save(flush: true)
        redirect(uri: "/questionnaires/${questionnaire.id}/questions/${question.id}/choices/${choice.id}/show")
    }

    def removeChoice() {
        Questionnaire questionnaire = Questionnaire.findById(params.questionnaireID)
        Question question = Question.findById(params.questionID)
        Choice choice = Choice.findById(params.choiceID)
        choice.delete(flush: true)
        redirect(uri: "/questionnaires/${questionnaire.id}/questions/${question.id}/show")
    }

}
