package com.github.cmput301f14t11.teamlort;


/**
 * Profile controller will modify the profile class 
 * @author Hang_Peng
 */
public class ProfileController {
	private static Profile p = new Profile();
	
	
	/**
	 * Sets the {@link Profile} in ProfileController to the instance in AppCache
	 */
	public ProfileController() {
		p = AppCache.getInstance().getProfile();
	}
	
	/**
	 * 
	 * @return the {@link Profile} from AppCache
	 */
	public Profile getProfile() {
		return p;
	}
	
	/**
	 * add favorite question into the favorite question list in the profile
	 * @param q1 question that will be added
	 * @return boolean indicated whether the operation it is success or not
	 */
	public boolean addFavedQuestion(Question q1) {
		if(p.isLogin()){
			if(!p.getFavedQuestionList().contains(q1)){
				p.getFavedQuestionList().add(q1);
				return true;}
			else{
				return false;
			}
		}
		else{
			return false;
		}
		
	}
	
	/**
	 * remove favorite question from the favorite question list in the profile
	 * @param q1 question that will be removed
	 * @return boolean indicated whether the operation it is success or not
	 */
	public boolean removeFavedQuestion(Question q1) {
		if(p.isLogin()){
			return p.getFavedQuestionList().remove(q1);
		}
		else{
			return false;
		}
	}
	/**
	 * add a question into the local file (saved question list) in the profile
	 * @param q1 question that will be added
	 * @return boolean indicated whether the operation it is success or not
	 */
	public boolean addSavedQuestion(Question q1) {
		if(p.isLogin()){
			if(!p.getSavedQuestionList().contains(q1)){
				p.getSavedQuestionList().add(q1);
				return true;}
			else{
				return false;
			}
		}
		else{
			return false;
		}
		
	}
	/**
	 * remove a question from the local file (saved question list) in the profile
	 * @param q1 question that will be removed
	 * @return boolean indicated whether the operation it is success or not
	 */
	public boolean removeSavedQuestion(Question q1) {
		if(p.isLogin()){
			return p.getSavedQuestionList().remove(q1);
		}
		else{
			return false;
		}
	}

	/**
	 * add the authors' questions into the local question list saved in the profile
	 * @param q1 question that will be added
	 * @return boolean indicated whether the operation it is success or not
	 */
	public boolean addCreatedQuestion(Question q1) {
		if(p.isLogin()){
			return p.getMyQuestionList().add(q1);
		}
		else{
		return false;
		}
	}

	/**
	 * remove the authors' question from the local file (my question list) in the profile
	 * @param q1 question that will be removed
	 * @return boolean indicated whether the operation it is success or not
	 */
	public boolean removeCreatedQuestion(Question q1) {
		if(p.isLogin()){
			if(p.getMyQuestionList().contains(q1)){
				return p.getMyQuestionList().remove(q1);
			}
			return false;
		}
		return false;
	}

}