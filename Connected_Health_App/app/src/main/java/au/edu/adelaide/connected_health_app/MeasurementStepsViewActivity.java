package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MeasurementStepsViewActivity extends QuickMenu {

    private final String url = "http://www.adelaide.edu.au"; // constant local URL
    //    private final String url = "http://10.201.6.250:8080/ConnectedHealth/withingsApi/activitymeasurements?startdate=2014-01-01&enddate=2014-12-25"; // constant local URL
    private WebView webView;
    String json = "";

    String constantStepsJson = "{\"status\":0,\"body\":{\"activities\":[{\"date\":\"2014-12-11\",\"steps\":66,\"distance\":94.99,\"calories\":4.03,\"elevation\":0,\"soft\":120,\"moderate\":0,\"intense\":0,\"timezone\":\"Australia\\/Adelaide\"},{\"date\":\"2014-12-12\",\"steps\":136,\"distance\":94.99,\"calories\":4.03,\"elevation\":0,\"soft\":120,\"moderate\":0,\"intense\":0,\"timezone\":\"Australia\\/Adelaide\"},{\"date\":\"2014-12-13\",\"steps\":2012,\"distance\":94.99,\"calories\":4.03,\"elevation\":0,\"soft\":120,\"moderate\":0,\"intense\":0,\"timezone\":\"Australia\\/Adelaide\"},{\"date\":\"2014-12-14\",\"steps\":799,\"distance\":94.99,\"calories\":4.03,\"elevation\":0,\"soft\":120,\"moderate\":0,\"intense\":0,\"timezone\":\"Australia\\/Adelaide\"},{\"date\":\"2014-12-15\",\"steps\":0,\"distance\":94.99,\"calories\":4.03,\"elevation\":0,\"soft\":120,\"moderate\":0,\"intense\":0,\"timezone\":\"Australia\\/Adelaide\"}]}}";

    String firstPart = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "\t<head>\n" +
            "\t\t<script type=\"text/javascript\" src=\"http://www.jchartfx.com/libs/jQuery/jquery-1.7.1.js\">\n" +
            "\t    </script>\n" +
            "\t    <script type=\"text/javascript\" src=\"http://www.jchartfx.com/libs/v7/current/js/jchartfx.system.js\">\n" +
            "\t    </script>\n" +
            "\t    <script type=\"text/javascript\" src=\"http://www.jchartfx.com/libs/v7/current/js/jchartfx.coreVector.js\">\n" +
            "\t    </script>\n" +
            "\t    <script type=\"text/javascript\" src=\"http://www.jchartfx.com/libs/v7/current/js/jchartfx.advanced.js\">\n" +
            "\t    </script>\n" +
            "\t    <script type=\"text/javascript\" src=\"http://www.jchartfx.com/libs/v7/current/js/jchartfx.gauge.js\">\n" +
            "\t    </script>\n" +
            "\t    <script type=\"text/javascript\" src=\"http://www.jchartfx.com/libs/v7/current/js/jchartfx.treemap.js\">\n" +
            "\t    </script>\n" +
            "\t    <script type=\"text/javascript\" src=\"http://www.jchartfx.com/libs/v7/current/js/jchartfx.sparkline.js\">\n" +
            "\t    </script>\n" +
            "\t    <script type=\"text/javascript\" src=\"http://www.jchartfx.com/libs/v7/current/motifs/jchartfx.motif.whitespace.js\">\n" +
            "\t    </script>\n" +
            "\t\t\n" +
            "\t\t\n" +
            "\t\t<script type=\"text/javascript\">\n" +
            "\t\t\tvar chart1;        \n" +
            "\t\t\tfunction loadChart()\n" +
            "\t\t\t{\n" +
            "\t\t\t\tchart1 = new cfx.Chart();\n" +
            "\t\t\t\tchart1.getData().setSeries(1);\n" +
            "\t\t\t\tchart1.getAxisY().setMin(min);\n" +
            "\t\t\t\tchart1.getAxisY().setMax(max);\n" +
            "\t\t\t\tvar series1 = chart1.getSeries().getItem(0);\n" +
            "\t\t\t\tseries1.setGallery(cfx.Gallery.Lines);\n" +
            "\t\t\t\tchart1.setDataSource(data);\n" +
            "\t\t\t\tvar divHolder = document.getElementById('ChartDiv');\n" +
            "\t\t\t\tchart1.create(divHolder);\n" +
            "\t\t\t\tchart1.setTitle(null);\t\n" +
            "\t\t\t}";

    String secondPart = "</script>\n" +
            "\t\t\n" +
            "\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"http://www.jchartfx.com/libs/v7/current/styles/Attributes/jchartfx.attributes.whitespace.css\" />\n" +
            "    \t<link rel=\"stylesheet\" type=\"text/css\" href=\"http://www.jchartfx.com/libs/v7/current/styles/Palettes/jchartfx.palette.whitespace.css\" />\n" +
            "   \t</head>\n" +
            "\t\n" +
            "\t\n" +
            "\t<body onload=\"loadChart()\"> \n" +
            "\t\t<div id=\"ChartDiv\" style=\"width:100%;height:500px;top:-50px;display:inline-block;position:absolute\"></div>\n" +
            "\t</body>\n" +
            "\t\n" +
            "</html>";

    String constantStepsData = "var max = 81;\n" +
            "var min = 51;\n" +
            "var data=[{\"date\":\"2014-12-11\",\"steps\":66},{\"date\":\"2014-12-12\",\"steps\":66}];";

    String singleReadingPageA = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "\t<body> \n" +
            "\t\t<h1>";

    String singleReadingPageC = "\n" +
            "\t\t</h1>\n" +
            "\t</body>\n" +
            "\t\n" +
            "</html>";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_measurement_view);

        webView = (WebView) findViewById(R.id.webviewGraphTest);        // graph of steps
        webView.getSettings().setJavaScriptEnabled(true);

        // Instantiate the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string JSON response from the Grails app.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // use constantStepsData for constant API data (draws graph for more than 1 value)
                        json = JSONParser.DataParser(constantStepsJson, "date", "steps");
//                        json = JSONParser.DataParser(response, "date", "steps");

                        if(JSONParser.readingCount(json) == 1){     // only one steps value
                            String[] jsonSingleData = json.split(",");      // get date value and steps value
                            String date = jsonSingleData[0];
                            String steps = jsonSingleData[1];

                            String singleReadingPageB = "Only One Value<br/>Date: " + date + " Steps: " + steps ;   // print single steps value as HTML
                            webView.loadData(singleReadingPageA + singleReadingPageB + singleReadingPageC, "text/html", "UTF-8");
                        }else{      // multiple steps values
                            webView.loadData(firstPart + json + secondPart, "text/html", "UTF-8");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Volley HTTP request failed.");
            }
        });
        // Add the request to the RequestQueue for asynchronous handling.
        queue.add(stringRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_measurement_view, menu);
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
        Intent intent = new Intent(this, InformationViewActivity.class);
        startActivity(intent);
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
        //already on view disable
//        Intent intent = new Intent(this, MeasurementViewActivity.class);
//        startActivity(intent);
    }

    /** Called when the user clicks the Home View button */
    public void goToMainView(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
