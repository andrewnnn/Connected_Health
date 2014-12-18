package connectedhealth;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import org.json.*;

public class WithingsAPITest {

	WithingsAPI w;
	
	@Before
	// use known values for developer and user
	public void initialize() {
		w = new WithingsAPI("3ee15d490209fc40b0163102cfcb34f7451382d27c686b8b24489bdcb66f",
				"f9879f0bcb97c773001e01121de542420014065173d8f915f895efd510fb79",
				"5536764",
				"3d27787c4fdf4d18f60005e06929dbb478249c8c02f6042591b40f9ce101",
				"e71762578dc325bd32ec73b9628b88c519093d65efd80017434c327f88c897");
	}

	/* String getActivity/getMeasurements
	 * check that JSON is returned, and the response code is 0 (success) */
	private void tryStringToJson(String str) {
		boolean exceptionCaught = false;
		JSONObject jo = null;
		try {
			jo = new JSONObject(str);

			int statusCode = jo.getInt("status");
			assertEquals(0, statusCode);
		} catch (JSONException je) {
			exceptionCaught = true;
		}
		assertFalse(exceptionCaught);		
	}

	@Test
	public void getActivityMeasurementsWithoutParamReturnsJson() throws Exception {
		String measurements = w.getActivityMeasurements();
		tryStringToJson(measurements);
	}

	@Test
	public void getActivityMeasurementsWithDateReturnsJson() throws Exception {
		String measurements = w.getActivityMeasurements("2014-12-11");
		tryStringToJson(measurements);
	}

	@Test
	public void getActivityMeasurementsWithStartAndEndDateReturnsJson() throws Exception {
		String measurements = w.getActivityMeasurements("2014-12-01", "2014-12-15");
		tryStringToJson(measurements);
	}

	@Test
	public void getBodyMeasurementsWithoutParamReturnsJson() throws Exception {
		String measurements = w.getBodyMeasurements();
		tryStringToJson(measurements);
	}

	@Test
	public void getBodyMeasurementsWithDateReturnsJson() throws Exception {
		String measurements = w.getBodyMeasurements("1418100199");
		tryStringToJson(measurements);
	}

	@Test
	public void getBodyMeasurementsWithStartAndEndDateReturnsJson() throws Exception {
		String measurements = w.getBodyMeasurements("1418100199", "1418618750");
		tryStringToJson(measurements);
	}

}
