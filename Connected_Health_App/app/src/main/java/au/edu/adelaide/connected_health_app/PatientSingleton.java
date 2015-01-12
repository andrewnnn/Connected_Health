package au.edu.adelaide.connected_health_app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PatientSingleton {
    private static PatientSingleton mInstance = null;

    private String mString;

    private JSONArray jJournalEntries;
    private JSONArray jMedicalNotes;

    private PatientSingleton(){
        mString = "Private constructor string";
    }

    public static PatientSingleton getInstance(){
        if(mInstance == null)
        {
            mInstance = new PatientSingleton();
        }
        return mInstance;
    }

    public String getString(){
        return this.mString;
    }

    public void setString(String value){
        mString = value;
    }

    public void setJournalEntries(String journalEntries) throws JSONException {
        jJournalEntries = new JSONArray(journalEntries);
    }

    public void setMedicalNotes(String medicalNotes) throws JSONException {
        jMedicalNotes = new JSONArray(medicalNotes);
    }

    public JSONArray getJournalEntries() {
        return jJournalEntries;
    }

    public ArrayList<JSONObject> getJournalEntries(int first, int last) throws JSONException {
        ArrayList<JSONObject> journalEntries = new ArrayList<JSONObject>();
        for (int i = first; i <= last; i++) {
            journalEntries.add(jJournalEntries.getJSONObject(i));
        }
        return journalEntries;
    }

    public JSONArray getMedicalNotes() {
        return jMedicalNotes;
    }

    public ArrayList<JSONObject> getMedicalNotes(int first, int last) throws JSONException {
        ArrayList<JSONObject> medicalNotes = new ArrayList<JSONObject>();
        for (int i = first; i <= last; i++) {
            medicalNotes.add(jMedicalNotes.getJSONObject(i));
        }
        return medicalNotes;
    }
}