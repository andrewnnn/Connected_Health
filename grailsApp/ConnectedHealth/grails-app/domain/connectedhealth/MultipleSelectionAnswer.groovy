package connectedhealth

class MultipleSelectionAnswer {

    Question question

    static belongsTo = [submission: Submission]
    static hasMany = [choices: Choice]

    static constraints = {
    }
}
