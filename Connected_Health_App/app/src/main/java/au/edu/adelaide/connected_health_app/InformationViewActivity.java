package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class InformationViewActivity extends ActionBarActivity {

    private final String vidUrl = "https://www.google.com";
    private WebView browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_view);

        //Making the info link clickable
        TextView link = (TextView) findViewById(R.id.infoLink);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        // Youtube webview
        browser = (WebView)findViewById(R.id.webviewYoutubeVid);
        browser.setWebViewClient(new MyBrowser());

        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.getSettings().setJavaScriptEnabled(true);
//        browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        browser.loadUrl(vidUrl);

        //testing loading html instead of url
//        String embedVid ="<html><body>Youtube video .. <br> <iframe class=\"youtube-player\" type=\"text/html\" width=\"640\" height=\"385\" src=\"http://www.youtube.com/embed/7NdtXe4LWeg\" frameborder=\"0\"></body></html>";
        String embedVid ="<html><body>Youtube video .. <br> <iframe class=\"youtube-player\" type=\"text/html\" width=\"300\" height=\"300\" src=\"http://www.youtube.com/embed/7NdtXe4LWeg\" frameborder=\"0\"></body></html>";

        browser.loadData(embedVid,"text/html","utf-8");
    }

    //embedded browser
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_information_view, menu);
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

    /** Called when the user clicks the Information View button */
    public void goToInformationView(View view) {
        //disabled
//        Intent intent = new Intent(this, InformationViewActivity.class);
//        startActivity(intent);
    }

    /** Called when the user clicks the Questionnaire View button */
    public void goToQuestionnaireView(View view) {
        Intent intent = new Intent(this, QuestionnaireViewActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Profile View button */
    public void goToProfileView(View view) {
        Intent intent = new Intent(this, ProfileViewActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Measurement View button */
    public void goToMeasurementView(View view) {
        Intent intent = new Intent(this, MeasurementViewActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Home View button */
    public void goToMainView(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
