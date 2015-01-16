package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;

public class QuickMenu extends ActionBarActivity {

    protected boolean informationButtonEnabled = true;
    protected boolean questionnaireButtonEnabled = true;
    protected boolean profileButtonEnabled = true;
    protected boolean measurementsButtonEnabled = true;

    protected void disableInformationButton() {
        informationButtonEnabled = false;
    }

    protected void disableQuestionnaireButton() {
        questionnaireButtonEnabled = false;
    }

    protected void disableProfileButton() {
        profileButtonEnabled = false;
    }

    protected void disableMeasurementsButton() {
        measurementsButtonEnabled = false;
    }

    /** Called when the user clicks the Information View button */
    public void goToInformationView(View view) {
        if (informationButtonEnabled) {
            Intent intent = new Intent(this, InformationViewActivity.class);
            startActivity(intent);
        }
    }

    /** Called when the user clicks the Questionnaire View button */
    public void goToQuestionnaireView(View view) {
        if (questionnaireButtonEnabled) {
            Intent intent = new Intent(this, QuestionnaireViewActivity.class);
            startActivity(intent);
        }
    }

    /** Called when the user clicks the Profile View button */
    protected void goToProfileView(View view) {
        if (profileButtonEnabled) {
            Intent intent = new Intent(this, ProfilePanelsActivity.class);
            startActivity(intent);
        }
    }

    /** Called when the user clicks the Measurement View button */
    protected void goToMeasurementView(View view) {
        if (measurementsButtonEnabled) {
            Intent intent = new Intent(this, MeasurementViewActivity.class);
            startActivity(intent);
        }
    }

    /** Called when the user clicks the Home View button */
    public void goToMainView(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
