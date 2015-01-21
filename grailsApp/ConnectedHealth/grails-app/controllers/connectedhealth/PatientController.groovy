package connectedhealth

class PatientController {

    def scaffold = Patient

    def indexView() {
        Set<Patient> patients = Patient.list(sort:'lastName')
        render(view: "/patients/index", model:
                [patients: patients])
    }

    def showView() {
        final int DATE_CHARS = 16
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
                        DATE_CHARS: DATE_CHARS,
                        PREVIEW_CHARS: PREVIEW_CHARS,
                        PREVIEW_COUNT: PREVIEW_COUNT])
    }

    def newView() {
        render(view: "/patients/newView")
    }

    def createPatient() {
        Patient patient = new Patient(firstName: params.firstName, lastName: params.lastName, contactEmail: params.contactEmail, homeAddress: params.homeAddress, phone: params.phone)
        patient.save(true)
        redirect(uri: "/patients/${patient.id}/show")
    }

    def editView() {
        Patient patient = Patient.findById(params.patientID)

        render (view: "/patients/editView", model:[patient: patient] )
    }

    def updatePatient() {
        Patient patient = Patient.findById(params.patientID)
        patient.contactEmail = params.firstName
        patient.lastName = params.lastName
        patient.firstName = params.firstName
        patient.homeAddress = params.homeAddress
        patient.phone = params.phone

        patient.save(true)
        redirect(uri: "/patients/${patient.id}/show")
    }

    def deletePatient() {
        Patient patient = Patient.findById(params.patientID)
        patient.delete(flush: true)
        redirect(uri: "/patients/")
    }

}
