package com.github.cmput301f14t11.teamlort.Controller;

import com.github.cmput301f14t11.teamlort.Model.PersistentDataManager;
import com.github.cmput301f14t11.teamlort.Model.Question;

public class UserController {
	private static PersistentDataManager pdm = PersistentDataManager.getInstance();
	//private static String username = pdm.getUsername();
	
	// isLogin() is used to check if there is a user logging in or not
	public boolean isLogin() {
		if (pdm.getUsername().isEmpty()){
			return false;
		}
		else{
			
			return true;
		}
	}

	// logging in by setting the username to given name
	// in Persistent Data Manager
	public void login(String name) {
		pdm.setUsername(name);
	}

	// logging out by setting the username to empty string
	// in Persistent Data Manager
	public void logout() {
		pdm.setUsername("");
		
	}

	// adding and saving the favorite question into the PDM
	public boolean addFavorite(Question q1) {
		if(isLogin()){
			if(!pdm.getFavedQuestions().contains(q1)){
				pdm.getFavedQuestions().add(q1);
				return true;}
			else{
				System.out.print("The question is already in the favorite question list");
				return false;
			}
		}
		else{
			System.out.print("No user logged in");
			return false;
		}
		
	}
	
	// removing the favorite question from the PDM
	public boolean removeFavorite(Question q1) {
		if(isLogin()){
			return pdm.getFavedQuestions().remove(q1);
		}
		else{
			System.out.print("No user logged in");
			return false;
		}
	}

	// adding and saving the saved question into the PDM
	public boolean addCache(Question q1) {
		if(isLogin()){
			if(!pdm.getSavedQuestions().contains(q1)){
				pdm.getSavedQuestions().add(q1);
				return true;}
			else{
				System.out.print("The question is already in the saved question list");
				return false;
			}
		}
		else{
			System.out.print("No user logged in");
			return false;
		}
		
	}
	// removing the saved question from the PDM
	public boolean removeCache(Question q1) {
		if(isLogin()){
			return pdm.getSavedQuestions().remove(q1);
		}
		else{
			System.out.print("No user logged in");
			return false;
		}
	}



}
