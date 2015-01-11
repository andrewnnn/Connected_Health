package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
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



public class QuestionnaireViewActivity extends ActionBarActivity {

    final int questionnaireId = 10;
    final String url = "http://129.127.210.69:9999/ConnectedHealth/questionnaire/get?IDorName=10";
    final String staticJsonQuestionnaire = "{\"id\":29,\"name\":\"AnotherQuestoinnaire\",\"questions\":[{\"content\":\"q7\",\"id\":53,\"choices\":[{\"content\":\"choice5 for q7\",\"id\":58},{\"content\":\"choice3 for q7\",\"id\":56},{\"content\":\"choice1 for q7\",\"id\":54},{\"content\":\"choice4 for q7\",\"id\":57},{\"content\":\"choice2 for q7\",\"id\":55}],\"answerFormat\":0},{\"content\":\"q4\",\"id\":30,\"choices\":[{\"content\":\"choice2 for q4\",\"id\":32},{\"content\":\"choice10 for q4\",\"id\":40},{\"content\":\"choice7 for q4\",\"id\":37},{\"content\":\"choice6 for q4\",\"id\":36},{\"content\":\"choice4 for q4\",\"id\":34},{\"content\":\"choice3 for q4\",\"id\":33},{\"content\":\"choice9 for q4\",\"id\":39},{\"content\":\"choice1 for q4\",\"id\":31},{\"content\":\"choice8 for q4\",\"id\":38},{\"content\":\"choice5 for q4\",\"id\":35}],\"answerFormat\":1},{\"content\":\"q6\",\"id\":47,\"choices\":[{\"content\":\"choice2 for q6\",\"id\":49},{\"content\":\"choice3 for q6\",\"id\":50},{\"content\":\"choice4 for q6\",\"id\":51},{\"content\":\"choice1 for q6\",\"id\":48},{\"content\":\"choice5 for q6\",\"id\":52}],\"answerFormat\":0},{\"content\":\"q5\",\"id\":41,\"choices\":[{\"content\":\"choice1 for q5\",\"id\":42},{\"content\":\"choice4 for q5\",\"id\":45},{\"content\":\"choice5 for q5\",\"id\":46},{\"content\":\"choice3 for q5\",\"id\":44},{\"content\":\"choice2 for q5\",\"id\":43}],\"answerFormat\":0}]}";
    final int ANSWER_FORMAT_RADIOBUTTON = 0;
    final int ANSWER_FORMAT_CHECKBOX = 1;
    final int ANSWER_FORMAT_TEXT = 2;
    static int viewId = 1;      // get unique ID for views

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

        try {
            generateQuestionnaire(staticJsonQuestionnaire);
        } catch (JSONException je) {
            System.out.println("JSON parsing for questionnaire failed.");
        }
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

