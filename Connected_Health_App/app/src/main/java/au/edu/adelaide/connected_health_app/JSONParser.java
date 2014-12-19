package au.edu.adelaide.connected_health_app;

import android.app.Activity;
import android.content.Context;

import org.json.*;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.File;

public class JSONParser extends Activity {

    // return a string containing the variables for the javascript to draw the graph
    // DataInput = JSON from Grails app
    // XAxisKey = JSON key in DataInput that will be used as the x-axis values in the graph
    // YAxisKey = JSON key in DataInput that will be used as the y-axis values in the graph
    public static String DataParser(String DataInput, String XAxisKey, String YAxisKey) {
        String result = "[";
        System.out.println("DataInput: "+DataInput);
        int max, min;
        try {
            // get activities array from JSON string
            JSONObject job = new JSONObject(DataInput);
            JSONObject body = (job.getJSONObject("body"));
            JSONArray act = body.getJSONArray("activities");

            //get the max and min value
            max = act.getJSONObject(0).getInt("steps");
            min = act.getJSONObject(0).getInt("steps");

            String valueA = "";
            int valueB = 0;

            // get each date and steps value
            for (int i = 0; i < act.length(); i++) {
                valueA = act.getJSONObject(i).getString("date");
                valueB = act.getJSONObject(i).getInt("steps");

                //update max and min
                if (valueB > max) {
                    max = valueB;
                }
                if (valueB < min) {
                    min = valueB;
                }

                // combine date and steps values into an array
                result += ("{\"" + XAxisKey + "\":\"" + valueA + "\",\"" + YAxisKey + "\":" + valueB + "}");
                if (i < (act.length() - 1)) {
                    result += ",";
                }
            }
            result += "]";


            //set max and min a better range
            int diff = (max - min) / 2;
            max += diff;
            min -= diff;
            if(max == min){
                max += 15;
                min -= 15;
            }
            if(min < 0){
                min = 0;
            }
            // store max, min and date/steps array as javascript variables
            result = "var max = " + max + ";\r\nvar min = " + min + ";\r\nvar data=" + result + ";\r\n";

            if(act.length() == 1){      // single reading, return "date,steps"
                result = valueA + "," + valueB;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("result: "+result);
        return result;
    }

    // return the number of key-value pairs in a JSON string that contains one array
    // input = JSON returned from Grails app
    public static int readingCount(String input){
        String[] jsonCounter = input.split("\\},\\{");
        return jsonCounter.length;
    }


}
