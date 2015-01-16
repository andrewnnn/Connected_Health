package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MedicalNotesViewActivity extends QuickMenu {

    private final int patientID = 1;
    private ArrayList<JSONObject> medicalNotesForPreviews;
//    private final String medicalNotesUrl = "http://129.127.251.97:8080/ConnectedHealth/patient/" + patientID + "/notes";
    String medicalNotesUrl = "http://129.127.251.97:8080/ConnectedHealth/medicalNote/notes?patientID=1";
    private int pageNumber = -1;
    private final int textPreviewsPerPage = HelperSingleton.getInstance().getTextPreviewsPerPage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_text_preview_view);

        // Instantiate the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string JSON response from the Grails app.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, medicalNotesUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // check if response is a valid JSON array
                        JSONArray medicalNotesJson = null;
                        try {
                            medicalNotesJson = new JSONArray(response);
                        } catch (JSONException je) {
                            System.out.println("Response was not a valid JSON array.");
                        }

                        // Extract values from JSON array to display in the medical notes section of the profile view
                        StringBuilder sb = null;
                        try {
                            JSONObject noteObject;
                            sb = new StringBuilder();
                            sb.append("Medical Notes\n");
                            for (int i = 0; i < medicalNotesJson.length(); i++) {
                                noteObject = medicalNotesJson.getJSONObject(i);
                                sb.append("Date: " + (noteObject.get("created")).toString() + "\n");
                                sb.append("Content: " + noteObject.getString("content") + "\n");
                            }
                        } catch (JSONException je) {
                            System.out.println("Couldn't extract values from JSON array");
                        }

                        // Display values
                        TextView medical_notes = (TextView)findViewById(R.id.preview_text0);
                        medical_notes.append("\n" + sb.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Volley HTTP request for medical notes failed.");
            }
        });
        // Add the request to the RequestQueue for asynchronous handling.
        queue.add(stringRequest);

        try {
            if (getIntent().hasExtra("pageNumber")) {
                pageNumber = getIntent().getExtras().getInt("pageNumber");
                medicalNotesForPreviews = PatientSingleton.getInstance().getMedicalNotes(pageNumber * textPreviewsPerPage, pageNumber * textPreviewsPerPage + 2);
            } else {
                medicalNotesForPreviews = PatientSingleton.getInstance().getMedicalNotes(0, 2);
                pageNumber = 0;
            }
            int i;

            // for each preview, set background colour to match home panel and set preview text
            for (i = 0; i < medicalNotesForPreviews.size(); i++){
                String created = medicalNotesForPreviews.get(i).getString("created");
                String content = medicalNotesForPreviews.get(i).getString("content");
                String preview = created + "\n\n" + content;

                int resID = getResources().getIdentifier("preview_text" + i,
                        "id", getPackageName());
                TextView previewText = (TextView) findViewById(resID);
                previewText.setText(preview);
            }

            // if there are less preview items than preview spaces, remove colour/click listener for unused preview panels
            for (; i < textPreviewsPerPage; i++) {
                int resID = getResources().getIdentifier("preview" + i,
                        "id", getPackageName());
                RelativeLayout previewLayout = (RelativeLayout) findViewById(resID);
                previewLayout.setOnClickListener(null);
                previewLayout.setBackgroundColor(0x00000000);       // transparent background
            }
        } catch (JSONException je) {
            System.out.println("getting med notes failed");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_medical_notes_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToSingleItemView(View view) {
        int itemPageOffset = -1;
        switch(view.getId()) {
            case R.id.preview0:
                PatientSingleton.getInstance().setCurrentObject(medicalNotesForPreviews.get(0));
                itemPageOffset = 0;
                break;
            case R.id.preview1:
                PatientSingleton.getInstance().setCurrentObject(medicalNotesForPreviews.get(1));
                itemPageOffset = 1;
                break;
            case R.id.preview2:
                PatientSingleton.getInstance().setCurrentObject(medicalNotesForPreviews.get(2));
                itemPageOffset = 2;
                break;
        }

        PatientSingleton.getInstance().setCurrentItemType(PatientSingleton.ItemType.medicalNote);
        Intent intent = new Intent(this, SingleItemViewActivity.class);
        intent.putExtra("itemIndex",pageNumber*textPreviewsPerPage + itemPageOffset);
        startActivity(intent);
    }

    public void goToPreviousPage(View view) {
        if (pageNumber > 0) {
            Intent intent = new Intent(this, MedicalNotesViewActivity.class);
            intent.putExtra("pageNumber", pageNumber - 1);
            startActivity(intent);
        }
    }

    public void goToNextPage(View view) {
        if ((pageNumber+1)*textPreviewsPerPage <= PatientSingleton.getInstance().getMedicalNotes().length() - 1) {
            Intent intent = new Intent(this, MedicalNotesViewActivity.class);
            intent.putExtra("pageNumber", pageNumber + 1);
            startActivity(intent);
        }
    }
}
