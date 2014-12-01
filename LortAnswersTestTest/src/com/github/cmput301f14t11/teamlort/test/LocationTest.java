package com.github.cmput301f14t11.teamlort.test;

import junit.framework.TestCase;
import android.location.LocationManager;

import com.github.cmput301f14t11.teamlort.Model.GpsLocation;
import com.github.cmput301f14t11.teamlort.Model.Profile;
import com.github.cmput301f14t11.teamlort.Model.Question;

public class LocationTest extends TestCase {
	
	/**
	 * Use Case 24
	 * Tests whether the location has been attached to posts only if the user specified whether they want their location attached
	 */
	public void testAttachLocation(){
		Profile prof = new Profile();
		double latitude = 53;
		double longitude = 113;
		GpsLocation gpsLoc = new GpsLocation(latitude, longitude);
		
		// user wants to attach location
		prof.setLocationServices(true);
		
		prof.setLocation(latitude, longitude);
		
		Question question = new Question();
		question.setLocation(gpsLoc);

		assertTrue("latitudes do not match", question.getLocation().getLatitude() == latitude);
		assertTrue("longitudes do not match", question.getLocation().getLongitude() == longitude);
		
		// user does not want to attach location to question
		prof.setLocationServices(false);
		
		assertTrue("location is not false", prof.getLocationService() == false);
		
		question = new Question();
		question.setLocation(gpsLoc);
		
		assertTrue("latitudes match", question.getLocation().getLatitude() == latitude);
		assertTrue("longitudes match", question.getLocation().getLongitude() == longitude);
		

	}

	
	/** 
	 * Use Case 26:
	 * Tests whether the user can set their location manually
	 */
	public void testSetManually() {
		Profile prof = new Profile();
		double latitude = 53;
		double longitude = 113;
		LocationManager locationManager = null;
		
		// user has specified that they want to manually set location
		prof.locationSetManually(true);
		assertTrue("not manually set", prof.getManuallySetStatus() == true);
		
		prof.setLocation(latitude, longitude);
		
		GpsLocation loc = prof.getLocation(locationManager);
		
		// Check that coordinates are the ones that were set by the user
		assertTrue("latitudes don't match", loc.getLatitude() == latitude);
		assertTrue("longitudes don't match", loc.getLongitude() == longitude);
		
		// test where location is manually set
		prof.locationSetManually(false);
		
		assertTrue("manually set", prof.getManuallySetStatus() == false);
		
		prof.setLocation(latitude, longitude);
		
		GpsLocation location = prof.getLocation(locationManager);
		
		// Check that coordinates are the ones that were set by the user
		assertTrue("latitudes match", location.getLatitude() != latitude);
		assertTrue("longitudes match", location.getLongitude() != longitude);
	}
	
	/**
	 * Use Case 27: 
	 * Tests if city location is displayed on posts
	 */
	public void testDisplayPostLocation() {
		
	}
		

}
