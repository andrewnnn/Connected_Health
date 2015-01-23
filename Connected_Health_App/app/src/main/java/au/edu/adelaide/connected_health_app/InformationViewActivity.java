package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class InformationViewActivity extends QuickMenu {

    private WebView browser;
    private final String mimeType = "text/html";
    private final String encoding = "utf-8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_view);

        //Making the info links clickable
        TextView link = (TextView) findViewById(R.id.infoLink0);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) findViewById(R.id.infoLink1);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) findViewById(R.id.infoLink2);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) findViewById(R.id.infoLink3);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        ImageView image = (ImageView) findViewById(R.id.infoImage0);

        // Youtube webviews
        browser = (WebView)findViewById(R.id.webViewYoutube0);
        browser.setWebViewClient(new MyBrowser());

        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.getSettings().setJavaScriptEnabled(true);
//        browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        browser.loadUrl(vidUrl);

        //testing loading html instead of url
//        String embedVid ="<html><body>Youtube video<br> <iframe class=\"youtube-player\" type=\"text/html\" width=\"640\" height=\"385\" src=\"http://www.youtube.com/embed/7NdtXe4LWeg\" frameborder=\"0\"></body></html>";

        browser.loadData(getEmbedVidHtml("7NdtXe4LWeg"),"text/html","utf-8");

        browser = (WebView)findViewById(R.id.webViewYoutube1);
        browser.setWebViewClient(new MyBrowser());
        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.loadData(getEmbedVidHtml("Qn2_ARtSzG4"), mimeType, encoding);

        browser = (WebView)findViewById(R.id.webViewYoutube2);
        browser.setWebViewClient(new MyBrowser());
        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.loadData(getEmbedVidHtml("_iXNd4EE6_Y"), mimeType, encoding);
//ikl8CSnILP4
        browser = (WebView)findViewById(R.id.webViewYoutube3);
        browser.setWebViewClient(new MyBrowser());
        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.loadData(getEmbedVidHtml("KrUyziCXzVs"), mimeType, encoding);
    }

    private String getEmbedVidHtml(String youtubeVideoId) {
        return "<html><body>Youtube video<br> <iframe class=\"youtube-player\" type=\"text/html\" width=\"300\" height=\"300\" src=\"http://www.youtube.com/embed/" + youtubeVideoId + "\" frameborder=\"0\"></body></html>";
    }

    //embedded browser
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public void openBrowser(View view){
        //Get url from tag
        String url = (String)view.getTag();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        //pass the url to intent data
        intent.setData(Uri.parse(url));

        startActivity(intent);
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
