package connectedhealth

import javax.print.attribute.standard.DateTimeAtCreation

class MedicalNote {

    int patientID
    String content
    Date created

    static constraints = {
    }
}
