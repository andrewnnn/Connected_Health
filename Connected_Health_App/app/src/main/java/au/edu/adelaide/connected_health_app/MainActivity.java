package au.edu.adelaide.connected_health_app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import org.json.JSONException;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Show value stored by private constructor
        Toast.makeText(getApplicationContext(), PatientSingleton.getInstance().getString(), Toast.LENGTH_LONG).show();

        //Change singleton value
        PatientSingleton.getInstance().setString("SETTING A NEW STRING");

        // Set static data in patient singleton
        try {
            PatientSingleton.getInstance().setJournalEntries("[{\"content\":\"I am a rich man, I have many houses\",\"updated\":\"2015-01-09 15:45:12.177\",\"created\":\"2015-01-09 15:45:12.177\",\"ID\":14},{\"content\":\"and many cars!!!\",\"updated\":\"2015-01-09 15:45:12.178\",\"created\":\"2015-01-09 15:45:12.178\",\"ID\":15},{\"content\":\"and many banks!!!\",\"updated\":\"2015-01-09 15:45:12.181\",\"created\":\"2015-01-09 15:45:12.181\",\"ID\":16},{\"content\":\"and many boats!!!\",\"updated\":\"2015-01-09 15:45:12.183\",\"created\":\"2015-01-09 15:45:12.183\",\"ID\":17},{\"content\":\"and many many many many dogs!!!\",\"updated\":\"2015-01-09 15:45:12.185\",\"created\":\"2015-01-09 15:45:12.185\",\"ID\":18}]");
            PatientSingleton.getInstance().setMedicalNotes("[{\"content\":\"I am the third note\",\"created\":\"3966-05-03 09:04:05.0\",\"ID\":4},{\"content\":\"Please pay the medical fee\",\"created\":\"5036-01-02 03:35:05.0\",\"ID\":5},{\"content\":\"NO FEE NO LIFE\",\"created\":\"3134-02-12 03:04:05.0\",\"ID\":6},{\"content\":\"FEE! FEE! FEE! FEE! FEE! FEE!\",\"created\":\"9554-02-12 03:04:05.0\",\"ID\":7},{\"content\":\"If you don't pay the fee today, I will ask someone to kill you tomorrow\",\"created\":\"11899-02-12 03:04:05.0\",\"ID\":8},{\"content\":\"good boy\",\"created\":\"11899-10-09 09:09:09.0\",\"ID\":9}]");
        } catch (JSONException je) {
            Toast.makeText(getApplicationContext(), "Storing static JSON in patient singleton failed.", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /** Called when the user clicks the Profile Panels button */
    public void goToProfilePanels(View view) {
        Intent intent = new Intent(this, ProfilePanelsActivity.class);
        startActivity(intent);
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
