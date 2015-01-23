package au.edu.adelaide.connected_health_app;

import android.content.ClipData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

public class PatientSingleton {
    private static PatientSingleton mInstance = null;

    private ItemType mItemType;

    private JSONArray jJournalEntries;
    private JSONArray jMedicalNotes;
    private JSONArray jMeasurementTypes;
    private JSONArray jQuestionnaires;
    private JSONObject jPatient;

    private JSONArray jCurrentArray;
    private JSONObject jCurrentObject;

    private PatientSingleton(){
        jQuestionnaires = new JSONArray();
        mItemType = ItemType.defaultItemType;
    }

    public static PatientSingleton getInstance(){
        if(mInstance == null)
        {
            mInstance = new PatientSingleton();
        }
        return mInstance;
    }

    public enum ItemType {
        defaultItemType,
        journalEntry,
        measurement,
        medicalNote,
        questionnaire
    }

    public void addQuestionnaire(String questionnaire) throws JSONException {
        jQuestionnaires.put(new JSONObject(questionnaire));
    }

    public JSONArray getQuestionnaires() {
        return jQuestionnaires;
    }

    public void setQuestionnaires(String questionnaires) throws JSONException {
        jQuestionnaires = new JSONArray(questionnaires);
    }

    public ArrayList<JSONObject> getQuestionnaires(int first, int last) throws JSONException {
        ArrayList<JSONObject> questionnaires = new ArrayList<JSONObject>();
        for (int i = first; i <= last; i++) {
            if (i >= jQuestionnaires.length()) {
                break;
            }
            questionnaires.add(jQuestionnaires.getJSONObject(i));
        }
        return questionnaires;
    }

    public void setJournalEntries(String journalEntries) throws JSONException {
        jJournalEntries = new JSONArray(journalEntries);
    }

    public void setMedicalNotes(String medicalNotes) throws JSONException {
        jMedicalNotes = new JSONArray(medicalNotes);
    }

    public JSONArray getJournalEntries() {
        jCurrentArray = jJournalEntries;
        return jJournalEntries;
    }

    public ArrayList<JSONObject> getJournalEntries(int first, int last) throws JSONException {
        jCurrentArray = jJournalEntries;
        ArrayList<JSONObject> journalEntries = new ArrayList<JSONObject>();
        for (int i = first; i <= last; i++) {
            if (i >= jJournalEntries.length()) {
                break;
            }
            journalEntries.add(jJournalEntries.getJSONObject(i));
        }
        return journalEntries;
    }

    public void setMeasurementTypes(String measurementTypes) throws JSONException {
        jMeasurementTypes = new JSONArray(measurementTypes);
    }

    public JSONArray getMeasurementTypes() {
        return jMeasurementTypes;
    }

    public ArrayList<JSONObject> getMeasurementTypes(int first, int last) throws JSONException {
        jCurrentArray = jMeasurementTypes;
        ArrayList<JSONObject> measurementTypes = new ArrayList<JSONObject>();
        for (int i = first; i <= last; i++) {
            if (i >= jMeasurementTypes.length()) {
                break;
            }
            measurementTypes.add(jMeasurementTypes.getJSONObject(i));
        }
        return measurementTypes;
    }

    public JSONArray getMedicalNotes() {
        jCurrentArray = jMedicalNotes;
        return jMedicalNotes;
    }

    public ArrayList<JSONObject> getMedicalNotes(int first, int last) throws JSONException {
        jCurrentArray = jMedicalNotes;
        ArrayList<JSONObject> medicalNotes = new ArrayList<JSONObject>();
        for (int i = first; i <= last; i++) {
            if (i >= jMedicalNotes.length()) {
                break;
            }
            medicalNotes.add(jMedicalNotes.getJSONObject(i));
        }
        return medicalNotes;
    }

    public void setPatient(String patient) throws JSONException {
        jPatient = new JSONObject(patient);
    }

    public JSONObject getPatient() {
        return jPatient;
    }

    public void setCurrentObject(JSONObject object) {
        jCurrentObject = object;
    }

    public JSONObject getCurrentObject() {
        return jCurrentObject;
    }

    public void setCurrentItemType(ItemType type) {
        mItemType = type;
    }

    public ItemType getCurrentItemType() {
        return mItemType;
    }

    public Class getBackToPreviewsClass() {
        switch (mItemType) {
            case journalEntry:
                return JournalViewActivity.class;
            case measurement:
                return MeasurementViewActivity.class;
            case medicalNote:
                return MedicalNotesViewActivity.class;
            case questionnaire:
                return QuestionnaireViewActivity.class;
            default:
                return MainActivity.class;
        }
    }

    public JSONArray getCurrentArray() {
        return jCurrentArray;
    }
}