    private void generateQuestionnaire(String jsonString) throws JSONException {
        JSONObject questionnaireJson = new JSONObject(jsonString);
        JSONArray questionsJson = questionnaireJson.getJSONArray("questions");
        final RelativeLayout questionnaireLayout = (RelativeLayout) findViewById(R.id.questionnaire_layout);

        // for each journal entry, display the entry content in a TextView, with edit and delete Buttons below it
        // assign a unique ID to each TextView/Button, so they can be arranged in a RelativeLayout
        CheckBox recentCheckBox = null;
        RadioButton recentRadioButton = null;
        EditText recentEditText = null;
        int recentAnswerFormat = -1;
        for (int i = 0; i < questionsJson.length(); i++) {
            TextView currentQuestion = new TextView(this);
            JSONObject currentQuestionJson = questionsJson.getJSONObject(i);
            RelativeLayout.LayoutParams questionTextViewParams = new RelativeLayout.LayoutParams
                    ((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int) RelativeLayout.LayoutParams.WRAP_CONTENT);
            if (i > 0) {    // put entry below the buttons for the previous entry (excluding 1st question)
                int idOfAboveView = -1;
                switch (recentAnswerFormat) {
                    case ANSWER_FORMAT_CHECKBOX:
                        idOfAboveView = recentCheckBox.getId();
                        break;
                    case ANSWER_FORMAT_RADIOBUTTON:
                        idOfAboveView = recentRadioButton.getId();
                        break;
                    case ANSWER_FORMAT_TEXT:
                        idOfAboveView = recentEditText.getId();
                        break;
                }
                questionTextViewParams.addRule(RelativeLayout.BELOW, idOfAboveView);
            }
            currentQuestion.setId(++viewId);
            currentQuestion.setLayoutParams(questionTextViewParams);
            currentQuestion.setPadding(10, 10, 10, 0);
            currentQuestion.setText(currentQuestionJson.getString("content"));
            currentQuestion.setTextSize((float) 30);
            questionnaireLayout.addView(currentQuestion);

            JSONArray choicesJson = currentQuestionJson.getJSONArray("choices");

            switch (currentQuestionJson.getInt("answerFormat")) {
                case ANSWER_FORMAT_CHECKBOX:
                    for (int j = 0; j < choicesJson.length(); j++) {
                        JSONObject currentChoiceJson = choicesJson.getJSONObject(i);
                        CheckBox currentCheckBox = new CheckBox(this);
                        RelativeLayout.LayoutParams checkBoxParams = new RelativeLayout.LayoutParams
                                ((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int) RelativeLayout.LayoutParams.WRAP_CONTENT);
                        if (j == 0) {       // put first option below question
                            checkBoxParams.addRule(RelativeLayout.BELOW, currentQuestion.getId());
                        } else {
                            checkBoxParams.addRule(RelativeLayout.BELOW, recentCheckBox.getId());
                        }
                        currentCheckBox.setId(++viewId);
                        currentCheckBox.setLayoutParams(checkBoxParams);
                        currentCheckBox.setPadding(10, 10, 10, 0);
                        currentCheckBox.setText(currentChoiceJson.getString("content"));
                        currentCheckBox.setTextSize((float) 20);
                        questionnaireLayout.addView(currentCheckBox);
                        recentCheckBox = currentCheckBox;
                    }

                    recentAnswerFormat = ANSWER_FORMAT_CHECKBOX;
                    break;

                case ANSWER_FORMAT_RADIOBUTTON:
                    for (int j = 0; j < choicesJson.length(); j++) {
                        JSONObject currentChoiceJson = choicesJson.getJSONObject(i);
                        RadioButton currentRadioButton = new RadioButton(this);
                        RelativeLayout.LayoutParams radioButtonParams = new RelativeLayout.LayoutParams
                                ((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int) RelativeLayout.LayoutParams.WRAP_CONTENT);
                        if (j == 0) {       // put first option below question
                            radioButtonParams.addRule(RelativeLayout.BELOW, currentQuestion.getId());
                        } else {
                            radioButtonParams.addRule(RelativeLayout.BELOW, recentRadioButton.getId());
                        }
                        currentRadioButton.setId(++viewId);
                        currentRadioButton.setLayoutParams(radioButtonParams);
                        currentRadioButton.setPadding(10, 10, 10, 0);
                        currentRadioButton.setText(currentChoiceJson.getString("content"));
                        currentRadioButton.setTextSize((float) 20);
                        questionnaireLayout.addView(currentRadioButton);
                        recentRadioButton = currentRadioButton;
                    }

                    recentAnswerFormat = ANSWER_FORMAT_RADIOBUTTON;
                    break;

                case ANSWER_FORMAT_TEXT:
                    EditText currentEditText = new EditText(this);
                    RelativeLayout.LayoutParams editTextParams = new RelativeLayout.LayoutParams
                            ((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int) RelativeLayout.LayoutParams.WRAP_CONTENT);
                    editTextParams.addRule(RelativeLayout.BELOW, currentQuestion.getId());
                    currentEditText.setId(++viewId);
                    currentEditText.setLayoutParams(editTextParams);
                    currentEditText.setPadding(10, 10, 10, 0);
                    currentEditText.setTextSize((float) 20);
                    questionnaireLayout.addView(currentEditText);
                    recentEditText = currentEditText;

                    recentAnswerFormat = ANSWER_FORMAT_TEXT;
                    break;
            }

        }
    }
}
