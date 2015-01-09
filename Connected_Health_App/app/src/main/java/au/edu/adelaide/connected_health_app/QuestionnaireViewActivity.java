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


public class QuestionnaireViewActivity extends ActionBarActivity {

    int questionnaireId = 10;
    String url = "http://129.127.210.69:9999/ConnectedHealth/questionnaire/get?IDorName=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_view);

        // Instantiate the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string JSON response from the Grails app.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // check if response is a valid JSON object
                        JSONObject questionnaireJson = null;
                        try {
                            questionnaireJson = new JSONObject(response);
                        } catch (JSONException je) {
                            System.out.println("Response was not a valid JSON object.");
                        }

                        try {
                            // Display title
                            TextView questionnaire_title = (TextView) findViewById(R.id.questionnaire_title);
                            questionnaire_title.setText(questionnaireJson.getString("name"));

                            // Get content of each question
                            StringBuilder sb = new StringBuilder();
                            JSONArray questionsJson = questionnaireJson.getJSONArray("questions");
                            for (int i = 0; i < questionsJson.length(); i++) {
                                JSONObject currentQuestion = questionsJson.getJSONObject(i);
                                String questionContent = currentQuestion.getString("content");
                                sb.append(questionContent + "\n");
                                int answerFormat = currentQuestion.getInt("answerFormat");
                                sb.append(answerFormat + "\n");

                                // Get content of each choice (if any)
                                JSONArray choicesJson = currentQuestion.getJSONArray("choices");
                                for (int j = 0; j < choicesJson.length(); j++) {
                                    JSONObject currentChoice = choicesJson.getJSONObject(i);
                                    String choiceContent = currentChoice.getString("content");
                                    sb.append(choiceContent + "\n");
                                }
                            }

                            // Display questions and choices (TODO use forms for POST submission instead of a text view)
                            TextView questionnaire_questions = (TextView) findViewById(R.id.questionnaire_questions);
                            questionnaire_questions.setText(sb.toString());
                        } catch (JSONException je) {
                            System.out.println("Couldn't parse questionnaire JSON object.");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Volley HTTP request for questionnaire failed.");
            }
        });
        // Add the request to the RequestQueue for asynchronous handling.
        queue.add(stringRequest);

        final String[] staticJournalEntries={"The first journal entry.\nWith two lines.","The second journal entry.","The third journal entry.","The fourth journal entry."};
        final RelativeLayout staticJournalEntriesLayout=(RelativeLayout) findViewById(R.id.staticJournalEntryLayout);
        ArrayList<TextView> staticEntryTextViews = new ArrayList<TextView>();
        ArrayList<Button> staticEntryEditButtons = new ArrayList<Button>();
        ArrayList<Button> staticEntryDeleteButtons = new ArrayList<Button>();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_questionnaire_view, menu);
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
        //already on view disable
//        Intent intent = new Intent(this, QuestionnaireViewActivity.class);
//        startActivity(intent);
    }

    /** Called when the user clicks the Profile View button */
    public void goToProfileView(View view) {
        Intent intent = new Intent(this, ProfileViewActivity.class);
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
