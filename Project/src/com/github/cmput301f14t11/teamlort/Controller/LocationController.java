package com.github.cmput301f14t11.teamlort.Controller;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.http.AndroidHttpClient;

import com.github.cmput301f14t11.teamlort.Model.GpsLocation;
import com.github.cmput301f14t11.teamlort.Model.RepliableText;

public class LocationController {



	
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

	

}
