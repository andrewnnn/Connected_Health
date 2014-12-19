package au.edu.adelaide.connected_health_app;

import android.app.Activity;
import android.content.Context;

import org.json.*;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.File;

public class JSONParser extends Activity {

    public static String getDataFromJson(String JsonInput, String Key, String KeyEnd, String Value, String ValueEnd) {
        String cleanInput = JsonInput.substring(JsonInput.indexOf("[{"), JsonInput.indexOf("}]") + 2);
        String[] splitInput = cleanInput.split("\\},\\{");
        String result = "[";
        for (int i = 0; i < splitInput.length; i++) {
            String X = splitInput[i].substring(splitInput[i].indexOf(Key + "\":\"") + Key.length(), splitInput[i].indexOf("\",\"" + KeyEnd));
            String Y = splitInput[i].substring(splitInput[i].indexOf(Value + "\":") + Value.length(), splitInput[i].indexOf(",\"" + ValueEnd));
            result += ("{\"" + Key + "\":\"" + X + "\",\"" + Value + "\":" + Y + "}");
            if (i < (splitInput.length - 1)) {
                result += ",";
            }
        }
        result += "]";
        return result;
    }


    public static String DataParser(String DataInput, String Key, String Value) {
        String result = "[";
        System.out.println("DataInput: "+DataInput);
        int max, min;
        try {
            JSONObject job = new JSONObject(DataInput);
            JSONObject body = (job.getJSONObject("body"));
            JSONArray act = body.getJSONArray("activities");

            //get the max and min value
            max = act.getJSONObject(0).getInt("steps");
            min = act.getJSONObject(0).getInt("steps");

            String valueA = "";
            int valueB = 0;

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

                result += ("{\"" + Key + "\":\"" + valueA + "\",\"" + Value + "\":" + valueB + "}");
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
            //output to Graph.js file
            result = "var max = " + max + ";\r\nvar min = " + min + ";\r\nvar data=" + result + ";\r\n";

            if(act.length() == 1){
                result = valueA + "," + valueB;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("result: "+result);
        return result;
    }

    public static int readingCount(String input){
        String[] jsonCounter = input.split("\\},\\{");
        return jsonCounter.length;
    }


}
