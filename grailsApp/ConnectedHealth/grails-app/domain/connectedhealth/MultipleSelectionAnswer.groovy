package connectedhealth

class MultipleSelectionAnswer {

    Question question

    static belongsTo = [submission: Submission]
    static hasMany = [choice: Choice]

    static constraints = {
    }
}
