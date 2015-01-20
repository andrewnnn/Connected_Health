package connectedhealth

import org.json.JSONArray
import org.json.JSONObject

class MedicalNoteController {

    def scaffold = MedicalNote      // basic RESTful views

    def indexView() {
        Patient patient = Patient.findById(params.patientID)
        Set<MedicalNote> medicalNotes = patient.medicalNotes
        render(view: "/medicalNotes/index", model:
                [medicalNotes: medicalNotes,
                        patient: patient])
    }

    def showView() {
        Patient patient = Patient.findById(params.patientID)
        MedicalNote medicalNote = MedicalNote.findById(params.medicalnoteID)
        render(view: "/medicalNotes/show", model:
                [medicalNote: medicalNote,
                        patient: patient])
    }

    def newView() {
        Patient patient = Patient.findById(params.patientID)
        render(view: "/medicalNotes/new", model:
                [patient: patient])
    }

    def createMedicalNote() {
        Patient patient = Patient.findById(params.patientID)
        MedicalNote medicalNote = new MedicalNote(created: new Date(), content: params.content, patient: patient)
        medicalNote.save()
        render(view: "/medicalNotes/show", model: [medicalNote: medicalNote, patient: patient])
    }

    def editView() {
        Patient patient = Patient.findById(params.patientID)
        MedicalNote medicalNote = MedicalNote.findById(params.medicalnoteID)
        render(view: "/medicalNotes/edit", model: [medicalNote: medicalNote, patient: patient])
    }

    def updateMedicalNote() {
        Patient patient = Patient.findById(params.patientID)
        MedicalNote medicalNote = MedicalNote.findById(params.medicalnoteID)
        medicalNote.content = params.content
        medicalNote.save(true)
        render(view: "/medicalNotes/show", model: [medicalNote: medicalNote, patient: patient])
    }

    def deleteMedicalNote() {}


    // get medical notes for a single patient
    // (required) patientID = ID of patient to get medical notes for
    def notes() {
        if (params.patientID == null) {
            response.status = 404
            render "Patient ID is required"
            return
        }
        Patient p = Patient.findById(params.patientID)
        if (p == null) {
            response.status = 404
            render "Patient with this ID does not exist"
            return
        }

        MedicalNote[] patientMedicalNotes = p.getMedicalNotes()   // get all notes for this patient

        if (patientMedicalNotes == null) {
            render '[]'
            return
        }

        // return notes in JSON format, with patient ID removed
        ArrayList<JSONObject> medicalNotesToSend = new ArrayList<JSONObject>()

        for (int i = 0; i < patientMedicalNotes.size(); i++) {
            JSONObject medicalNote = new JSONObject()
            medicalNote.put("ID", patientMedicalNotes[i].getId())
            medicalNote.put("created", patientMedicalNotes[i].getCreated())
            medicalNote.put("content", patientMedicalNotes[i].getContent())
            medicalNotesToSend.add(medicalNote)
        }

        JSONArray medicalNotesJson = new JSONArray(medicalNotesToSend)
        render medicalNotesJson.toString()
    }


    def notesView(){
        render (view:"/NotesView")
    }
}
