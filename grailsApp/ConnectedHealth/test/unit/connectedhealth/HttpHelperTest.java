package connectedhealth;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;


public class HttpHelperTest {

	// useful constants
	private static final String activityMeasurementsAction = "getactivity";
	private static final String bodyMeasurementsPath = "/measure";
	private static final String withingsApiHost = "wbsapi.withings.net";

	/* String buildUrl
	 * check that correct URL is built from components */
	@Test
	public void buildURLFromComponents() throws Exception {
		String scheme = "https";
		String host = withingsApiHost;
		String path = bodyMeasurementsPath;
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name1", "value1"));
		params.add(new BasicNameValuePair("name2", "value2"));

		String url = HttpHelper.buildUrl(activityMeasurementsAction, params, scheme, host, path);
		assertTrue(url.compareTo(scheme + "://" + host + path + "?name1=value1&name2=value2") == 0);	// strings are equal
	}

}
