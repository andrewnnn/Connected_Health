package connectedhealth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;


public class HttpHelper {
    // Create the URL for the API call corresponding to the given action
    // action = value of the Withings API "action" parameter
    // orderedParams = parameter names and values ordered by name for signature
    // scheme = scheme for API call URL (http, https...)
    // host = host for API call URL
    // path = path for API call URL
    public static String buildUrl(String action, ArrayList<NameValuePair> orderedParams, String scheme, String host, String path) {
        URIBuilder uriBuilder = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path);

        for (int i = 0; i < orderedParams.size(); i++) {
            uriBuilder.setParameter(orderedParams.get(i).getName(), orderedParams.get(i).getValue());
        }

        return uriBuilder.toString();
    }

    // Return result of sending HTTP GET request.
    // urlString = url for GET request
    public static String httpGetRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

        BufferedReader br = new BufferedReader(
                new InputStreamReader(httpConnection.getInputStream()));
        String responseLine;
        StringBuilder response = new StringBuilder();

        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine);
        }
        br.close();

        return response.toString();
    }

}
