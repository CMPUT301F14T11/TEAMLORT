package com.github.cmput301f14t11.teamlort.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import android.location.LocationManager;
import android.util.Log;

import com.github.cmput301f14t11.teamlort.Controller.LocationController;

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
	
	protected ArrayList<Question> savedQuestionList = null;
	protected ArrayList<Question> favedQuestionList = null;
	protected ArrayList<Question> myQuestionList = null;
	protected ArrayList<Question> tempQuestionList = null;
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
	
	/**
	 * record the user's current location into the profile
	 * @param latitude 
	 * @param longitude
	 */
	public void setLocation(double latitude, double longitude) {
		if(getLocationService()) {
			this.gpsLocation = new GpsLocation(latitude, longitude);
		}
		else {
			this.gpsLocation = new GpsLocation(0,0);
		}
	}
	
	/**
	 * get user's current location in the profile
	 * @param locationManager android routine service 
	 * @return location point
	 */
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
					Log.i("returngps","blank gps sent - gps null");
					return new GpsLocation(0,0);
				}
			}
		} else {
			Log.i("returngps","location services off");
			return new GpsLocation(0,0);
		}
	}
	
	/**
	 * set flag of requesting the location service
	 * @param status
	 */
	public void setLocationServices(boolean status) {
		this.locationService = status;
	}
	
	/**
	 * return the flag of requesting location service
	 * @return
	 */
	public boolean getLocationService() {
		return locationService;
	}
	
	/**
	 * set the flag of setting location manually
	 * @param status
	 */
	public void locationSetManually(boolean status) {
		this.manuallySetStatus = status;
	}
	
	/**
	 * get the flag of setting location manually
	 * @return
	 */
	public boolean getManuallySetStatus() {
		return manuallySetStatus;
	}
	
	/**
	 * Used to return the cache question list saved locally 
	 * from the local manager
	 * @return a question list that is saved locally.
	 */
	public ArrayList<Question> getSavedQuestionList(){
		if(AppCache.getInstance().getProfile().savedQuestionList == null){
			AppCache.getInstance().getProfile().savedQuestionList = new ArrayList<Question>();
			return AppCache.getInstance().getProfile().savedQuestionList;
		}
		else{
			return AppCache.getInstance().getProfile().savedQuestionList;
		}
	}
	
	/**
	 * Used to return the favorite question list from
	 * the user
	 * @issue not implemented
	 * @return a question list that is added favorite by current user.
	 */
	public ArrayList<Question> getFavedQuestionList(){

		if(AppCache.getInstance().getProfile().favedQuestionList == null){
			AppCache.getInstance().getProfile().favedQuestionList = new ArrayList<Question>();
			return AppCache.getInstance().getProfile().favedQuestionList;
		}
		else{
			return AppCache.getInstance().getProfile().favedQuestionList;
		}
	}
	
	/**
	 * Used to return the author question list from the local manager
	 * @return a question list that is composed by current user.
	 */
	
	public ArrayList<Question> getMyQuestionList(){
		if(AppCache.getInstance().getProfile().myQuestionList == null){
			AppCache.getInstance().getProfile().myQuestionList = new ArrayList<Question>();
			return AppCache.getInstance().getProfile().myQuestionList;
		}
		else{
			return AppCache.getInstance().getProfile().myQuestionList;
		}
	}
	
	/**
	 * Used to return unsynchronized question list from the local manager
	 * @return a question list that is composed by current user.
	 */
	public ArrayList<Question> getTempQuestionList(){
		if(AppCache.getInstance().getProfile().tempQuestionList == null){
			AppCache.getInstance().getProfile().tempQuestionList = new ArrayList<Question>();
			return AppCache.getInstance().getProfile().tempQuestionList;
		}
		else{
			return AppCache.getInstance().getProfile().tempQuestionList;
		}
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
	
	/**
	 * used to check if the given question is already in the faved question list
	 * @param questionID the given question id
	 * @return boolean indicated whether the question is in the list or not
	 */
	public boolean containsFavedQuestion(int questionID){
		ArrayList<Question> favedQuestions = getFavedQuestionList();
		for (Question question : favedQuestions){
			if(question.getID() == questionID){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * used to check if the given question is already in the saved question list
	 * @param questionID the given question id
	 * @return boolean indicated whether the question is in the list or not
	 */
	public boolean containsSavedQuestion(int questionID){
		ArrayList<Question> savedQuestions = getSavedQuestionList();
		for (Question question : savedQuestions){
			if(question.getID() == questionID){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * used to remove the given question from the faved question list
	 * @param questionID the given question id
	 */
	public void removeFavedQuestion(int questionID){
		ArrayList<Question> favedQuestions = getFavedQuestionList();
		for (Question question : favedQuestions){
			if(question.getID() == questionID){
				this.favedQuestionList.remove(question);
				return;
			}
		}
	}
	
	/**
	 * used to remove the given question from the faved question list
	 * @param questionID the given question id
	 */
	public void removeSavedQuestion(int questionID){
		ArrayList<Question> savedQuestions = getSavedQuestionList();
		for (Question question : savedQuestions){
			if(question.getID() == questionID){
				this.savedQuestionList.remove(question);
				return;
			}
		}
	}


	
}
