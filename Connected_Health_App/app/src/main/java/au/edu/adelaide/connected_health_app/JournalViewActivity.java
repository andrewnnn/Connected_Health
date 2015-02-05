package au.edu.adelaide.connected_health_app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;


public class JournalViewActivity extends TextPreview {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_text_preview_view);

        textPreviewSetup(PatientSingleton.ItemType.journalEntry);

        try {
            int i;

            // for each preview, set background colour to match home panel and set preview text
            for (i = 0; i < itemsForPreviews.size(); i++){
                String created = itemsForPreviews.get(i).getString("created");
                String content = itemsForPreviews.get(i).getString("content");
                String preview = created + "\n\n" + content;

                int resID = getResources().getIdentifier("preview_text" + i,
                        "id", getPackageName());
                TextView previewText = (TextView) findViewById(resID);
                previewText.setText(preview);
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
            System.out.println("getting journal entries failed");
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
