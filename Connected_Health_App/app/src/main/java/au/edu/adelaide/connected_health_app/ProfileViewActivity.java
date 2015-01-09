package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import java.util.Date;


public class ProfileViewActivity extends ActionBarActivity {
    //local profile values
    private String first_name;
    private String last_name;
    private String address;
    private String number;
    private String email;

    private final int patientID = 1;
    private final String journalEntriesUrl = "http://192.168.1.5:9999/ConnectedHealth/patient/" + patientID + "/journal";
    private final String medicalNotesUrl = "http://192.168.1.5:9999/ConnectedHealth/patient/" + patientID + "/notes";
    static int viewId = 1;

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

        // Request a string JSON response from the Grails app.
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, journalEntriesUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // check if response is a valid JSON array
                        JSONArray journalEntriesJson = null;
                        try {
                            journalEntriesJson = new JSONArray(response);
                        } catch (JSONException je) {
                            System.out.println("Response was not a valid JSON array.");
                        }

                        // Extract values from JSON array to display in the medical notes section of the profile view
                        StringBuilder sb = null;
                        try {
                            JSONObject entryObject;
                            sb = new StringBuilder();
                            sb.append("Journal Entries\n");
                            for (int i = 0; i < journalEntriesJson.length(); i++) {
                                entryObject = journalEntriesJson.getJSONObject(i);
                                sb.append("Created: " + (entryObject.get("created")).toString() + "\n");
                                sb.append("Updated: " + (entryObject.get("updated")).toString() + "\n");
                                sb.append("Content: " + entryObject.getString("content") + "\n");
                            }
                        } catch (JSONException je) {
                            System.out.println("Couldn't extract values from JSON array");
                        }

                        // Display values
                        TextView journal_entries = (TextView)findViewById(R.id.journal_entries);
                        journal_entries.append("\n" + sb.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Volley HTTP request for journal entries failed.");
            }
        });
        // Add the request to the RequestQueue for asynchronous handling.
        queue.add(stringRequest2);


        final String[] staticJournalEntries = {"The first journal entry.\nWith two lines.", "The second journal entry.", "The third journal entry.", "The fourth journal entry."};
        final RelativeLayout staticJournalEntriesLayout=(RelativeLayout) findViewById(R.id.staticJournalEntryLayout);
        ArrayList<TextView> staticEntryTextViews = new ArrayList<TextView>();
        ArrayList<Button> staticEntryEditButtons = new ArrayList<Button>();
        ArrayList<Button> staticEntryDeleteButtons = new ArrayList<Button>();

        // for each journal entry, display the entry content in a TextView, with edit and delete Buttons below it
        // assign a unique ID to each TextView/Button, so they can be arranged in a RelativeLayout
        Button recentEditButton = null;
        for (int i = 0; i < staticJournalEntries.length; i++) {
            TextView currentTextView = new TextView(this);
            staticEntryTextViews.add(currentTextView);
            RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams
                    ((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int) RelativeLayout.LayoutParams.WRAP_CONTENT);
            if (i > 0) {    // put entry below the buttons for the previous entry (excluding 1st question)
                textViewParams.addRule(RelativeLayout.BELOW, recentEditButton.getId());
            }
            currentTextView.setId(++viewId);
            currentTextView.setLayoutParams(textViewParams);
            currentTextView.setPadding(10, 10, 10, 0);
            currentTextView.setText(staticJournalEntries[i]);
            currentTextView.setTextSize((float) 20);
            staticJournalEntriesLayout.addView(currentTextView);

            Button currentEditButton = new Button(this);
            staticEntryEditButtons.add(currentEditButton);
            RelativeLayout.LayoutParams editButtonParams = new RelativeLayout.LayoutParams
                    ((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int) RelativeLayout.LayoutParams.WRAP_CONTENT);
            editButtonParams.addRule(RelativeLayout.BELOW, currentTextView.getId());
            currentEditButton.setId(++viewId);
            currentEditButton.setLayoutParams(editButtonParams);
            currentEditButton.setText("Edit");
            currentEditButton.setTextSize((float) 20);
            staticJournalEntriesLayout.addView(currentEditButton);
            recentEditButton = currentEditButton;

            Button currentDeleteButton = new Button(this);
            staticEntryDeleteButtons.add(currentDeleteButton);
            RelativeLayout.LayoutParams deleteButtonParams = new RelativeLayout.LayoutParams
                    ((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int) RelativeLayout.LayoutParams.WRAP_CONTENT);
            deleteButtonParams.addRule(RelativeLayout.BELOW, currentTextView.getId());
            deleteButtonParams.addRule(RelativeLayout.RIGHT_OF, recentEditButton.getId());
            currentDeleteButton.setId(++viewId);
            currentDeleteButton.setLayoutParams(deleteButtonParams);
            currentDeleteButton.setText("Delete");
            currentDeleteButton.setTextSize((float) 20);
            staticJournalEntriesLayout.addView(currentDeleteButton);
        }

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

    /** Called when the user clicks the Information View button */
    public void goToInformationView(View view) {
        Intent intent = new Intent(this, InformationViewActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Questionnaire View button */
    public void goToQuestionnaireView(View view) {
        Intent intent = new Intent(this, QuestionnaireViewActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Profile View button */
    public void goToProfileView(View view) {
        //already on view disable
//        Intent intent = new Intent(this, ProfileViewActivity.class);
//        startActivity(intent);
    }

    /** Called when the user clicks the Measurement View button */
    public void goToMeasurementView(View view) {
        Intent intent = new Intent(this, MeasurementViewActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Home View button */
    public void goToMainView(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
