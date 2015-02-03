package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;

public class TextPreview extends QuickMenu {

    protected void removeUnusedNavButtons() {
        PatientSingleton ps = PatientSingleton.getInstance();
        PatientSingleton.ItemType itemType = ps.getCurrentItemType();
        JSONArray items = null;
        switch (itemType) {
            case journalEntry:
                items = ps.getJournalEntries();
                break;
            case measurement:
                items = ps.getMeasurementTypes();
                removeCreateButton();
                break;
            case medicalNote:
                items = ps.getMedicalNotes();
                removeCreateButton();
                break;
            case questionnaire:
                items = ps.getQuestionnaires();
                removeCreateButton();
                break;
        }

        int previewsPerPage = HelperSingleton.getInstance().getTextPreviewsPerPage();
        int pageNumber;
        if (getIntent().hasExtra("pageNumber")) {
            pageNumber = getIntent().getExtras().getInt("pageNumber");
        } else {
            pageNumber = 0;
        }

        if (pageNumber == 0) {   // first page
            Button newerButton = (Button) findViewById(R.id.left_nav_button);
            newerButton.setOnClickListener(null);
            newerButton.setVisibility(View.INVISIBLE);
            if (previewsPerPage >= items.length()) {        // no pages after first page
                Button olderButton = (Button) findViewById(R.id.right_nav_button);
                olderButton.setOnClickListener(null);
                olderButton.setVisibility(View.INVISIBLE);
            }
        } else {    // after first page
            if ((pageNumber + 1) * previewsPerPage >= items.length()) {
                Button olderButton = (Button) findViewById(R.id.right_nav_button);
                olderButton.setOnClickListener(null);
                olderButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void removeCreateButton() {
        Button createButton = (Button) findViewById(R.id.new_button);
        createButton.setClickable(false);
        createButton.setVisibility(View.INVISIBLE);
    }

}