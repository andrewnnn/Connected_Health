package connectedhealth

class Submission {

    Date created

    static belongsTo = [patient: Patient, questionnaire: Questionnaire]
    static hasMany = [singleSelectionAnswer: SingleSelectionAnswer, multipleSelectionAnswer: MultipleSelectionAnswer, textAnswer: TextAnswer]

    static constraints = {
    }
}
