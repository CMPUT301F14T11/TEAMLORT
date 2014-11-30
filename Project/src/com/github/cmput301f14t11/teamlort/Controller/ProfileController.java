package com.github.cmput301f14t11.teamlort.Controller;

import java.util.Observable;

import com.github.cmput301f14t11.teamlort.Model.AppCache;
import com.github.cmput301f14t11.teamlort.Model.LocalManager;
import com.github.cmput301f14t11.teamlort.Model.Profile;
import com.github.cmput301f14t11.teamlort.Model.Question;


/**
 * Profile controller will modify the profile class 
 * @author Hang_Peng
 */
public class ProfileController extends Observable{
	private static Profile p = new Profile();
	
	
	/**
	 * Sets the {@link Profile} in ProfileController to the instance in AppCache
	 */
	public ProfileController() {
		setP(AppCache.getInstance().getProfile());
	}
	
	/**
	 * 
	 * @return the {@link Profile} from AppCache
	 */
	public Profile getProfile() {
		return getP();
	}
	
	/**
	 * add favorite question into the favorite question list in the profile
	 * @param q1 question that will be added
	 * @return boolean indicated whether the operation it is success or not
	 */
	 public void addFavedQuestion(Question q1) {
		
			if(!AppCache.getInstance().getProfile().getFavedQuestionList().contains(q1)){
				
				AppCache.getInstance().getProfile().getFavedQuestionList().add(q1);
				save();
				triggleObservers();
				
			}
		
	}
	
	/**
	 * remove favorite question from the favorite question list in the profile
	 * @param q1 question that will be removed
	 */
	public void removeFavedQuestion(Question q1) {
			AppCache.getInstance().getProfile().removeFavedQuestion(q1.getID());
			save();
			triggleObservers();
		
	}
	/**
	 * add a question into the local file (saved question list) in the profile
	 * @param q1 question that will be added
	 * @return boolean indicated whether the operation it is success or not
	 */
	public void addSavedQuestion(Question q1) {
		if(!AppCache.getInstance().getProfile().getSavedQuestionList().contains(q1)){
			AppCache.getInstance().getProfile().getSavedQuestionList().add(q1);
			save();
			triggleObservers();
		}
		
		
	}
	/**
	 * remove a question from the local file (saved question list) in the profile
	 * @param q1 question that will be removed
	 */
	public void removeSavedQuestion(Question q1) {

		AppCache.getInstance().getProfile().removeSavedQuestion(q1.getID());
		save();
		triggleObservers();

	}

	/**
	 * add the authors' questions into the local question list saved in the profile
	 * @param q1 question that will be added
	 * @return boolean indicated whether the operation it is success or not
	 */
	public void addCreatedQuestion(Question q1) {

		if(!AppCache.getInstance().getProfile().getMyQuestionList().contains(q1)){
			AppCache.getInstance().getProfile().getMyQuestionList().add(q1);
			save();
			triggleObservers();
			
		}
			
		
	}

	/**
	 * remove the authors' question from the local file (my question list) in the profile
	 * @param q1 question that will be removed
	 */
	public void removeCreatedQuestion(Question q1) {

		AppCache.getInstance().getProfile().getMyQuestionList().remove(q1);
		save();
		triggleObservers();
	}
	
	
	public void addTempQuestion(Question q1) {

		if(!AppCache.getInstance().getProfile().getTempQuestionList().contains(q1)){
			AppCache.getInstance().getProfile().getTempQuestionList().add(q1);
			save();
			triggleObservers();
			
		}
		
	}

	public void removeTempQuestion(Question q1) {

		AppCache.getInstance().getProfile().getTempQuestionList().remove(q1);
		save();
		triggleObservers();
	}
	

	public static Profile getP() {
		return p;
	}

	public static void setP(Profile p) {
		ProfileController.p = p;
	}
	
	private void triggleObservers(){
		setChanged();
		notifyObservers();
	}
	
	private void save(){
		LocalManager.getManager().saveProfile(AppCache.getInstance().getProfile());
	}

}