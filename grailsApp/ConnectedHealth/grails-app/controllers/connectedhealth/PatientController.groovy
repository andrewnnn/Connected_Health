package connectedhealth

class PatientController {

    def scaffold = Patient

    def showView() {
        Patient patient = Patient.findById(params.patientID)
        Set<JournalEntry> recentJournalEntries = patient.journalEntries
        int journalEntriesCount = recentJournalEntries.size()
        Set<MedicalNote> recentMedicalNotes = patient.medicalNotes
        int medicalNotesCount = recentMedicalNotes.size()

        final int DATE_CHARS = 16
        final int PREVIEW_CHARS = 100
        final int PREVIEW_COUNT = 3

        render(view: "/patients/show", model:
                [patient: patient,
                        recentMedicalNotes: recentMedicalNotes,
                        recentJournalEntries: recentJournalEntries,
                        journalEntriesCount: journalEntriesCount,
                        medicalNotesCount: medicalNotesCount,
                        DATE_CHARS: DATE_CHARS,
                        PREVIEW_CHARS: PREVIEW_CHARS,
                        PREVIEW_COUNT: PREVIEW_COUNT])
    }
}
