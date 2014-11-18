package com.github.cmput301f14t11.teamlort.Controller;

import com.github.cmput301f14t11.teamlort.Model.PersistentDataManager;
import com.github.cmput301f14t11.teamlort.Model.RepliableText;

public class ScoreController {
	
	private static PersistentDataManager pdm = PersistentDataManager.getInstance();
	private static String username = pdm.getUsername();

	public boolean checkIllegal(RepliableText rt) {
		if(username.isEmpty()){
			return false;
		}
		else{
			if(rt.getVoterSet().contains(username) || rt.getAuthor().equals(username)){
				return false;
			}
			else{
				return true;
			}
		}
	}

	public void addToVotedList(RepliableText rt, String string) {
		
		rt.getVoterSet().add(string);
		
	}

	public void increaseScore(RepliableText rt) {
		
		if(this.checkIllegal(rt)){
			rt.upVote(username);
		}
		
	}

	public void removeFromVotedList(RepliableText rt, String string) {
		try {rt.getVoterSet().remove(string);}
		catch (NullPointerException e){
			System.out.println(string+" is not in the voter list");
		}
		
	}

	

}
