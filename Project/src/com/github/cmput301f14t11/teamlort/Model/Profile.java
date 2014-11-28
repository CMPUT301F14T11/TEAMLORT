package com.github.cmput301f14t11.teamlort.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import com.github.cmput301f14t11.teamlort.Controller.LocationController;

import android.location.LocationManager;
import android.util.Log;

/** 
 *  Profile class implements attributes and methods for an author to 
 *  log in and retrieve his or her data from server
 * @author Hang_Peng
 *
 */

public class Profile extends Observable
implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String username = "";
	private transient GpsLocation gpsLocation = null;
	//private transient LocalManager localManager = LocalManager.getManager();
	private transient boolean locationService = true;
	private transient boolean manuallySetStatus = false;
	
	protected ArrayList<Question> savedQuestionList = new ArrayList<Question>();
	protected ArrayList<Question> favedQuestionList = new ArrayList<Question>();
	protected ArrayList<Question> myQuestionList = new ArrayList<Question>();
	protected ArrayList<Question> tempQuestionList = new ArrayList<Question>();
	//protected LocalManager = new LocalManager();

	
	
	/**
	 * setUsername will set the given username
	 * @param username   a unique string that used to identify the profile 
	 */
	public void setUsername(String username) {
		this.username = username;
		
	}
	
	/**
	 * getUsername() will get the given username
	 * @return username as a string
	 */
	public String getUsername(){
		return username;
	}
	
	/**
	 * isLogin will check if the user have log in
	 * @return a boolean indicate whether the user have logged in or not
	 */
	public boolean isLogin() {
		if (this.getUsername().isEmpty() || this.getUsername() == null){
			return false;
		}
		else{			
			return true;
		}
	}
	
	public void setLocation(double latitude, double longitude) {
		if(getLocationService()) {
			this.gpsLocation = new GpsLocation(latitude, longitude);
		}
		else {
			this.gpsLocation = null;
		}
	}
	
	public GpsLocation getLocation(LocationManager locationManager) {
		if (getLocationService()) {
			if(getManuallySetStatus() == true) {
				Log.i("returngps","manually set gps sent");
				return gpsLocation;
			} else {
				LocationController loc = new LocationController();
				gpsLocation = loc.getGPSLocation(locationManager);
				if (gpsLocation != null) {
					Log.i("returngps","actual gps sent");
					return gpsLocation;
				} else {
					Log.i("returngps","blank gps sent");
					return new GpsLocation(0,0);
				}
			}
		} else {
			Log.i("returngps","blank gps sent");
			return new GpsLocation(0,0);
		}
	}
	
	public void setLocationServices(boolean status) {
		this.locationService = status;
	}
	
	public boolean getLocationService() {
		return locationService;
	}
	
	public void locationSetManually(boolean status) {
		this.manuallySetStatus = status;
	}
	
	public boolean getManuallySetStatus() {
		return manuallySetStatus;
	}
	
	/**
	 * Used to return the cache question list saved locally 
	 * from the local manager
	 * @return a question list that is saved locally.
	 */
	public ArrayList<Question> getSavedQuestionList(){
		 return savedQuestionList;
		 //return localManager.loadProfile().getSavedQuestionList();
	}
	
	/**
	 * Used to return the favorite question list from
	 * the user
	 * @issue not implemented
	 * @return a question list that is added favorite by current user.
	 */
	public ArrayList<Question> getFavedQuestionList(){
		return favedQuestionList;
		//return localManager.loadProfile(AppCache.getInstance().getProfile().getUsername()).getFavedQuestionList();
	}
	
	/**
	 * Used to return the author question list from the local manager
	 * @return a question list that is composed by current user.
	 */
	
	public ArrayList<Question> getMyQuestionList(){
		return myQuestionList;
		//return localManager.loadProfile(AppCache.getInstance().getProfile().getUsername()).getMyQuestionList();
	}
	
	public ArrayList<Question> getTempQuestionList(){
		return tempQuestionList;
	}
	
	
	/**
	 * UI test method and returning question list token.
	 * @return a question list that is composed automatically.
	 */
	public ArrayList<Question> getTestQuestionList(int index){
		ArrayList<Question> testQuestionList = new ArrayList<Question>();
		for (int i = 0; i<5; i++){
			testQuestionList.add(ObjectFactory.initQuestion("Title" + index, "Body"+ index, "author"+ index, gpsLocation));
		}
		return testQuestionList;
	}
}
