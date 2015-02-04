package connectedhealth

class JournalEntry {

    static belongsTo = [patient: Patient]       // deleting Patient also deletes JournalEntries

    String content

    static mapping = {
        content type: "text", length: 5000
    }

    Date created
    Date updated

    static constraints = {
    }

}
