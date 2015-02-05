package connectedhealth

class TextAnswer {

    Question question
    String answer;

    static belongsTo = [submission: Submission]

    static constraints = {
    }
}
