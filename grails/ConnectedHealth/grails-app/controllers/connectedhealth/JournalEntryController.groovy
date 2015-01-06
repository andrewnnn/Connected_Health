package connectedhealth

import org.json.JSONArray
import org.json.JSONObject

class JournalEntryController {

    def scaffold = JournalEntry

    def showEntry() {

        if (params.patientID == null) {
            render "Patient ID is required"
            return
        }
        JournalEntry[] patientJournalEntry = JournalEntry.findAllByPatientID(params.patientID)
        // get all notes for this patient

        // return notes in JSON format, with patient ID removed
        ArrayList<JSONObject> JournalEntryToSend = new ArrayList<JSONObject>()

        for (int i = 0; i < patientJournalEntry.size(); i++) {
            JSONObject journalEntry = new JSONObject()
            journalEntry.put("ID", patientJournalEntry[i].getId())
            journalEntry.put("content", patientJournalEntry[i].getCreated())
            journalEntry.put("created", patientJournalEntry[i].getContent())
            journalEntry.put("updated", patientJournalEntry[i].getContent())
            JournalEntryToSend.add(journalEntry)
        }

        JSONArray JournalEntryJson = new JSONArray(JournalEntryToSend)
        render JournalEntryJson.toString()
    }

}
