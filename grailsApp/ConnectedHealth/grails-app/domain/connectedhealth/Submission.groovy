package connectedhealth

class Submission {

    static belongsTo = [patient: Patient, questionnaire: Questionnaire]
    static hasMany = [singleSelectionAnswer: SingleSelectionAnswer, multipleSelectionAnswer: MultipleSelectionAnswer, textAnswer: TextAnswer]

    static constraints = {
    }
}
