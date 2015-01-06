package connectedhealth

import javax.print.attribute.standard.DateTimeAtCreation

class MedicalNote {

    MedicalNote(int _patientID, String _content, Date _created){
        patientID = _patientID
        content = _content
        created = _created
    }

    int patientID
    String content
    static mapping = {
            content type: "text", length: 1000
    }

    Date created

    static constraints = {
    }
}
