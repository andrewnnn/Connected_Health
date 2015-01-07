package connectedhealth

class Questionnaire {

    String name

    static hasMany = [questions: Question]

    static constraints = {

    }
}
