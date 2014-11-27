package com.github.cmput301f14t11.teamlort.Model;

import java.util.ArrayList;
import java.util.HashSet;

import com.github.cmput301f14t11.teamlort.Listener;

import android.graphics.drawable.Drawable;

/**
 * @author  eunderhi
 * 
 */
public class RepliableText extends TextPrimitive{
	/**
	 * All objects that have replies and upvotes inherit from this
	 * object
	 */
	private static final long serialVersionUID = 1L;
	
	private int score = 0;
	
	private Drawable picture = null;
	
	private ArrayList<Reply> replyList = new ArrayList<Reply>();
	//to keep track of who's voted what (hashed for quick lookup)
	
	private HashSet<String> voterSet = new HashSet<String>(); 
	
	private transient Listener listener;
	
	private GpsLocation GpsLocation = null;
	
	/**
	 * @return Returns the score of the question/answer
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @return Returns the picture/null if there isn't one
	 */
	public Drawable getPicture() {
		return picture;
	}
	/**
	 * @return Gets the arraylist of replies to the object
	 */
	public ArrayList<Reply> getReplyList() {
		return replyList;
	}
	/**
	 * @return Returns the HashSet containing everyone who's voted 
	 * on this question
	 */
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
	
	/**
	 * @param username
	 * Increases the score, and adds the user to the voterSet
	 * which keeps track of all voters of the question
	 */
	public void upVote(String username) {
		score++;
		voterSet.add(username);
	}
	/**
	 * @param username
	 * Reduces the score and removes the user from the voterset
	 */
	public void unVote(String username) {
		score--;
		voterSet.remove(username);
	}
	/**
	 * @param newPicture sets the drawable to the picture
	 */
	public void addPicture(Drawable newPicture) {
		picture = newPicture;
	}
	/**
	 * @return returns a boolean based on whether or not
	 * the object has a picture
	 */
	public boolean hasPicture() {
		if (getPicture() == null) {
			return false;
		}
		return true;
	}
	/**
	 *Deletes the current picture 
	 */
	public void deletePicture() {
		picture = null;
	}
	public void addReply(Reply reply) {
		replyList.add(reply);
	}
	public void addReplyToStart(Reply reply){
		replyList.add(0, reply);
	}
	public void deleteReply(Reply reply) {
		replyList.remove(reply);
	}
	/**
	 * @param index
	 * @return the object at index
	 */
	public Reply getReply(int index) {
		return replyList.get(index);
	}
	
	public void setLocation(GpsLocation GpsLocation) {
		this.GpsLocation = GpsLocation;
	}
	
	public GpsLocation getLocation() {
		return GpsLocation;
	}
	
}
