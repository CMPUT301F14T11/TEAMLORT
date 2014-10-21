package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;
import java.util.HashSet;

import android.graphics.drawable.Drawable;

public class RepliableText extends TextPrimitive{
	private int score = 0;
	private Drawable picture = null;
	private ArrayList<Reply> replyList = new ArrayList<Reply>();
	//to keep track of who's voted what (hashed for quick lookup)
	private HashSet<String> voterSet = new HashSet<String>(); 
	
	public int getScore() {
		return score;
	}
	public Drawable getPicture() {
		return picture;
	}
	public ArrayList<Reply> getReplyList() {
		return replyList;
	}
	public HashSet<String> getVoterSet() {
		return voterSet;
	}
	/*I'd imagine the conroller upvote code should
	 *look like this psuedo code: 
	 *if(upvoter not in thing.voterList) {
	 * upvote
	 * }
	 * else {
	 *  unvote
	 * }
	 * because if they're upvoting and they are
	 * already IN the voterlist, that means they
	 * actually want to un-upvote the question
	 * */
	public void upVote(String username) {
		score++;
		voterSet.add(username);
	}
	public void unVote(String username) {
		score--;
		voterSet.remove(username);
	}
	public void addPicture(Drawable newPicture) {
		picture = newPicture;
	}
	public boolean hasPicture() {
		if (getPicture() == null) {
			return false;
		}
		return true;
	}
	public void deletePicture() {
		picture = null;
	}
	public void addReply(Reply reply) {
		replyList.add(reply);
	}
	public void deleteReply(Reply reply) {
		replyList.remove(reply);
	}
	public Reply getReply(int index) {
		return replyList.get(index);
	}
}
