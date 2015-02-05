package connectedhealth

class Patient {

    String contactEmail;
    String firstName;
    String homeAddress;
    String lastName;
    String phone;

    static hasMany = [journalEntries: JournalEntry, medicalNotes: MedicalNote, measurementTypes: MeasurementType, submissions: Submission]

    static constraints = {
    }

    static mapping = {
        journalEntries sort:'created', order:'desc'
        measurementTypes sort:'name'
        medicalNotes sort:'created', order:'desc'
    }
}
