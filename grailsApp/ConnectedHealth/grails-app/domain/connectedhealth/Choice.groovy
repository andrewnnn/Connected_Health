package connectedhealth

class Choice {

    String content

    static belongsTo = [question: Question]       // deleting Question also deletes Choices

    static constraints = {
//        content != null
    }
}
