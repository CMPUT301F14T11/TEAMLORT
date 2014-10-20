package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;
import java.util.Set;

import android.graphics.drawable.Drawable;

public class RepliableText extends TextPrimitive{
	private int score;
	private Drawable picture;
	private ArrayList<Reply> replyList;
	private Set<String> voterSet; 
	
	public int getScore() {
		return score;
	}
	public Drawable getPicture() {
		return picture;
	}
	public ArrayList<Reply> getReplies() {
		return replyList;
	}
	public Set<String> getVoterSet() {
		return voterSet;
	}
	public void upVote(String username) {
		score++;
//		try {
//			voterSet.add(username);
//		}
//		catch Exception e {
//			
//		}
	}
	public void downVote(String username) {
		score--;
		voterSet.add(username);
	}
}
