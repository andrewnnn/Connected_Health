package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

import java.util.Date;


public class ProfileViewActivity extends ActionBarActivity {
    //local profile values
    private String first_name;
    private String last_name;
    private String address;
    private String number;
    private String email;

    private final int patientID = 1;
    private final String medicalNotesUrl = "http://129.127.210.243:9999/ConnectedHealth/patients/" + patientID + "/notes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        setProfileDummy();

        //check if updated profile values
        try {
            String newFirstName = getIntent().getExtras().getString("newFirstName");
            if (newFirstName != null) {
                setFirstName(newFirstName);
            }
        } catch (Exception e) {System.out.println("updated profile value error");}

        //displaying profile values within view
        TextView profile_values = (TextView)findViewById(R.id.profile_content);
        profile_values.setText("Profile:\n" +
                                "First Name: " + first_name + "\n" +
                                "Last Name: " + last_name + "\n" +
                                "Address: " + address + "\n" +
                                "Phone Number: " + number + "\n" +
                                "Email: " + email);

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
                        TextView medical_notes = (TextView)findViewById(R.id.medical_notes);
                        medical_notes.setText(sb.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Volley HTTP request for medical notes failed.");
            }
        });
        // Add the request to the RequestQueue for asynchronous handling.
        queue.add(stringRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_view, menu);
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

    /** Called when the user clicks the Profile Edit button */
    public void goToProfileEdit(View view) {
        Intent intent = new Intent(this, ProfileEditActivity.class);
        startActivity(intent);
    }

    /** Profile setters */

    //set initial profile values to dummy values
    public void setProfileDummy() {
        first_name = "John";
        last_name = "Smith";
        address = "123 Example St Fakeville";
        number = "63428756";
        email = "johnsmith@adelaide.com";
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }
}
