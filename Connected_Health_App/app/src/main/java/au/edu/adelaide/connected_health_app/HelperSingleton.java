package au.edu.adelaide.connected_health_app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HelperSingleton {
    private static HelperSingleton mInstance = null;

    private final int textPreviewsPerPage = 3;

    private HelperSingleton() {}

    public static HelperSingleton getInstance() {
        if(mInstance == null)
        {
            mInstance = new HelperSingleton();
        }
        return mInstance;
    }

    public int getTextPreviewsPerPage() {
        return textPreviewsPerPage;
    }

    public Class getMeasurementTypeClass() {
        String measurementName = null;
        try {
            measurementName = PatientSingleton.getInstance().getCurrentObject().getString("name");
        } catch (JSONException je) {
            System.out.println("Couldn't get measurement name.");
            return null;
        }

        if (measurementName.equals("Steps")) {
            return MeasurementStepsViewActivity.class;
        } else if (measurementName.equals("Weight")) {
            return MeasurementStepsViewActivity.class;  //TODO
        } else {
            return null;
        }
    }
}
