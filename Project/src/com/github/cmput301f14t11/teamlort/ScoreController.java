package com.github.cmput301f14t11.teamlort;

public class ScoreController {

	public boolean checkIllegal(RepliableText rt) {
		
		return false;
	}

	public void addToVotedList(RepliableText rt, String string) {
		
		rt.getVoterSet().add(string);
		
	}

	public void increaseScore(RepliableText rt) {
		
		if(this.checkIllegal(rt)){
			//rt.upVote(username);
		}
		
	}

	public void decreaseScore(RepliableText rt) {
		if(this.checkIllegal(rt)){
			//rt.downVote(username);
		}
		
	}

	public void removeFromVotedList(RepliableText rt, String string) {
		try {rt.getVoterSet().remove(string);}
		catch (NullPointerException e){
			System.out.println(string+" is not in the voter list");
		}
		
	}
	

}
