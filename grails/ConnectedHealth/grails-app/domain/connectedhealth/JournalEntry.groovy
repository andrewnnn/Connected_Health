package connectedhealth

class JournalEntry {

    JournalEntry(int _patientID, String _content, Date _created, Date _updated){
        patientID = _patientID
        content = _content
        created = _created
        updated = _updated
    }

    int patientID
    String content
    static mapping = {
        content type: "text", length: 5000
    }

    Date created
    Date updated

    static constraints = {
    }

}
