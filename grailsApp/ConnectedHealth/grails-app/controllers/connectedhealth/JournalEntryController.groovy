package connectedhealth

import org.json.JSONArray
import org.json.JSONObject

class JournalEntryController {

    def scaffold = JournalEntry

    // get journal entries for a single patient
    // (required) patientID = ID of patient to get journal entries for
    def entries() {
        if (params.patientID == null) {
            response.status = 404
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

    def newEntry(){
        if (params.patientID == null) {
            response.status = 404
            render "Patient ID is required"
            return
        }
        if (params.content == null) {
            response.status = 404
            render "Content is required"
            return
        }
        JournalEntry a = new JournalEntry(Integer.parseInt(params.patientID), params.content,new Date(), new Date());
        a.save();
        render "Journal Entry Created"
    }

    def updateEntry(){
        if (params.patientID == null) {
            response.status = 404
            render "Patient ID is required"
            return
        }
        if (params.journalEntryID == null) {
            response.status = 404
            render "Journal Entry ID is required"
            return
        }
        if (params.content == null) {
            response.status = 404
            render "Journal content is required"
            return
        }
        JournalEntry patientJournalEntry = JournalEntry.findById(params.journalEntryID)
        patientJournalEntry.setContent(params.content)
        patientJournalEntry.setUpdated(new Date())
        patientJournalEntry.save(flush: true)
        render "Journal Entry Updated"
    }

    def removeEntry(){
        if (params.patientID == null) {
            response.status = 404
            render "Patient ID is required"
            return
        }
        if (params.journalEntryID == null) {
            response.status = 404
            render "Journal Entry ID is required"
            return
        }
        JournalEntry patientJournalEntry = JournalEntry.findById(params.journalEntryID)
        patientJournalEntry.delete(flush: true)
        render "Journal Entry Deleted"
    }


}
