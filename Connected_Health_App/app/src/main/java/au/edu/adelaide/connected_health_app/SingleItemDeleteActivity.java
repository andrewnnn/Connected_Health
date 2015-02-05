package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;


public class SingleItemDeleteActivity extends QuickMenu {

    int itemId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PatientSingleton.getInstance().getCurrentItemType() == PatientSingleton.ItemType.journalEntry) {
            setContentView(R.layout.activity_single_item_delete);
        } else {
            setContentView(R.layout.activity_single_item_delete);
        }

        TextView content = (TextView) findViewById(R.id.content_text);
        try {
            content.setText(PatientSingleton.getInstance().getCurrentObject().getString("content"));
        } catch (JSONException je) {
            System.out.println(je);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_item_delete, menu);
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

    public void goToCancelDelete(View view) { finish(); }

    public void goToConfirmDelete(View view) {
        String patientId = HelperSingleton.getInstance().getPatientId() + "";
        try {
            itemId = PatientSingleton.getInstance().getCurrentObject().getInt("ID");
        } catch (JSONException je) {
            System.out.println(je);
        }
        String path = "patients/" + patientId + "/journal/" + itemId + "/update/json";
        String url = HelperSingleton.getInstance().getConstantUrl() + path;

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, url,
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

                return params;
            }
        };
        queue.add(deleteRequest);
        HelperSingleton.getInstance().updateJournalEntries(this);

        Intent intent = new Intent(this, JournalViewActivity.class);
        startActivity(intent);
    }

}
