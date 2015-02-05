package connectedhealth

class SingleSelectionAnswer {

    Choice choice
    Question question

    static belongsTo = [submission: Submission]

    static constraints = {
    }
}
