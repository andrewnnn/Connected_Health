package connectedhealth

class Questionnaire {

    String description
    String name

    static hasMany = [questions: Question]

    static constraints = {

    }

    static mapping = {
        questions sort:'content', order:'asc'
    }
}
