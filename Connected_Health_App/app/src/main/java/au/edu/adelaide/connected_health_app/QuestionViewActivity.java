package au.edu.adelaide.connected_health_app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class QuestionViewActivity extends ActionBarActivity {

    final int ANSWER_FORMAT_RADIOBUTTON = 0;
    final int ANSWER_FORMAT_CHECKBOX = 1;
    final int ANSWER_FORMAT_TEXT = 2;
    static int viewId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_single_object_view);

        int questionNumber = getIntent().getExtras().getInt("questionNumber");
        RelativeLayout main_layout = (RelativeLayout) findViewById(R.id.main_layout);
        TextView questionText = new TextView(this);

        try {
            JSONObject questionnaire = PatientSingleton.getInstance().getCurrentObject();
            JSONArray questions = questionnaire.getJSONArray("questions");
            JSONObject currentQuestionJson = questions.getJSONObject(questionNumber);

            if (questionNumber == questions.length() - 1) {
                // TODO last question, submit answers
            }

            questionText.setText(currentQuestionJson.getString("content"));

            questionText.setId(++viewId);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                    ((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int) RelativeLayout.LayoutParams.WRAP_CONTENT);
            questionText.setLayoutParams(params);
            questionText.setPadding(10, 10, 10, 0);
            questionText.setTextSize((float) 35);
            main_layout.addView(questionText);

            JSONArray choicesJson = currentQuestionJson.getJSONArray("choices");

            switch (currentQuestionJson.getInt("answerFormat")) {
                case ANSWER_FORMAT_CHECKBOX:
                    CheckBox recentCheckBox = null;
                    for (int i = 0; i < choicesJson.length(); i++) {
                        JSONObject currentChoiceJson = choicesJson.getJSONObject(i);
                        CheckBox currentCheckBox = new CheckBox(this);
                        RelativeLayout.LayoutParams checkBoxParams = new RelativeLayout.LayoutParams
                                ((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int) RelativeLayout.LayoutParams.WRAP_CONTENT);
                        if (i == 0) {       // put first option below question
                            checkBoxParams.addRule(RelativeLayout.BELOW, questionText.getId());
                        } else {
                            checkBoxParams.addRule(RelativeLayout.BELOW, recentCheckBox.getId());
                        }
                        currentCheckBox.setId(++viewId);
                        currentCheckBox.setLayoutParams(checkBoxParams);
                        currentCheckBox.setPadding(10, 10, 10, 0);
                        currentCheckBox.setText(currentChoiceJson.getString("content"));
                        currentCheckBox.setTextSize((float) 25);
                        main_layout.addView(currentCheckBox);
                        recentCheckBox = currentCheckBox;
                    }
                    break;

                case ANSWER_FORMAT_RADIOBUTTON:
                    RadioButton recentRadioButton = null;
                    for (int i = 0; i < choicesJson.length(); i++) {
                        JSONObject currentChoiceJson = choicesJson.getJSONObject(i);
                        RadioButton currentRadioButton = new RadioButton(this);
                        RelativeLayout.LayoutParams radioButtonParams = new RelativeLayout.LayoutParams
                                ((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int) RelativeLayout.LayoutParams.WRAP_CONTENT);
                        if (i == 0) {       // put first option below question
                            radioButtonParams.addRule(RelativeLayout.BELOW, questionText.getId());
                        } else {
                            radioButtonParams.addRule(RelativeLayout.BELOW, recentRadioButton.getId());
                        }
                        currentRadioButton.setId(++viewId);
                        currentRadioButton.setLayoutParams(radioButtonParams);
                        currentRadioButton.setPadding(10, 10, 10, 0);
                        currentRadioButton.setText(currentChoiceJson.getString("content"));
                        currentRadioButton.setTextSize((float) 25);
                        main_layout.addView(currentRadioButton);
                        recentRadioButton = currentRadioButton;
                    }
                    break;

                case ANSWER_FORMAT_TEXT:
                    EditText currentEditText = new EditText(this);
                    RelativeLayout.LayoutParams editTextParams = new RelativeLayout.LayoutParams
                            ((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int) RelativeLayout.LayoutParams.WRAP_CONTENT);
                    editTextParams.addRule(RelativeLayout.BELOW, questionText.getId());
                    currentEditText.setId(++viewId);
                    currentEditText.setLayoutParams(editTextParams);
                    currentEditText.setPadding(10, 10, 10, 0);
                    currentEditText.setTextSize((float) 25);
                    main_layout.addView(currentEditText);
                    break;
            }
        } catch (JSONException je) {
            System.out.println("Getting question from JSON failed.");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question_view, menu);
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
}
