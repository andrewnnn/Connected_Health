package connectedhealth;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
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
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;


// 
public class OAuthHelper {

	// useful constants
	private static final Base64 base64Encoder = new Base64();
	private static final SecureRandom randomNumberGenerator = new SecureRandom();
	private static final String httpMethod = "GET";
	private static final String oauthSignatureMethod = "HMAC-SHA1";
	private static final String oauthVersion = "1.0";
	private static final String secretKeyAlgorithm = "HmacSHA1";
	private static final String urlEncoding = "UTF-8";

	// values of action parameter/URL path for supported types of API calls
	private static final String activityMeasurementsAction = "getactivity";
	private static final String bodyMeasurementsAction = "getmeas";

	// Build a list of query string parameters in the correct order (alphabetical) for OAuth API signature generation
	// Optional parameters that we don't want to use have value = ""
	// action = value of the Withings API "action" parameter
	// urlWithoutParams = URL for API call with the query string omitted
	// date (optional) = only date to get measurements from. used without startDate/endDate
	// startDate (optional) first date to get measurements from. used with endDate, without date
	// endDate (optional) last date to get measurements from. used with startDate, without date
	public static ArrayList<NameValuePair> buildParams(String action, String urlWithoutParams, 
			String date, String startDate, String endDate, String consumerKey, 
			String consumerSecret, String oauthToken, String oauthTokenSecret, String userId) 
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

		String signature = OAuthHelper.createSignature(httpMethod, urlWithoutParams, orderedParams, consumerSecret, oauthTokenSecret);

		// add signature param immediately before signature method param
		orderedParams.add(orderedParams.indexOf(oauthSignatureMethodPair), new BasicNameValuePair("oauth_signature", signature));

		return orderedParams;
	}
	
	// Create an OAuth signature for a URL and parameters.
	// url = URL for signature
	// orderedParams = parameter names and values ordered by name for signature
	private static String createSignature(String httpMethod, String urlWithoutParams, ArrayList<NameValuePair> orderedParams, String consumerSecret, String oauthTokenSecret)
		throws UnsupportedEncodingException, NoSuchAlgorithmException,
		InvalidKeyException {

		String encodedUrl = URLEncoder.encode(urlWithoutParams, urlEncoding);
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

}
