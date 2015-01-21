package connectedhealth


class MeasurementType {
    String name
    String Description

    static hasMany = [patient: Patient]
    static belongsTo = Patient

    static constraints = {
    }
}
