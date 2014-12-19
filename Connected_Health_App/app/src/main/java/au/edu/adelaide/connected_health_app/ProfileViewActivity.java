package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ProfileViewActivity extends ActionBarActivity {
    //local profile values
    private String first_name;
    private String last_name;
    private String address;
    private String number;
    private String email;

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
