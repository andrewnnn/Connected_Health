package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TextPreview extends QuickMenu {

    protected int textPreviewsPerPage = HelperSingleton.getInstance().getTextPreviewsPerPage();

    protected int pageNumber = -1;
    protected ArrayList<JSONObject> itemsForPreviews;
    protected PatientSingleton.ItemType itemType;
    protected JSONArray items;

    protected void textPreviewSetup(PatientSingleton.ItemType itemType) {
        this.itemType = itemType;
        PatientSingleton.getInstance().setCurrentItemType(itemType);

        // get items to preview on this page
        int firstItemIndex, lastItemIndex;
        if (getIntent().hasExtra("pageNumber")) {
            pageNumber = getIntent().getExtras().getInt("pageNumber");
            firstItemIndex = pageNumber * textPreviewsPerPage;
            lastItemIndex = pageNumber * textPreviewsPerPage + 2;
        } else {
            pageNumber = 0;
            firstItemIndex = 0;
            lastItemIndex = textPreviewsPerPage - 1;
        }

        try {
            switch (itemType) {
                case journalEntry:
                    itemsForPreviews = PatientSingleton.getInstance().getJournalEntries(firstItemIndex, lastItemIndex);
                    break;
                case measurement:
                    itemsForPreviews = PatientSingleton.getInstance().getMeasurementTypes(firstItemIndex, lastItemIndex);
                    break;
                case medicalNote:
                    itemsForPreviews = PatientSingleton.getInstance().getMedicalNotes(firstItemIndex, lastItemIndex);
                    break;
                case questionnaire:
                    itemsForPreviews = PatientSingleton.getInstance().getQuestionnaires(firstItemIndex, lastItemIndex);
                    break;
            }
        } catch (JSONException je) {
            System.out.println("Couldn't get items for previews: " + je);
        }

        // update items
        switch (itemType) {
            case journalEntry:
                HelperSingleton.getInstance().updateJournalEntries(this);
                items = PatientSingleton.getInstance().getJournalEntries();
                break;
            case measurement:
                HelperSingleton.getInstance().updateMeasurementTypes(this);
                items = PatientSingleton.getInstance().getMeasurementTypes();
                break;
            case medicalNote:
                HelperSingleton.getInstance().updateMedicalNotes(this);
                items = PatientSingleton.getInstance().getMedicalNotes();
                break;
            case questionnaire:
                HelperSingleton.getInstance().updateQuestionnaires(this);
                items = PatientSingleton.getInstance().getQuestionnaires();
                break;
        }

        removeUnusedNavButtons();

    }

    protected void removeUnusedNavButtons() {
        PatientSingleton ps = PatientSingleton.getInstance();

        if (pageNumber == 0) {   // first page
            Button newerButton = (Button) findViewById(R.id.left_nav_button);
            newerButton.setOnClickListener(null);
            newerButton.setVisibility(View.INVISIBLE);
            if (textPreviewsPerPage >= items.length()) {        // no pages after first page
                Button olderButton = (Button) findViewById(R.id.right_nav_button);
                olderButton.setOnClickListener(null);
                olderButton.setVisibility(View.INVISIBLE);
            }
        } else {    // after first page
            if ((pageNumber + 1) * textPreviewsPerPage >= items.length()) {
                Button olderButton = (Button) findViewById(R.id.right_nav_button);
                olderButton.setOnClickListener(null);
                olderButton.setVisibility(View.INVISIBLE);
            }
        }

        // remove create button unless we are viewing journal
        switch (itemType) {
            case journalEntry:
                break;
            default:
                removeCreateButton();
        }
    }

    private void removeCreateButton() {
        Button createButton = (Button) findViewById(R.id.new_button);
        createButton.setClickable(false);
        createButton.setVisibility(View.INVISIBLE);
    }

    public void goToSingleItemView(View view) {
        int itemPageOffset = -1;
        switch(view.getId()) {
            case R.id.preview0:
            case R.id.test0:
                itemPageOffset = 0;
                PatientSingleton.getInstance().setCurrentObject(itemsForPreviews.get(0));
                break;
            case R.id.preview1:
                itemPageOffset = 1;
                PatientSingleton.getInstance().setCurrentObject(itemsForPreviews.get(1));
                break;
            case R.id.preview2:
                itemPageOffset = 2;
                PatientSingleton.getInstance().setCurrentObject(itemsForPreviews.get(2));
                break;
        }

        Intent intent = new Intent(this, getSingleItemClass());
        intent.putExtra("itemIndex", pageNumber*textPreviewsPerPage + itemPageOffset);
        startActivity(intent);
    }

    public void goToPreviousPage(View view) {
        if (pageNumber > 0) {
            Intent intent = new Intent(this, getPreviewClass());
            intent.putExtra("pageNumber", pageNumber - 1);
            startActivity(intent);
        }
    }

    public void goToNextPage(View view) {
        if ((pageNumber+1)*textPreviewsPerPage <= items.length() - 1) {
            Intent intent = new Intent(this, getPreviewClass());
            intent.putExtra("pageNumber", pageNumber + 1);
            startActivity(intent);
        }
    }

    public void goToNewItem(View view) {
        Intent intent = new Intent(this, getSingleItemClass());
        intent.putExtra("edit", false);
        startActivity(intent);
    }

    private Class getPreviewClass() {
        switch (itemType) {
            case journalEntry:
                return JournalViewActivity.class;
            case measurement:
                return MeasurementViewActivity.class;
            case medicalNote:
                return MedicalNotesViewActivity.class;
            case questionnaire:
                return QuestionnaireViewActivity.class;
            default:
                return MainActivity.class;
        }
    }

    private Class getSingleItemClass() {
        switch (itemType) {
            case questionnaire:
                return QuestionViewActivity.class;
            default:
                return SingleItemViewActivity.class;
        }
    }
}
