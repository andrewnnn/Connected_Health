package connectedhealth

import org.json.JSONArray
import org.json.JSONObject

class MedicalNoteController {

    def scaffold = MedicalNote      // basic RESTful views

    // get medical notes for a single patient
    // (required) patientID = ID of patient to get medical notes for
    def notes() {
        if (params.patientID == null) {
            render "Patient ID is required"
            return
        }

        MedicalNote[] patientMedicalNotes = MedicalNote.findAllByPatientID(params.patientID)   // get all notes for this patient

        // return notes in JSON format, with patient ID removed
        ArrayList<JSONObject> medicalNotesToSend = new ArrayList<JSONObject>()

        for (int i = 0; i < patientMedicalNotes.size(); i++) {
            JSONObject medicalNote = new JSONObject()
            medicalNote.append("ID", patientMedicalNotes[i].getId())
            medicalNote.append("created", patientMedicalNotes[i].getCreated())
            medicalNote.append("content", patientMedicalNotes[i].getContent())
            medicalNotesToSend.add(medicalNote)
        }

        JSONArray medicalNotesJson = new JSONArray(medicalNotesToSend)
        render medicalNotesJson.toString()
    }
}