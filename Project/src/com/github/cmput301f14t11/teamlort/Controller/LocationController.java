package com.github.cmput301f14t11.teamlort.Controller;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import com.github.cmput301f14t11.teamlort.Model.GpsLocation;
import com.github.cmput301f14t11.teamlort.Model.RepliableText;

public class LocationController {

	private static final AndroidHttpClient ANDROID_HTTP_CLIENT = AndroidHttpClient
			.newInstance(LocationController.class.getName());

	
	/**
	 * This method is used to get the user's location and set it to a GpsLocation type.
	 * This method is used whenever an activity needs to get the current GPS location.
	 * For example, in Sort By Location or Geolocation Settings
	 * @param locationManager from activity
	 * @return GpsLocation of the user 
	 */
	public GpsLocation getGPSLocation(LocationManager locationManager) {

		double latitude, longitude;

		// Define the criteria how to select the locatioin provider -> use
		// default

		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, false);
		Location location = locationManager.getLastKnownLocation(provider);

		GpsLocation gpsLocation;

		// Initialize the location fields
		if (location != null) {
			latitude = location.getLatitude();
			longitude = location.getLongitude();

			gpsLocation = new GpsLocation(latitude, longitude);

			return gpsLocation;

		} else {

			return gpsLocation = new GpsLocation(0.0, 0.0);
		}
	}

	/**
	 * This method is used to see when a post has a location.
	 * This is useful for initalizing objects in object factory with or without location depending
	 * @param RepliableText - The post
	 * @return true if has a valid location, else false
	 */
	public boolean hasLocation(RepliableText field) {
		if (field.getLocation() != new GpsLocation(0, 0)) {
			return true;
		} else {
			return false;
		}
	}

	public String fetchCityNameUsingGoogleMap(double lat, double lng) {
		String googleMapUrl = "http://maps.googleapis.com/maps/api/geocode/json?latlng="
				+ lat + "," + lng + "&sensor=false&language=fr";
		String cityName = null;
		JSONObject googleMapResponse = null;
		JSONArray results = null;

		try {

			googleMapResponse = new JSONObject(ANDROID_HTTP_CLIENT.execute(
					new HttpGet(googleMapUrl), new BasicResponseHandler()));

			Log.i("GoogleMapsURL", "http good");

			// many nested loops.. not great -> use expression instead
			// loop among all results
			results = (JSONArray) googleMapResponse.get("results");
			Log.i("GoogleMapsURL", "results");

			for (int i = 0; i < results.length(); i++) {
				Log.i("GoogleMapsURL", "results not 0");
				// loop among all addresses within this result
				JSONObject result = results.getJSONObject(i);
				if (result.has("address_components")) {
					Log.i("GoogleMapsURL", "has address components");
					JSONArray addressComponents = result
							.getJSONArray("address_components");
					// loop among all address component to find a 'locality'
					// or
					// 'sublocality'
					for (int j = 0; j < addressComponents.length(); j++) {
						Log.i("GoogleMapsURL", "addCom not null");
						JSONObject addressComponent = addressComponents
								.getJSONObject(j);
						if (result.has("types")) {
							Log.i("GoogleMapsURL", "has types");
							JSONArray types = addressComponent
									.getJSONArray("types");

							// search for locality and sublocality

							for (int k = 0; k < types.length(); k++) {
								Log.i("GoogleMapsURL", "types not null");
								if ("locality".equals(types.getString(k))
										&& cityName == null) {
									Log.i("GoogleMapsURL", "has locality");
									if (addressComponent.has("long_name")) {

										cityName = addressComponent
												.getString("long_name");
										Log.i("GoogleMapsURL", "has long name");
									} else if (addressComponent
											.has("short_name")) {
										cityName = addressComponent
												.getString("short_name");
										Log.i("GoogleMapsURL", "has short name");
									}
								}

							}
							if (cityName != null) {
								Log.i("GoogleMapsURL", "city name not null");
								return cityName;
							}

						} else
							return "types";
					}
				} else
					return "componets";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return cityName;
		}
		return "uh oh";

	}

	private String getLocationInformaiton(double lat, double lng) {
		String googleMapsURL = "http://maps.googleapis.com/maps/api/geocode/json?latlng="
				+ lat + "," + lng + "&sensor=false&language=fr";
		MatchingNearByLocationTask task = new MatchingNearByLocationTask();
		task.execute(googleMapsURL);
		return null;
	}

	private class MatchingNearByLocationTask extends
			AsyncTask<String, Void, String> {

		String jsonStr;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog

		}

		@Override
		protected String doInBackground(String... params) {

			String cityName = null;
			JSONObject googleMapResponse = null;
			JSONArray results = null;

			try {

				googleMapResponse = new JSONObject(ANDROID_HTTP_CLIENT.execute(
						new HttpGet(), new BasicResponseHandler()));

				Log.i("GoogleMapsURL", "http good");

				// many nested loops.. not great -> use expression instead
				// loop among all results
				results = (JSONArray) googleMapResponse.get("results");
				Log.i("GoogleMapsURL", "results");

				for (int i = 0; i < results.length(); i++) {
					Log.i("GoogleMapsURL", "results not 0");
					// loop among all addresses within this result
					JSONObject result = results.getJSONObject(i);
					if (result.has("address_components")) {
						Log.i("GoogleMapsURL", "has address components");
						JSONArray addressComponents = result
								.getJSONArray("address_components");
						// loop among all address component to find a 'locality'
						// or
						// 'sublocality'
						for (int j = 0; j < addressComponents.length(); j++) {
							Log.i("GoogleMapsURL", "addCom not null");
							JSONObject addressComponent = addressComponents
									.getJSONObject(j);
							if (result.has("types")) {
								Log.i("GoogleMapsURL", "has types");
								JSONArray types = addressComponent
										.getJSONArray("types");

								// search for locality and sublocality

								for (int k = 0; k < types.length(); k++) {
									Log.i("GoogleMapsURL", "types not null");
									if ("locality".equals(types.getString(k))
											&& cityName == null) {
										Log.i("GoogleMapsURL", "has locality");
										if (addressComponent.has("long_name")) {

											cityName = addressComponent
													.getString("long_name");
											Log.i("GoogleMapsURL",
													"has long name");
										} else if (addressComponent
												.has("short_name")) {
											cityName = addressComponent
													.getString("short_name");
											Log.i("GoogleMapsURL",
													"has short name");
										}
									}

								}
								if (cityName != null) {
									Log.i("GoogleMapsURL", "city name not null");
									return cityName;
								}

							} else
								return "types";
						}
					} else
						return "componets";
				}
			} catch (Exception e) {
				e.printStackTrace();
				return cityName;
			}
			return "uh oh";

		}

		@Override
		protected void onCancelled() {

			super.onCancelled();
			// progressDialog.dismiss();

		}

	}

	public String getLocationInfo(double lat, double lng) {

		HttpGet httpGet = new HttpGet(
				"http://maps.googleapis.com/maps/api/geocode/json?latlng="
						+ lat + "," + lng + "&sensor=false");
		HttpClient client = new DefaultHttpClient();
		HttpResponse response;
		StringBuilder stringBuilder = new StringBuilder();

		try {
			response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			InputStream stream = entity.getContent();
			int b;
			while ((b = stream.read()) != -1) {
				stringBuilder.append((char) b);
			}
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}

		String jsonString = "No Location Found";

		jsonString = stringBuilder.toString();

		return jsonString;
	}

}
