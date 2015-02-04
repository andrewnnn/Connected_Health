package connectedhealth

import org.json.JSONArray
import org.json.JSONObject

class PatientController {

    final int PREVIEW_CHARS = 100
    final int PREVIEW_COUNT = 3

    def scaffold = Patient

    def indexView() {
        Set<Patient> patients = Patient.list(sort:'lastName')
        render(view: "/patients/index", model:
                [patients: patients])
    }

    def showView() {
        Patient patient = Patient.findById(params.patientID)

        ArrayList<JournalEntry> allJournalEntries = patient.journalEntries
        ArrayList<JournalEntry> recentJournalEntries = new ArrayList<JournalEntry>()
        for (int i = 0; i < PREVIEW_COUNT; i++) {
            if (i == allJournalEntries.size()) {
                break;
            }
            recentJournalEntries.add(allJournalEntries.get(i))
        }
        int journalEntriesCount = allJournalEntries.size()

        ArrayList<MedicalNote> allMedicalNotes = patient.medicalNotes
        ArrayList<MedicalNote> recentMedicalNotes = new ArrayList<MedicalNote>()
        for (int i = 0; i < PREVIEW_COUNT; i++) {
            if (i == allMedicalNotes.size()) {
                break;
            }
            recentMedicalNotes.add(allMedicalNotes.get(i))
        }
        int medicalNotesCount = allMedicalNotes.size()

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
        patient.contactEmail = params.contactEmail
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

    def get() {
        Patient patient = Patient.findById(params.patientID)
        JSONObject object = new JSONObject()
        object.put("contactEmail", patient.contactEmail)
        object.put("firstName", patient.firstName)
        object.put("homeAddress", patient.homeAddress)
        object.put("lastName", patient.lastName)
        object.put("phone", patient.phone)
        render object.toString()
    }

}
