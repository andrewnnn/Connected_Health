package connectedhealth;


import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;

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
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.userId = userId;
		this.oauthToken = oauthToken;
		this.oauthTokenSecret = oauthTokenSecret;
	}

	// Get activity measurements as a JSON string from Withings API.
	public String getActivityMeasurements() throws Exception {
		String scheme = "https";
		String host = withingsApiHost;
		String path = activityMeasurementsPath;
		String urlWithoutParams = scheme + "://" + host + path;
		ArrayList<NameValuePair> params = OAuthHelper.buildParams(activityMeasurementsAction, urlWithoutParams, "", "", "", consumerKey, consumerSecret, oauthToken, oauthTokenSecret, userId);
		String url = HttpHelper.buildUrl(activityMeasurementsAction, params, scheme, host, path);
		return HttpHelper.httpGetRequest(url);
	}

	// date (YYYY-MM-DD) = only date to get activity measurements from
	public String getActivityMeasurements(String date) throws Exception {
		String scheme = "https";
		String host = withingsApiHost;
		String path = activityMeasurementsPath;
		String urlWithoutParams = scheme + "://" + host + path;
		ArrayList<NameValuePair> params = OAuthHelper.buildParams(activityMeasurementsAction, urlWithoutParams, date, "", "", consumerKey, consumerSecret, oauthToken, oauthTokenSecret, userId);
		String url = HttpHelper.buildUrl(activityMeasurementsAction, params, scheme, host, path);
		return HttpHelper.httpGetRequest(url);
	}

	// startDate (YYYY-MM-DD) = first date to get activity measurements from
	// endDate (YYYY-MM-DD) = last date to get activity measurements from
	public String getActivityMeasurements(String startDate, String endDate) throws Exception {
		String scheme = "https";
		String host = withingsApiHost;
		String path = activityMeasurementsPath;
		String urlWithoutParams = scheme + "://" + host + path;
		ArrayList<NameValuePair> params = OAuthHelper.buildParams(activityMeasurementsAction, urlWithoutParams, "", startDate, endDate, consumerKey, consumerSecret, oauthToken, oauthTokenSecret, userId);
		String url = HttpHelper.buildUrl(activityMeasurementsAction, params, scheme, host, path);
		return HttpHelper.httpGetRequest(url);
	}

	// Get body measurements as a JSON string from Withings API.
	public String getBodyMeasurements() throws Exception {
		String scheme = "https";
		String host = withingsApiHost;
		String path = bodyMeasurementsPath;
		String urlWithoutParams = scheme + "://" + host + path;
		ArrayList<NameValuePair> params = OAuthHelper.buildParams(bodyMeasurementsAction, urlWithoutParams, "", "", "", consumerKey, consumerSecret, oauthToken, oauthTokenSecret, userId);
		String url = HttpHelper.buildUrl(bodyMeasurementsAction, params, scheme, host, path);
		return HttpHelper.httpGetRequest(url);
	}

	// date (unix epoch) = only date to get body measurements from
	public String getBodyMeasurements(String date) throws Exception {
		String scheme = "https";
		String host = withingsApiHost;
		String path = bodyMeasurementsPath;
		String urlWithoutParams = scheme + "://" + host + path;
		ArrayList<NameValuePair> params = OAuthHelper.buildParams(bodyMeasurementsAction, urlWithoutParams, date, "", "", consumerKey, consumerSecret, oauthToken, oauthTokenSecret, userId);
		String url = HttpHelper.buildUrl(bodyMeasurementsAction, params, scheme, host, path);
		return HttpHelper.httpGetRequest(url);
	}

	// startDate (unix epoch) = first date to get body measurements from
	// endDate (unix epoch) = last date to get body measurements from
	public String getBodyMeasurements(String startDate, String endDate) throws Exception {
		String scheme = "https";
		String host = withingsApiHost;
		String path = bodyMeasurementsPath;
		String urlWithoutParams = scheme + "://" + host + path;
		ArrayList<NameValuePair> params = OAuthHelper.buildParams(bodyMeasurementsAction, urlWithoutParams, "", startDate, endDate, consumerKey, consumerSecret, oauthToken, oauthTokenSecret, userId);
		String url = HttpHelper.buildUrl(bodyMeasurementsAction, params, scheme, host, path);
		return HttpHelper.httpGetRequest(url);
	}
	
}
