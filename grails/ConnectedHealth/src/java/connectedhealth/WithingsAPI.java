package connectedhealth;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class WithingsAPI {

	// given on Withings developer signup
	private static String consumerKey;
	private static String consumerSecret;

	// for the user we are getting data from
	private static String userId = "5536764";
	private static String oauthToken;
	private static String oauthTokenSecret;
	
	// values of action parameter/URL path for supported types of API calls
	private static final String activityMeasurementsAction = "getactivity";
	private static final String activityMeasurementsPath = "/v2/measure";
	private static final String bodyMeasurementsAction = "getmeas";
	private static final String bodyMeasurementsPath = "/measure";
	
	// useful constants
	private static final Base64 base64Encoder = new Base64();
	private static final SecureRandom randomNumberGenerator = new SecureRandom();
	private static final String httpMethod = "GET";
	private static final String oauthSignatureMethod = "HMAC-SHA1";
	private static final String oauthVersion = "1.0";
	private static final String secretKeyAlgorithm = "HmacSHA1";
	private static final String urlEncoding = "UTF-8";
	private static final String withingsApiHost = "wbsapi.withings.net";
	
	// simple testing with working values for constructor
	public static void main(String[] args) throws ClientProtocolException, Exception,
		IOException, URISyntaxException, InvalidKeyException,
		NoSuchAlgorithmException {

		WithingsAPI oauth = new WithingsAPI("3ee15d490209fc40b0163102cfcb34f7451382d27c686b8b24489bdcb66f",
				"f9879f0bcb97c773001e01121de542420014065173d8f915f895efd510fb79",
				"5536764",
				"3d27787c4fdf4d18f60005e06929dbb478249c8c02f6042591b40f9ce101",
				"e71762578dc325bd32ec73b9628b88c519093d65efd80017434c327f88c897");

		String a;
		a = oauth.getActivityMeasurements();
		System.out.println(a);
		a = oauth.getActivityMeasurements("2014-12-11");
		System.out.println(a);
		a = oauth.getActivityMeasurements("2014-12-11", "2014-12-12");
		System.out.println(a);
		a = oauth.getBodyMeasurements();
		System.out.println(a);
		a = oauth.getBodyMeasurements("1418100199", "1418618750");
		System.out.println(a);
	}
	
	// consumerKey = string given on Withings developer signup
	// consumerSecret = string given on Withings developer signup
	// userId = ID of user account we want to get data from
	// oauthToken = string given after user allows developer access to their account
	// oauthTokenSecret = string given after user allows developer access to their account
	public WithingsAPI(String consumerKey, String consumerSecret, String userId, String oauthToken, String oauthTokenSecret) {
		WithingsAPI.consumerKey = consumerKey;
		WithingsAPI.consumerSecret = consumerSecret;
		WithingsAPI.userId = userId;
		WithingsAPI.oauthToken = oauthToken;
		WithingsAPI.oauthTokenSecret = oauthTokenSecret;
	}

	// Get activity measurements as a JSON string from Withings API.
	public String getActivityMeasurements() throws Exception {
		String scheme = "https";
		String host = withingsApiHost;
		String path = activityMeasurementsPath;
		String urlWithoutParams = scheme + "://" + host + path;
		ArrayList<NameValuePair> params = buildParams(activityMeasurementsAction, urlWithoutParams, "", "", "");
		String url = buildUrl(activityMeasurementsAction, params, scheme, host, path);
		return httpGetRequest(url);
	}

	// date = only date to get activity measurements from
	public String getActivityMeasurements(String date) throws Exception {
		String scheme = "https";
		String host = withingsApiHost;
		String path = activityMeasurementsPath;
		String urlWithoutParams = scheme + "://" + host + path;
		ArrayList<NameValuePair> params = buildParams(activityMeasurementsAction, urlWithoutParams, date, "", "");
		String url = buildUrl(activityMeasurementsAction, params, scheme, host, path);
		return httpGetRequest(url);
	}

	// startDate = first date to get activity measurements from
	// endDate = last date to get activity measurements from
	public String getActivityMeasurements(String startDate, String endDate) throws Exception {
		String scheme = "https";
		String host = withingsApiHost;
		String path = activityMeasurementsPath;
		String urlWithoutParams = scheme + "://" + host + path;
		ArrayList<NameValuePair> params = buildParams(activityMeasurementsAction, urlWithoutParams, "", startDate, endDate);
		String url = buildUrl(activityMeasurementsAction, params, scheme, host, path);
		return httpGetRequest(url);
	}

	// Get body measurements as a JSON string from Withings API.
	public String getBodyMeasurements() throws Exception {
		String scheme = "https";
		String host = withingsApiHost;
		String path = bodyMeasurementsPath;
		String urlWithoutParams = scheme + "://" + host + path;
		ArrayList<NameValuePair> params = buildParams(bodyMeasurementsAction, urlWithoutParams, "", "", "");
		String url = buildUrl(bodyMeasurementsAction, params, scheme, host, path);
		return httpGetRequest(url);
	}

	// date = only date to get body measurements from
	public String getBodyMeasurements(String date) throws Exception {
		String scheme = "https";
		String host = withingsApiHost;
		String path = bodyMeasurementsPath;
		String urlWithoutParams = scheme + "://" + host + path;
		ArrayList<NameValuePair> params = buildParams(bodyMeasurementsAction, urlWithoutParams, date, "", "");
		String url = buildUrl(bodyMeasurementsAction, params, scheme, host, path);
		return httpGetRequest(url);
	}

	// startDate = first date to get body measurements from
	// endDate = last date to get body measurements from
	public String getBodyMeasurements(String startDate, String endDate) throws Exception {
		String scheme = "https";
		String host = withingsApiHost;
		String path = bodyMeasurementsPath;
		String urlWithoutParams = scheme + "://" + host + path;
		ArrayList<NameValuePair> params = buildParams(bodyMeasurementsAction, urlWithoutParams, "", startDate, endDate);
		String url = buildUrl(bodyMeasurementsAction, params, scheme, host, path);
		return httpGetRequest(url);
	}

	// Build an ordered list of query string parameters for an API call.
	// Optional parameters that we don't want to use have value = ""
	// action = value of the Withings API "action" parameter
	// urlWithoutParams = URL for API call with the query string omitted
	// date (optional) = only date to get measurements from. used without startDate/endDate
	// startDate (optional) first date to get measurements from. used with endDate, without date
	// endDate (optional) last date to get measurements from. used with startDate, without date
	private static ArrayList<NameValuePair> buildParams(String action, String urlWithoutParams, String date, String startDate, String endDate) 
		throws Exception {
		ArrayList<NameValuePair> orderedParams = new ArrayList<NameValuePair>();

		orderedParams.add(new BasicNameValuePair("action", action));

		if (date != "" && startDate == "" && endDate == "") {
			orderedParams.add(new BasicNameValuePair("date", date));
		} else if (date == "" && startDate != "" && endDate != "") {
			if (action == activityMeasurementsAction) {
				orderedParams.add(new BasicNameValuePair("enddateymd", endDate));
			} else if (action == bodyMeasurementsAction) {
				orderedParams.add(new BasicNameValuePair("enddate", endDate));
			}
		}

		orderedParams.add(new BasicNameValuePair("oauth_consumer_key", consumerKey));

		String nonce = (new BigInteger(400, randomNumberGenerator).toString(32)).substring(
				0, 32);
		orderedParams.add(new BasicNameValuePair("oauth_nonce", nonce));

		// keep reference to the param before the oauth signature
		BasicNameValuePair oauthSignatureMethodPair = new BasicNameValuePair("oauth_signature_method",
				oauthSignatureMethod);
		orderedParams.add(oauthSignatureMethodPair);

		long timestamp = (System.currentTimeMillis() / 1000);
		orderedParams.add(new BasicNameValuePair("oauth_timestamp", "" + timestamp));

		orderedParams.add(new BasicNameValuePair("oauth_token", oauthToken));

		orderedParams.add(new BasicNameValuePair("oauth_version", oauthVersion));

		if (date == "" && startDate != "" && endDate != "") {
			if (action == activityMeasurementsAction) {
				orderedParams.add(new BasicNameValuePair("startdateymd", startDate));
			} else if (action == bodyMeasurementsAction) {
				orderedParams.add(new BasicNameValuePair("startdate", startDate));
			}
		}
		
		orderedParams.add(new BasicNameValuePair("userid", userId));

		String signature = createSignature(urlWithoutParams, orderedParams);

		// add signature param immediately before signature method param
		orderedParams.add(orderedParams.indexOf(oauthSignatureMethodPair), new BasicNameValuePair("oauth_signature", signature));

		return orderedParams;
	}

	// Create the URL for the API call corresponding to the given action
	// action = value of the Withings API "action" parameter
	// orderedParams = parameter names and values ordered by name for signature
	// scheme = scheme for API call URL (http, https...)
	// host = host for API call URL
	// path = path for API call URL
	private static String buildUrl(String action, ArrayList<NameValuePair> orderedParams, String scheme, String host, String path) {
		if (action == activityMeasurementsAction) {
			path = activityMeasurementsPath;
		} else {
			path = bodyMeasurementsPath;			
		}
		URIBuilder uriBuilder = new URIBuilder()
        .setScheme(scheme)
        .setHost(host)
        .setPath(path);

		for (int i = 0; i < orderedParams.size(); i++) {
			uriBuilder.setParameter(orderedParams.get(i).getName(), orderedParams.get(i).getValue());
		}

		return uriBuilder.toString();
	}

	// Create an OAuth signature for a URL and parameters.
	// url = URL for signature
	// orderedParams = parameter names and values ordered by name for signature
	private static String createSignature(String url, ArrayList<NameValuePair> orderedParams)
		throws UnsupportedEncodingException, NoSuchAlgorithmException,
		InvalidKeyException {

		String encodedUrl = URLEncoder.encode(url, urlEncoding);
		String queryString = URLEncodedUtils.format(orderedParams, urlEncoding);
		String encodedQueryString = URLEncoder.encode(queryString, urlEncoding);
		
		String baseString = httpMethod + "&" + encodedUrl + "&" + encodedQueryString;

		byte[] secretKeyBytes = (consumerSecret + "&" + oauthTokenSecret).getBytes(urlEncoding);
		SecretKey secretKey = new SecretKeySpec(secretKeyBytes, secretKeyAlgorithm);

		Mac macAlgorithm = Mac.getInstance(secretKeyAlgorithm);
		macAlgorithm.init(secretKey);

		// encode it, base64Encoder it, change it to string and return.
		return new String(base64Encoder.encode(macAlgorithm.doFinal(baseString.getBytes(
				urlEncoding))), urlEncoding).trim();
	}

	// Return result of sending HTTP GET request.
	// urlString = url for GET request
	private static String httpGetRequest(String urlString) throws Exception { 
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
