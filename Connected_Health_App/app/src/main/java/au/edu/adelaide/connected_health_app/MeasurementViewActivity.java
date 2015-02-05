package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MeasurementViewActivity extends TextPreview {

    private final int patientID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_text_preview_view);

        textPreviewSetup(PatientSingleton.ItemType.measurement);

        try {
            // for each preview, set background colour to match home panel and set preview text
            int i;
            for (i = 0; i < itemsForPreviews.size(); i++){
                String name = itemsForPreviews.get(i).getString("name");
                String description = itemsForPreviews.get(i).getString("description");
                String preview = name + "\n\n" + description;

                int resID = getResources().getIdentifier("preview_text" + i,
                        "id", getPackageName());
                TextView previewText = (TextView) findViewById(resID);
                previewText.setText(preview);

                resID = getResources().getIdentifier("preview" + i,
                        "id", getPackageName());
                RelativeLayout previewLayout = (RelativeLayout) findViewById(resID);
                previewLayout.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

                // Set category icon according to panel
                resID = getResources().getIdentifier("icon" + i,
                        "id", getPackageName());
                ImageView icon = (ImageView) findViewById(resID);

                if (name.compareTo("Steps") == 0) {
                    icon.setImageResource(R.drawable.steps_icon_512);
                }
                if (name.compareTo("Weight") == 0) {
                    icon.setImageResource(R.drawable.weight_icon_512);
                }
            }

            // if there are less preview items than preview spaces, remove colour/click listener for unused preview panels
            for (; i < textPreviewsPerPage; i++) {
                int resID = getResources().getIdentifier("preview" + i,
                        "id", getPackageName());
                RelativeLayout previewLayout = (RelativeLayout) findViewById(resID);
                previewLayout.setOnClickListener(null);
                previewLayout.setBackgroundColor(0x00000000);       // transparent background
            }
        } catch (JSONException je) {
            System.out.println("getting measurement types failed");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_journal_view, menu);
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
