package com.github.cmput301f14t11.teamlort.Controller;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

import com.github.cmput301f14t11.teamlort.Model.GpsLocation;
import com.github.cmput301f14t11.teamlort.Model.RepliableText;

public class LocationController {

	public GpsLocation getGPSLocation(LocationManager locationManager) {

		double latitude, longitude;
		GpsLocation GpsLocation;

		// Define the criteria how to select the locatioin provider -> use
		// default
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, false);
		Location location = locationManager.getLastKnownLocation(provider);

		// Initialize the location fields
		if (location != null) {
			latitude = location.getLatitude();
			longitude = location.getLongitude();
			
			GpsLocation = new GpsLocation(latitude, longitude);
			
			return GpsLocation;
			
		} else {
			return GpsLocation = null;
		}
	}
	
	public boolean hasLocation(RepliableText field) {
		if (field.getLocation() != new GpsLocation(0,0)) {
			return true;
		}
		else {
			return false;
		}
	}

}
