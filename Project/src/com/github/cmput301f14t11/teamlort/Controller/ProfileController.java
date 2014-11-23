package com.github.cmput301f14t11.teamlort.Controller;

import java.util.Observable;

import com.github.cmput301f14t11.teamlort.Model.AppCache;
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
	public boolean addFavedQuestion(Question q1) {
		
			if(!getP().getFavedQuestionList().contains(q1)){
				getP().getFavedQuestionList().add(q1);
				triggleObservers();
				return true;}
			else{
				return false;
			}
		
		
	}
	
	/**
	 * remove favorite question from the favorite question list in the profile
	 * @param q1 question that will be removed
	 */
	public void removeFavedQuestion(Question q1) {
			getP().getFavedQuestionList().remove(q1);
			triggleObservers();
		
	}
	/**
	 * add a question into the local file (saved question list) in the profile
	 * @param q1 question that will be added
	 * @return boolean indicated whether the operation it is success or not
	 */
	public boolean addSavedQuestion(Question q1) {
			if(!getP().getSavedQuestionList().contains(q1)){
				getP().getSavedQuestionList().add(q1);
				triggleObservers();
				return true;}
			else{
				return false;
			}
		
	}
	/**
	 * remove a question from the local file (saved question list) in the profile
	 * @param q1 question that will be removed
	 */
	public void removeSavedQuestion(Question q1) {

			getP().getSavedQuestionList().remove(q1);
			triggleObservers();

	}

	/**
	 * add the authors' questions into the local question list saved in the profile
	 * @param q1 question that will be added
	 * @return boolean indicated whether the operation it is success or not
	 */
	public boolean addCreatedQuestion(Question q1) {

		if(!getP().getMyQuestionList().contains(q1)){
			getP().getMyQuestionList().add(q1);
			triggleObservers();
			return true;}
		else{
			return false;
		}
			
		
	}

	/**
	 * remove the authors' question from the local file (my question list) in the profile
	 * @param q1 question that will be removed
	 */
	public void removeCreatedQuestion(Question q1) {

			getP().getMyQuestionList().remove(q1);
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

}