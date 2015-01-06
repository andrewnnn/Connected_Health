package connectedhealth

import org.json.JSONArray
import org.json.JSONObject

class JournalEntryController {

    def scaffold = JournalEntry

    // get journal entries for a single patient
    // (required) patientID = ID of patient to get journal entries for
    def entries() {
        if (params.patientID == null) {
            render "Patient ID is required"
            return
        }

        JournalEntry[] patientJournalEntry = JournalEntry.findAllByPatientID(params.patientID)  // get all notes for this patient

        // return notes in JSON format, with patient ID removed
        ArrayList<JSONObject> JournalEntriesToSend = new ArrayList<JSONObject>()

        for (int i = 0; i < patientJournalEntry.size(); i++) {
            JSONObject journalEntry = new JSONObject()
            journalEntry.put("ID", patientJournalEntry[i].getId())
            journalEntry.put("content", patientJournalEntry[i].getContent())
            journalEntry.put("created", patientJournalEntry[i].getCreated())
            journalEntry.put("updated", patientJournalEntry[i].getUpdated())
            JournalEntriesToSend.add(journalEntry)
        }

        JSONArray JournalEntryJson = new JSONArray(JournalEntriesToSend)
        render JournalEntryJson.toString()
    }

}
