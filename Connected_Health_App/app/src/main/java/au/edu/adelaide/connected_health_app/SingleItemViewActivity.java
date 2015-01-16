package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SingleItemViewActivity extends QuickMenu {

    int itemIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PatientSingleton.getInstance().getCurrentItemType() == PatientSingleton.ItemType.journalEntry) {
            setContentView(R.layout.generic_single_object_4buttons_view);
        } else {
            setContentView(R.layout.generic_single_object_view);
        }
        itemIndex = getIntent().getExtras().getInt("itemIndex");

        RelativeLayout main_layout = (RelativeLayout) findViewById(R.id.main_layout);
        TextView content = new TextView(this);
        try {
            JSONObject object = PatientSingleton.getInstance().getCurrentObject();
            content.setText(object.getString("created") + "\n" + object.getString("content"));

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                    ((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int) RelativeLayout.LayoutParams.WRAP_CONTENT);
            content.setLayoutParams(params);
            content.setPadding(10, 10, 10, 0);
            content.setTextSize((float) 20);
            main_layout.addView(content);
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
            Intent intent = new Intent(this, SingleItemViewActivity.class);
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
            Intent intent = new Intent(this, SingleItemViewActivity.class);
            itemIndex--;
            intent.putExtra("itemIndex", itemIndex);
            ps.setCurrentObject(currentArray.getJSONObject(itemIndex));
            startActivity(intent);
        }
    }

}
