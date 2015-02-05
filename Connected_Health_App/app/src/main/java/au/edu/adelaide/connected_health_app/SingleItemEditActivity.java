package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class SingleItemEditActivity extends QuickMenu {

    int itemIndex = -1;
    int itemId = -1;
    EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PatientSingleton.getInstance().getCurrentItemType() == PatientSingleton.ItemType.journalEntry) {
            setContentView(R.layout.activity_single_item_edit);
        } else {
            setContentView(R.layout.activity_single_item_edit);
        }
        content = (EditText) findViewById(R.id.editText);

        RelativeLayout main_layout = (RelativeLayout) findViewById(R.id.main_layout);
        try {
            JSONObject object = PatientSingleton.getInstance().getCurrentObject();
            if (getIntent().getExtras().getBoolean("edit")) {
                content.setText(object.getString("content"));
            }

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                    ((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int) RelativeLayout.LayoutParams.WRAP_CONTENT);
            content.setLayoutParams(params);
            content.setPadding(10, 10, 10, 0);
            content.setTextSize((float) 20);
        } catch (JSONException je) {
            System.out.println("Couldn't get current JSON object to display in single item view.");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_item_view, menu);
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

    public void goToNextItem(View view) throws JSONException {
        PatientSingleton ps = PatientSingleton.getInstance();
        JSONArray currentArray = ps.getCurrentArray();
        if (itemIndex < currentArray.length() - 1) {
            Intent intent = new Intent(this, SingleItemEditActivity.class);
            itemIndex++;
            intent.putExtra("itemIndex", itemIndex);
            ps.setCurrentObject(currentArray.getJSONObject(itemIndex));
            startActivity(intent);
        }
    }

    public void goToPreviousItem(View view) throws JSONException {
        PatientSingleton ps = PatientSingleton.getInstance();
        JSONArray currentArray = ps.getCurrentArray();
        if (itemIndex > 0) {
            Intent intent = new Intent(this, SingleItemEditActivity.class);
            itemIndex--;
            intent.putExtra("itemIndex", itemIndex);
            ps.setCurrentObject(currentArray.getJSONObject(itemIndex));
            startActivity(intent);
        }
    }

    public void goToCancelEdit(View view) {
        finish();
    }

    // can use getItemType to update different models
    public void goToConfirmEdit(View view) {
        if (getIntent().getExtras().getBoolean("edit")) {       // edit existing item
            try {
                JSONObject journalEntry = PatientSingleton.getInstance().getCurrentObject();
                itemId = journalEntry.getInt("ID");
                httpPut();
            } catch (JSONException je) {
                System.out.println("Couldn't get journal entry ID to update");
            }
        } else {        // create new item
            httpPost();
        }

        PatientSingleton.getInstance().getJournalEntries();     // update local journal entries after change TODO check that this works
        Intent intent = new Intent(this, JournalViewActivity.class);
        startActivity(intent);
    }

    public void httpPost() {
        String patientId = HelperSingleton.getInstance().getPatientId() + "";
        String path = "patients/" + patientId + "/journal/json";
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
                params.put("content", content.getText().toString());
                params.put("patientID", HelperSingleton.getInstance().getPatientId() + "");

                return params;
            }
        };
        queue.add(postRequest);
    }

    public void httpPut() {
        String url = null;
        try {
            String patientId = HelperSingleton.getInstance().getPatientId() + "";
            String path = "patients/" + patientId + "/journal/" + itemId + "/update/json?content=" + URLEncoder.encode(content.getText().toString(), "UTF-8");
            url = HelperSingleton.getInstance().getConstantUrl() + path;
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
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
                params.put("content", content.getText().toString());
                params.put("patientID", HelperSingleton.getInstance().getPatientId() + "");
                params.put("journalEntryID", itemId + "");

                return params;
            }
        };
        queue.add(putRequest);
    }

}
