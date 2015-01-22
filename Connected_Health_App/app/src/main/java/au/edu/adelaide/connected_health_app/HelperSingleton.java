package au.edu.adelaide.connected_health_app;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

public class HelperSingleton {
    private static HelperSingleton mInstance = null;

    private final int textPreviewsPerPage = 3;
    private int patientId;
    private String ipAddress;
    private String port;

    private boolean serverIsAvailable = false;
    private boolean serverResponse = false;

    private HelperSingleton() {}

    public static HelperSingleton getInstance() {
        if(mInstance == null)
        {
            mInstance = new HelperSingleton();
        }
        return mInstance;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getConstantUrl() {
        return "http://" + ipAddress + ":" + port + "/ConnectedHealth/";
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

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getPatientId() {
        return patientId;
    }

    public boolean checkServer(Context context) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = getConstantUrl();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        serverResponse = true;
                        serverIsAvailable = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                serverResponse = false;
                serverIsAvailable = true;
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        return serverResponse;
    }

    public boolean getServerAvailability() {
        return serverIsAvailable;
    }

    public void updateJournalEntries(Context context) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String path = "patients/" + getPatientId() + "/journal/json";
        String url = getConstantUrl() + path;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            PatientSingleton.getInstance().setJournalEntries(response);
                        } catch (JSONException je) {
                            System.out.println(je);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Volley for updateJournalEntries failed.");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void updateMedicalNotes(Context context) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String path = "patients/" + getPatientId() + "/medicalnotes/json";
        String url = getConstantUrl() + path;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            PatientSingleton.getInstance().setMedicalNotes(response);
                        } catch (JSONException je) {
                            System.out.println(je);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Volley for updateMedicalNotes failed.");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void updateQuestionnaires(Context context) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String path = "questionnaire/questionnaires";
        String url = getConstantUrl() + path;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            PatientSingleton.getInstance().setQuestionnaires(response);
                        } catch (JSONException je) {
                            System.out.println(je);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Volley for updateQuestionnaires failed.");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
