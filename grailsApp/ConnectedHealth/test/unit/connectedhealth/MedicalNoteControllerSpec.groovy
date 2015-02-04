package connectedhealth

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.web.json.JSONException
import org.json.JSONArray
import org.json.JSONObject
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(MedicalNoteController)
@Mock([MedicalNote, Patient])
class MedicalNoteControllerSpec extends Specification {

    private static Patient p2;
    private static final CONTENT = "note contents text text text text text text"
    private static final DATE = new Date()
    private static PATIENT2_ID;

    def setup() {
        p2 = new Patient(firstName: "John2", lastName: "Smith2", homeAddress: "123 Example St Fakeville2", phone: "12340987", contactEmail: "john2@smith.ru")
        p2.save()
        PATIENT2_ID = p2.getId()
        MedicalNote mn = new MedicalNote(content: CONTENT, created: DATE, patient: p2)
        mn.save()
    }

    def cleanup() {
    }

    void "missing patientID"() {
        when:
        controller.notes()

        then:
        response.status == 404
        response.text == "Patient ID is required"
    }

    void "valid patientID"() {
        when:
        controller.params.patientID = PATIENT2_ID
        controller.notes()

        then:
        response.status == 200
        response.text != "Patient ID is required"
    }

    void "valid patientID with no entries"() {
        when:
        Patient p5 = new Patient(firstName: "John5", lastName: "Smith5", homeAddress: "123 Example St Fakeville5", phone: "12340987", contactEmail: "john5@smith.ru")
        p5.save()
        controller.params.patientID = p5.getId()     // patient with this id has no medical notes
        controller.notes()

        then:
        response.status == 200
        response.text == "[]"
    }

    void "multiple requests for medical notes, parse as JSON array"() {
        when:
        controller.params.patientID = PATIENT2_ID
        controller.notes()

        then:
        response.status == 200
        response.text != "Patient ID is required"

        try {
            JSONArray a1 = new JSONArray(response.text)
            JSONArray a2 = new JSONArray(response.text)
            JSONArray a3 = new JSONArray(response.text)
        } catch (JSONException je) {
            fail("Couldn't parse medical notes JSON array")
        }
    }

    void "get MedicalNote object as JSON, parse and extract values"() {
        when:
        controller.params.patientID = PATIENT2_ID
        controller.notes()

        then:
        response.status == 200
        response.text != "Patient ID is required"

        JSONArray notesArray
        try {
            notesArray = new JSONArray(response.text)
        } catch (JSONException je) {
            fail("Couldn't parse medical notes JSON array")
        }

        JSONObject medicalNote
        try {
            medicalNote = notesArray.get(0)
        } catch (JSONException je) {
            fail("Couldn't get medical note from JSON array")
        }

        try {
            String content = medicalNote.getString("content")
            assertTrue content.equals(CONTENT)
            String createdString = medicalNote.getString("created")
            assertTrue createdString.equals(DATE.toString())
            int ID = medicalNote.getInt("ID")
        } catch (JSONException je) {
            fail("Couldn't find expected keys")
        }
    }

    void "multiple notes for same patient"() {
        // note for patient with id = 1
        Patient p1 = new Patient(firstName: "John", lastName: "Smith", homeAddress: "123 Example St Fakeville", phone: "12340987", contactEmail: "john@smith.ru")
        p1.save()
        String m1CONTENT = "content of note m1"
        Date m1DATE = new Date(1,1,1,1,1,1)
        MedicalNote m1 = new MedicalNote(content: m1CONTENT, created: m1DATE, patient: p1)
        m1.save()

        // note for patient with id = PATIENT2_ID
        String m2CONTENT = "The string content of note m2."
        Date m2DATE = new Date(2,2,2,2,2,2)
        MedicalNote m2 = new MedicalNote(content: m1CONTENT, created: m1DATE, patient: p2)
        m2.save()

        when:
        controller.params.patientID = PATIENT2_ID
        controller.notes()

        then:
        try {
            JSONArray notesArray = new JSONArray(response.text)
            notesArray.length() == 2

            for (int i = 0; i < notesArray.length(); i++) {
                JSONObject medicalNote = notesArray.getJSONObject(i)
                if (medicalNote.getInt("ID") == m2.getId()) {
                    medicalNote.getString("content") == m2CONTENT
                    medicalNote.getString("created") == m2DATE.toString()
                } else {
                    medicalNote.getString("content") == CONTENT
                    medicalNote.getString("created") == DATE.toString()
                }
            }
        } catch (JSONException je) {
            fail("JSONException")
        }
    }

}
