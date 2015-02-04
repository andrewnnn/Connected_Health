package connectedhealth

import org.json.JSONArray
import org.json.JSONObject

class MeasurementTypeController {
    def types() {
        Patient patient = Patient.findById(params.patientID);
//        MeasurementType[] measurementTypes = patient.measurementTypes;      // ERROR: column measuremen0_.name does not exist
        MeasurementType[] measurementTypes = MeasurementType.list(sort:'name')
        ArrayList<JSONObject> types = new ArrayList<JSONObject>();
        for (int i = 0; i < measurementTypes.size(); i++) {
            JSONObject object = new JSONObject();
            object.put("name", measurementTypes[i].name);
            object.put("description", measurementTypes[i].description);
            types.add(object);
        }
        JSONArray array = new JSONArray(types);
        render array.toString()
    }
}
