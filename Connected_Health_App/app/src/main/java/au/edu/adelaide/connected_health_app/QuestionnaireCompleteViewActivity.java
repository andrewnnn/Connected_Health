package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class QuestionnaireCompleteViewActivity extends QuickMenu {

    JSONObject submissionJson = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_complete_view);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_questionnaire_complete_view, menu);
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

    public void goBackToQuestions(View view) {
        finish();
    }

    public void goToSubmit(View view) {
        // build JSON submission from each JSON answer
        JSONArray answers = new JSONArray(PatientSingleton.getInstance().getQuestionnaireAnswers());

        try {
            submissionJson.put("answers", answers);
            submissionJson.put("questionnaireId", PatientSingleton.getInstance().getCurrentQuestionnaireId());
        } catch (JSONException je) {
            System.out.println(je);
        }

        httpPost();

        System.out.println("===================================");
        System.out.println(submissionJson.toString());
        System.out.println("===================================");

        Intent intent = new Intent(this, QuestionnaireViewActivity.class);
        startActivity(intent);
    }

    public void httpPost() {
        String patientId = HelperSingleton.getInstance().getPatientId() + "";
        String path = "patients/" + patientId + "/submissions/json";
        String url = HelperSingleton.getInstance().getConstantUrl() + path;

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("submission", submissionJson.toString());

                return params;
            }
        };
        queue.add(postRequest);
    }

}
