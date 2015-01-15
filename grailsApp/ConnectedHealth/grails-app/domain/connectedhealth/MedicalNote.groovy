package connectedhealth

class MedicalNote {

    static belongsTo = [patient: Patient]       // deleting Patient also deletes MedicalNotes

    String content

    static mapping = {
        content type: "text", length: 1000
    }

    Date created

    static constraints = {
    }
}
