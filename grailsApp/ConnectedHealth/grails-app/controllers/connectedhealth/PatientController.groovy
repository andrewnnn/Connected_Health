package connectedhealth

class PatientController {

    def scaffold = Patient

    def indexView() {
        Set<Patient> patients = Patient.list(sort:'lastName')
        render(view: "/patients/index", model:
                [patients: patients])
    }

    def showView() {
        final int PREVIEW_CHARS = 100
        final int PREVIEW_COUNT = 3

        Patient patient = Patient.findById(params.patientID)
        Set<JournalEntry> recentJournalEntries = JournalEntry.findAllByPatient(patient, [max: PREVIEW_COUNT])
        int journalEntriesCount = patient.journalEntries.size()
        Set<MedicalNote> recentMedicalNotes = MedicalNote.findAllByPatient(patient, [max: PREVIEW_COUNT])
        int medicalNotesCount = patient.medicalNotes.size()

        render(view: "/patients/show", model:
                [patient: patient,
                        recentMedicalNotes: recentMedicalNotes,
                        recentJournalEntries: recentJournalEntries,
                        journalEntriesCount: journalEntriesCount,
                        medicalNotesCount: medicalNotesCount,
                        PREVIEW_CHARS: PREVIEW_CHARS,
                        PREVIEW_COUNT: PREVIEW_COUNT])
    }

    def newView() {

    }

    def createPatient() {

    }

    def editView() {
        render (view: "/patients/form")
    }

    def updatePatient() {

    }

    def deletePatient() {}

}
