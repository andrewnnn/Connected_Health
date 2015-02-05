package connectedhealth

class SingleSelectionAnswer {

    static belongsTo = [submission: Submission]
    static hasOne = [choice: Choice, question: Question]

    static constraints = {
    }
}
