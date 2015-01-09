package connectedhealth;

import org.apache.http.NameValuePair;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;


public class OAuthHelperTest {

	// given on developer signup
	private static String consumerKey = "3ee15d490209fc40b0163102cfcb34f7451382d27c686b8b24489bdcb66f";
	private static String consumerSecret = "f9879f0bcb97c773001e01121de542420014065173d8f915f895efd510fb79";

	// for the user we are getting data from
	private static String userId = "5536764";
	private static String oauthToken = "3d27787c4fdf4d18f60005e06929dbb478249c8c02f6042591b40f9ce101";
	private static String oauthTokenSecret = "e71762578dc325bd32ec73b9628b88c519093d65efd80017434c327f88c897";

	// useful constants
	private static final String activityMeasurementsAction = "getactivity";
	private static final String activityMeasurementsPath = "/v2/measure";
	private static final String bodyMeasurementsAction = "getmeas";
	private static final String bodyMeasurementsPath = "/measure";
	private static final String withingsApiHost = "wbsapi.withings.net";
	
	/* ArrayList<NameValuePair> buildParams
	 * check that oauth params and extra params are included, and are in alphabetical order */
	private void checkParamsAlphabeticalOrder(ArrayList<NameValuePair> params) {
		String currentName, nextName;
		for (int i = 0; i < params.size() - 1; i++) {
			currentName = params.get(i).getName();
			nextName = params.get(i+1).getName();
			assertTrue(currentName.compareTo(nextName) < 0);		// current name is lexicographically less than next name
		}		
	}
	
	private void checkParamsPresent(ArrayList<NameValuePair> orderedParams, ArrayList<String> extraParams) {
		ArrayList<String> oauthParams = new ArrayList<String>();
		oauthParams.add("oauth_consumer_key");
		oauthParams.add("oauth_nonce");
		oauthParams.add("oauth_signature");
		oauthParams.add("oauth_signature_method");
		oauthParams.add("oauth_timestamp");
		oauthParams.add("oauth_token");
		oauthParams.add("oauth_version");
		oauthParams.addAll(extraParams);

		for (int i = 0; i < orderedParams.size(); i++) {
			String p = orderedParams.get(i).getName();
			oauthParams.remove(p);
		}
		assertTrue(oauthParams.isEmpty());
	}

	@Test
	public void paramsOrderedWithNoExtraParams() throws Exception {
		ArrayList<NameValuePair> params = OAuthHelper.buildParams("action value", "urlWithoutParams value", "", "", "", "consumerKey value", "consumerSecret", "oauthToken", "oauthTokenSecret", "userId");
		checkParamsAlphabeticalOrder(params);

		checkParamsPresent(params, new ArrayList<String>());
	}

	@Test
	public void paramsOrderedWithDateParam() throws Exception {
		String date = "2014-12-11";
		String scheme = "https";
		String host = withingsApiHost;
		String path = activityMeasurementsPath;
		String urlWithoutParams = scheme + "://" + host + path;
		ArrayList<NameValuePair> params = OAuthHelper.buildParams(activityMeasurementsAction, urlWithoutParams, date, "", "", consumerKey, consumerSecret, oauthToken, oauthTokenSecret, userId);

		checkParamsAlphabeticalOrder(params);
	
		ArrayList<String> extraParams = new ArrayList<String>();
		extraParams.add("date");
		checkParamsPresent(params, extraParams);
	}

	@Test
	public void paramsOrderedWithStartDateAndEndDateParams() throws Exception {
		String startDate = "1418100199";
		String endDate = "1418618750";
		String scheme = "https";
		String host = withingsApiHost;
		String path = bodyMeasurementsPath;
		String urlWithoutParams = scheme + "://" + host + path;
		ArrayList<NameValuePair> params = OAuthHelper.buildParams(bodyMeasurementsAction, urlWithoutParams, "", startDate, endDate, consumerKey, consumerSecret, oauthToken, oauthTokenSecret, userId);

		checkParamsAlphabeticalOrder(params);

		ArrayList<String> extraParams = new ArrayList<String>();
		extraParams.add("startdate");
		extraParams.add("enddate");
		checkParamsPresent(params, extraParams);
	}

	/* ArrayList<NameValuePair> buildParams
	 * when an invalid combination of extra params is given, do API call without params */

	@Test
	public void paramsInvalidCombinationStartDateWithoutEndDate() throws Exception {
		String startDate = "1418100199";
		String scheme = "https";
		String host = withingsApiHost;
		String path = bodyMeasurementsPath;
		String urlWithoutParams = scheme + "://" + host + path;
		ArrayList<NameValuePair> params = OAuthHelper.buildParams(bodyMeasurementsAction, urlWithoutParams, "", startDate, "", consumerKey, consumerSecret, oauthToken, oauthTokenSecret, userId);

		checkParamsAlphabeticalOrder(params);

		ArrayList<String> extraParams = new ArrayList<String>();
		checkParamsPresent(params, extraParams);
	}

	@Test
	public void paramsInvalidCombinationEndDateWithoutStartDate() throws Exception {
		String endDate = "1428100250";
		String scheme = "https";
		String host = withingsApiHost;
		String path = bodyMeasurementsPath;
		String urlWithoutParams = scheme + "://" + host + path;
		ArrayList<NameValuePair> params = OAuthHelper.buildParams(bodyMeasurementsAction, urlWithoutParams, "", "", endDate, consumerKey, consumerSecret, oauthToken, oauthTokenSecret, userId);

		checkParamsAlphabeticalOrder(params);

		ArrayList<String> extraParams = new ArrayList<String>();
		checkParamsPresent(params, extraParams);
	}
	
	@Test
	public void paramsInvalidCombinationDateAndStartDate() throws Exception {
		String date = "1418100199";
		String startDate = "1418100199";
		String scheme = "https";
		String host = withingsApiHost;
		String path = bodyMeasurementsPath;
		String urlWithoutParams = scheme + "://" + host + path;
		ArrayList<NameValuePair> params = OAuthHelper.buildParams(bodyMeasurementsAction, urlWithoutParams, date, startDate, "", consumerKey, consumerSecret, oauthToken, oauthTokenSecret, userId);

		checkParamsAlphabeticalOrder(params);

		ArrayList<String> extraParams = new ArrayList<String>();
		checkParamsPresent(params, extraParams);
	}

	@Test
	public void paramsInvalidCombinationDateAndStartDateAndEndDate() throws Exception {
		String date = "1418100199";
		String startDate = "1418100199";
		String endDate = "1418618750";
		String scheme = "https";
		String host = withingsApiHost;
		String path = bodyMeasurementsPath;
		String urlWithoutParams = scheme + "://" + host + path;
		ArrayList<NameValuePair> params = OAuthHelper.buildParams(bodyMeasurementsAction, urlWithoutParams, date, startDate, endDate, consumerKey, consumerSecret, oauthToken, oauthTokenSecret, userId);

		checkParamsAlphabeticalOrder(params);

		ArrayList<String> extraParams = new ArrayList<String>();
		checkParamsPresent(params, extraParams);
	}
	
}
