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
    private String phone;
    private String email;
    private boolean profileSetUp = false;

    private final int patientID = 1;
    private final String journalEntriesUrl = "http://192.168.1.5:9999/ConnectedHealth/patient/" + patientID + "/journal";
    private final String medicalNotesUrl = "http://192.168.1.5:9999/ConnectedHealth/patient/" + patientID + "/notes";
    static int viewId = 1;      // get unique ID for views

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        setProfileDummy();

        //check if updated profile values
        try {
            String newFirstName = getIntent().getExtras().getString("newFirstName");
            String newLastName = getIntent().getExtras().getString("newLastName");
            String newAddress = getIntent().getExtras().getString("newAddress");
            String newPhone = getIntent().getExtras().getString("newPhone");
            String newEmail = getIntent().getExtras().getString("newEmail");

            if (newFirstName != null && !newFirstName.isEmpty()) {
                setFirstName(newFirstName);
            }
            if (newLastName != null && !newLastName.isEmpty()) {
                setLastName(newLastName);
            }
            if (newAddress != null && !newAddress.isEmpty()) {
                setAddress(newAddress);
            }
            if (newPhone != null && !newPhone.isEmpty()) {
                setPhone(newPhone);
            }
            if (newEmail != null && !newEmail.isEmpty()) {
                setEmail(newEmail);
            }
        } catch (Exception e) {System.out.println("updated profile value error");}

        //displaying profile values within view
        TextView profile_values = (TextView)findViewById(R.id.profile_content);
        profile_values.setText("Profile:\n" +
                                "First Name: " + first_name + "\n" +
                                "Last Name: " + last_name + "\n" +
                                "Address: " + address + "\n" +
                                "Phone Number: " + phone + "\n" +
                                "Email: " + email);
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
        phone = "63428756";
        email = "johnsmith@adelaide.com";
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setEmail(String email) {
        this.email = email;
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
