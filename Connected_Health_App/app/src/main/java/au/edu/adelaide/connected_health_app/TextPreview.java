package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

        setPreviewContent();
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
            case R.id.preview_contents0:
                itemPageOffset = 0;
                PatientSingleton.getInstance().setCurrentObject(itemsForPreviews.get(0));
                break;
            case R.id.preview1:
            case R.id.preview_contents1:
                if (itemType == PatientSingleton.ItemType.questionnaire) {
                    itemPageOffset = 0;
                } else {
                    itemPageOffset = 1;
                }
                PatientSingleton.getInstance().setCurrentObject(itemsForPreviews.get(1));
                break;
            case R.id.preview2:
            case R.id.preview_contents2:
                if (itemType == PatientSingleton.ItemType.questionnaire) {
                    itemPageOffset = 0;
                } else {
                    itemPageOffset = 2;
                }
                PatientSingleton.getInstance().setCurrentObject(itemsForPreviews.get(2));
                break;
        }

        if (itemType == PatientSingleton.ItemType.questionnaire) {
            try {
                PatientSingleton.getInstance().setCurrentQuestionnaire(PatientSingleton.getInstance().getCurrentObject());
            } catch (JSONException je) {
                System.out.println(je);
            }
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
        Intent intent = new Intent(this, SingleItemEditActivity.class);
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
            case measurement:
                return MeasurementStepsViewActivity.class;
            case questionnaire:
                return QuestionViewActivity.class;
            default:
                return SingleItemViewActivity.class;
        }
    }

//    public void setPreviewContent(int iconId, int tintColour, String heading, String content, int borderColour) {
    public void setPreviewContent() {
        try {
            // for each preview, set background colour to match home panel and set preview text
            int i;
            for (i = 0; i < itemsForPreviews.size(); i++){
                String headingText = null;
                String contentText = null;
                switch (itemType) {
                    case journalEntry:
                        headingText = itemsForPreviews.get(i).getString("created");
                        contentText = itemsForPreviews.get(i).getString("content");
                        break;
                    case medicalNote:
                        headingText = itemsForPreviews.get(i).getString("created");
                        contentText = itemsForPreviews.get(i).getString("content");
                        break;
                    case measurement:
                        headingText = itemsForPreviews.get(i).getString("name");
                        contentText = itemsForPreviews.get(i).getString("description");
                        break;
                    case questionnaire:
                        headingText = itemsForPreviews.get(i).getString("name");
                        contentText = itemsForPreviews.get(i).getString("description");
                        break;
                }

                int resID = getResources().getIdentifier("preview_heading_text" + i,
                        "id", getPackageName());
                TextView previewText = (TextView) findViewById(resID);
                previewText.setText(headingText);

                resID = getResources().getIdentifier("preview_text" + i,
                        "id", getPackageName());
                previewText = (TextView) findViewById(resID);
                previewText.setText(contentText);

                int borderColour = -1;
                switch (itemType) {
                    case journalEntry:
                    case medicalNote:
                        borderColour = getResources().getColor(android.R.color.holo_red_light);
                        break;
                    case measurement:
                        borderColour = getResources().getColor(android.R.color.holo_green_light);
                        break;
                    case questionnaire:
                        borderColour = getResources().getColor(android.R.color.holo_blue_light);
                        break;
                }

                resID = getResources().getIdentifier("preview" + i,
                        "id", getPackageName());
                RelativeLayout previewLayout = (RelativeLayout) findViewById(resID);
                previewLayout.setBackgroundColor(borderColour);

                // Set category icon according to panel
                resID = getResources().getIdentifier("icon" + i,
                        "id", getPackageName());
                ImageView icon = (ImageView) findViewById(resID);
                switch (itemType) {
                    case journalEntry:
                        icon.setImageResource(R.drawable.journal_icon_512);
                        break;
                    case medicalNote:
                        icon.setImageResource(R.drawable.medical_note_icon_512);
                        break;
                    case measurement:
                        if (itemType == PatientSingleton.ItemType.measurement) {
                            if (headingText.compareTo("Steps") == 0) {
                                icon.setImageResource(R.drawable.steps_icon_512);
                            }
                            if (headingText.compareTo("Weight") == 0) {
                                icon.setImageResource(R.drawable.weight_icon_512);
                            }
                        }
                        break;
                    case questionnaire:
                        icon.setImageResource(R.drawable.questionnaire_icon_512);
                        break;
                }

            }

            // if there are less preview items than preview spaces, remove colour/click listener for unused preview panels
            for (; i < textPreviewsPerPage; i++) {
                int resID = getResources().getIdentifier("preview" + i,
                        "id", getPackageName());
                RelativeLayout previewLayout = (RelativeLayout) findViewById(resID);
                previewLayout.setOnClickListener(null);
                previewLayout.setVisibility(View.INVISIBLE);
                resID = getResources().getIdentifier("preview_contents" + i,
                        "id", getPackageName());
                RelativeLayout previewContents = (RelativeLayout) findViewById(resID);
                previewContents.setOnClickListener(null);
                previewContents.setVisibility(View.INVISIBLE);
            }
        } catch (JSONException je) {
            System.out.println("getting measurement types failed");
        }
    }
}
