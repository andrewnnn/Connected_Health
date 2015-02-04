package connectedhealth

class Question {

    String content
    int answerFormat        // 0 = radio buttons, 1 = checkboxes, 2 = string (no choices)

    static belongsTo = [questionnaire: Questionnaire]       // deleting Questionnaire also deletes Questions
    static hasMany = [choices: Choice]

    static constraints = {
//        content != null
//        answerFormat == 0 || answerFormat == 1 || answerFormat == 2
    }

    static mapping = {
        choices sort:'content', order:'asc'
    }
}
