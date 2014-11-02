package com.github.cmput301f14t11.teamlort;

public class ProfileController {
	private static Profile p = new Profile();

	public boolean isLogin() {
		if (p.getUsername().isEmpty() || p.getUsername() == null){
			return false;
		}
		else{
			
			return true;
		}
	}

	
	public void login(String name) {
		p.setUsername(name);
	}


	public void logout() {
		p.setUsername("");
		
	}

	public boolean addFavorite(Question q1) {
		if(isLogin()){
			if(!p.getFavedQuestionList().contains(q1)){
				p.getFavedQuestionList().add(q1);
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
	
	public boolean removeFavorite(Question q1) {
		if(isLogin()){
			return p.getFavedQuestionList().remove(q1);
		}
		else{
			System.out.print("No user logged in");
			return false;
		}
	}

	public boolean addCache(Question q1) {
		if(isLogin()){
			if(!p.getSavedQuestionList().contains(q1)){
				p.getSavedQuestionList().add(q1);
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
	
	public boolean removeCache(Question q1) {
		if(isLogin()){
			return p.getSavedQuestionList().remove(q1);
		}
		else{
			System.out.print("No user logged in");
			return false;
		}
	}
}
