package connectedhealth

class MultipleSelectionAnswer {

    static belongsTo = [submission: Submission]
    static hasOne = [question: Question]
    static hasMany = [choice: Choice]

    static constraints = {
    }
}
