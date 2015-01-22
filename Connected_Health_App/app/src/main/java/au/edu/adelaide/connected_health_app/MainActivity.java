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

        // Set static data in patient singleton
        try {
            PatientSingleton ps = PatientSingleton.getInstance();
            ps.addQuestionnaire("{\"id\":19,\"name\":\"StandardQuestionnaire\",\"questions\":[{\"content\":\"q3\",\"id\":22,\"choices\":[],\"answerFormat\":2},{\"content\":\"q1\",\"id\":20,\"choices\":[{\"content\":\"choice2 for q1\",\"id\":24},{\"content\":\"choice1 for q1\",\"id\":23},{\"content\":\"choice3 for q1\",\"id\":25}],\"answerFormat\":0},{\"content\":\"q2\",\"id\":21,\"choices\":[{\"content\":\"choice2 for q2\",\"id\":27},{\"content\":\"choice3 for q2\",\"id\":28},{\"content\":\"choice1 for q2\",\"id\":26}],\"answerFormat\":1}]}");
            ps.addQuestionnaire("{\"id\":29,\"name\":\"AnotherQuestionnaire\",\"questions\":[{\"content\":\"q7\",\"id\":53,\"choices\":[{\"content\":\"choice5 for q7\",\"id\":58},{\"content\":\"choice3 for q7\",\"id\":56},{\"content\":\"choice1 for q7\",\"id\":54},{\"content\":\"choice4 for q7\",\"id\":57},{\"content\":\"choice2 for q7\",\"id\":55}],\"answerFormat\":0},{\"content\":\"q4\",\"id\":30,\"choices\":[{\"content\":\"choice2 for q4\",\"id\":32},{\"content\":\"choice10 for q4\",\"id\":40},{\"content\":\"choice7 for q4\",\"id\":37},{\"content\":\"choice6 for q4\",\"id\":36},{\"content\":\"choice4 for q4\",\"id\":34},{\"content\":\"choice3 for q4\",\"id\":33},{\"content\":\"choice9 for q4\",\"id\":39},{\"content\":\"choice1 for q4\",\"id\":31},{\"content\":\"choice8 for q4\",\"id\":38},{\"content\":\"choice5 for q4\",\"id\":35}],\"answerFormat\":1},{\"content\":\"q6\",\"id\":47,\"choices\":[{\"content\":\"choice2 for q6\",\"id\":49},{\"content\":\"choice3 for q6\",\"id\":50},{\"content\":\"choice4 for q6\",\"id\":51},{\"content\":\"choice1 for q6\",\"id\":48},{\"content\":\"choice5 for q6\",\"id\":52}],\"answerFormat\":0},{\"content\":\"q5\",\"id\":41,\"choices\":[{\"content\":\"choice1 for q5\",\"id\":42},{\"content\":\"choice4 for q5\",\"id\":45},{\"content\":\"choice5 for q5\",\"id\":46},{\"content\":\"choice3 for q5\",\"id\":44},{\"content\":\"choice2 for q5\",\"id\":43}],\"answerFormat\":0}]}");
            ps.setJournalEntries("[{\"content\":\"Today was a good day, I reached a new weekly record for steps taken.\",\"updated\":\"2015-01-09 15:45:12.177\",\"created\":\"2015-01-09 15:45:12.177\",\"ID\":14},{\"content\":\"I can already see the difference in weight from striving to one up my previous steps from the day before.\",\"updated\":\"2015-01-09 15:45:12.178\",\"created\":\"2015-01-09 15:45:12.178\",\"ID\":15},{\"content\":\"After a few weeks, I am really getting the hang of this system and my carers are also seeing improvements. I am really happy!\",\"updated\":\"2015-01-09 15:45:12.181\",\"created\":\"2015-01-09 15:45:12.181\",\"ID\":16},{\"content\":\"My mood has been better in recent weeks. I think this can be attributed to the clean diet and daily exercise, which I'm really enjoying.\",\"updated\":\"2015-01-09 15:45:12.183\",\"created\":\"2015-01-09 15:45:12.183\",\"ID\":17},{\"content\":\"Yesterday I received my first remote check up from a nurse. It was nice to have an expert be able to check up and discuss about my current health.\",\"updated\":\"2015-01-09 15:45:12.185\",\"created\":\"2015-01-09 15:45:12.185\",\"ID\":18}]");
            ps.setMedicalNotes("[{\"content\":\"Please sleep earlier.\",\"created\":\"3966-05-03 09:04:05.0\",\"ID\":4},{\"content\":\"Advise you to stick to 3-4 small meals a day to keep weight levels stable.\",\"created\":\"5036-01-02 03:35:05.0\",\"ID\":5},{\"content\":\"Remember to take medicine in the morning, afternoon and at night. After meals are recommended.\",\"created\":\"3134-02-12 03:04:05.0\",\"ID\":6},{\"content\":\"Advise you to fit in a minimum of 30 mins of physical exercise per day.\",\"created\":\"9554-02-12 03:04:05.0\",\"ID\":7},{\"content\":\"Make sure you have a decent stretch before commencing physical exercise.\",\"created\":\"11899-02-12 03:04:05.0\",\"ID\":8},{\"content\":\"Your weight has seen an increase in recent weeks, please stick to the diet prescribed or come into the clinic for more consultation.\",\"created\":\"11899-10-09 09:09:09.0\",\"ID\":9}]");
            ps.setMeasurementTypes("[{\"name\":\"Steps\",\"description\":\"The number of steps taken in the last 7 days.\"},{\"name\":\"Weight\",\"description\":\"Measurements from your weighing scale from the last 7 days.\"}]\n");
        } catch (JSONException je) {
            Toast.makeText(getApplicationContext(), "Storing static JSON in patient singleton failed.", Toast.LENGTH_LONG).show();
        }

        // Set Grails IP address and port
        HelperSingleton hs = HelperSingleton.getInstance();
        hs.setIpAddress("129.127.250.78");
        hs.setPort("8080");
        hs.setPatientId(3);

        hs.updateJournalEntries(this);
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
