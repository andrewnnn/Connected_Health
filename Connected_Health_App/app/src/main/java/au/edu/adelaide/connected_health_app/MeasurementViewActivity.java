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
    private ArrayList<JSONObject> measurementTypesForPreviews;
    private int pageNumber = -1;
    private final int textPreviewsPerPage = HelperSingleton.getInstance().getTextPreviewsPerPage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_text_preview_view);

        PatientSingleton.getInstance().setCurrentItemType(PatientSingleton.ItemType.measurement);
        removeUnusedNavButtons();

        try {
            if (getIntent().hasExtra("pageNumber")) {
                pageNumber = getIntent().getExtras().getInt("pageNumber");
                measurementTypesForPreviews = PatientSingleton.getInstance().getMeasurementTypes(pageNumber*textPreviewsPerPage,pageNumber*textPreviewsPerPage + 2);
            } else {
                measurementTypesForPreviews = PatientSingleton.getInstance().getMeasurementTypes(0,2);
                pageNumber = 0;
            }

            // for each preview, set background colour to match home panel and set preview text
            int i;
            for (i = 0; i < measurementTypesForPreviews.size(); i++){
                String name = measurementTypesForPreviews.get(i).getString("name");
                String description = measurementTypesForPreviews.get(i).getString("description");
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

    public void goToSingleItemView(View view) {
        int itemPageOffset = -1;
        switch(view.getId()) {
            case R.id.preview0:
                PatientSingleton.getInstance().setCurrentObject(measurementTypesForPreviews.get(0));
                itemPageOffset = 0;
                break;
            case R.id.preview1:
                PatientSingleton.getInstance().setCurrentObject(measurementTypesForPreviews.get(1));
                itemPageOffset = 1;
                break;
            case R.id.preview2:
                PatientSingleton.getInstance().setCurrentObject(measurementTypesForPreviews.get(2));
                itemPageOffset = 2;
                break;
        }

        Intent intent = new Intent(this, HelperSingleton.getInstance().getMeasurementTypeClass());
        intent.putExtra("itemIndex",pageNumber*textPreviewsPerPage + itemPageOffset);
        startActivity(intent);
    }

    public void goToPreviousPage(View view) {
        if (pageNumber > 0) {
            Intent intent = new Intent(this, MeasurementViewActivity.class);
            intent.putExtra("pageNumber", pageNumber - 1);
            startActivity(intent);
        }
    }

    public void goToNextPage(View view) {
        if ((pageNumber+1)*textPreviewsPerPage <= PatientSingleton.getInstance().getJournalEntries().length() - 1) {
            Intent intent = new Intent(this, MeasurementViewActivity.class);
            intent.putExtra("pageNumber", pageNumber + 1);
            startActivity(intent);
        }
    }

}
