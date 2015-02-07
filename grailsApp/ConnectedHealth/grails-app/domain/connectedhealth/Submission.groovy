package connectedhealth

class Submission {

    Date created

    Patient patient
    Questionnaire questionnaire
//    static belongsTo = [patient: Patient, questionnaire: Questionnaire]
    static hasMany = [singleSelectionAnswers: SingleSelectionAnswer, multipleSelectionAnswers: MultipleSelectionAnswer, textAnswers: TextAnswer]

    static constraints = {
    }
}
