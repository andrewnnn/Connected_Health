package connectedhealth

class TextAnswer {

    String answer;

    static belongsTo = [submission: Submission]
    static hasOne = [question: Question]

    static constraints = {
    }
}
