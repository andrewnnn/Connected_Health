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

        Patient p = Patient.findById(params.patientID)
        if (p == null) {
            response.status = 404
            render "Patient with this ID does not exist"
            return
        }

        JournalEntry[] patientJournalEntries = p.getJournalEntries()  // get all journal entries for this patient

        if (patientJournalEntries == null) {
            render '[]'
            return
        }

        // return notes in JSON format, with patient ID removed
        ArrayList<JSONObject> JournalEntriesToSend = new ArrayList<JSONObject>()

        for (int i = 0; i < patientJournalEntries.size(); i++) {
            JSONObject journalEntry = new JSONObject()
            journalEntry.put("ID", patientJournalEntries[i].getId())
            journalEntry.put("content", patientJournalEntries[i].getContent())
            journalEntry.put("created", patientJournalEntries[i].getCreated())
            journalEntry.put("updated", patientJournalEntries[i].getUpdated())
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
        Patient p = Patient.findById(params.patientID)
        if (p == null) {
            response.status = 404
            render "Patient with this ID does not exist"
            return
        }

        JournalEntry a = new JournalEntry(patient: p, content: params.content, created: new Date(), updated: new Date());
        a.save();
        render "Journal Entry Created"
    }

    def updateEntry(){
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
        Patient p = Patient.findById(params.patientID)
        if (p == null) {
            response.status = 404
            render "Patient with this ID does not exist"
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
