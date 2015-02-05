package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class QuestionnaireViewActivity extends TextPreview {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_text_preview_view);

        textPreviewSetup(PatientSingleton.ItemType.questionnaire);
/*
        try {
            int i;

            // for each preview, set background colour to match home panel and set preview text
            for (i = 0; i < itemsForPreviews.size(); i++){
                JSONObject questionnaire = itemsForPreviews.get(i);
                String name = questionnaire.getString("name");
                String description = questionnaire.getString("description");
                StringBuilder preview = new StringBuilder();
                preview.append(name);
                preview.append("\n\n");
                preview.append(description);

                int resID = getResources().getIdentifier("preview_text" + i,
                        "id", getPackageName());
                TextView previewText = (TextView) findViewById(resID);
                previewText.setText(preview.toString());

                resID = getResources().getIdentifier("preview" + i,
                        "id", getPackageName());
                RelativeLayout previewLayout = (RelativeLayout) findViewById(resID);
                previewLayout.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
            }

            // if there are less preview items than preview spaces, remove colour/click listener for unused preview panels
            for (; i < textPreviewsPerPage; i++) {
                int resID = getResources().getIdentifier("preview" + i,
                        "id", getPackageName());
                RelativeLayout previewLayout = (RelativeLayout) findViewById(resID);
                previewLayout.setOnClickListener(null);
                previewLayout.setBackgroundColor(0x00000000);       // transparent background
                resID = getResources().getIdentifier("preview_contents" + i,
                        "id", getPackageName());
                RelativeLayout previewContents = (RelativeLayout) findViewById(resID);
                previewContents.setOnClickListener(null);
                previewContents.setVisibility(View.INVISIBLE);       // transparent background
            }
        } catch (JSONException je) {
            System.out.println("getting questionnaires failed");
        }
*/

        setPreviewContent();
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

}
