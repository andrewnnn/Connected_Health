package connectedhealth


class MeasurementType {
    String name
    String description

    static hasMany = [patients: Patient]
    static belongsTo = Patient

    static constraints = {
    }
}
