package com.github.cmput301f14t11.teamlort.Model;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashSet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.github.cmput301f14t11.teamlort.Listener;

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
	
	private transient Drawable picture = null;
	private byte[] pictureData = new byte[0];
	
	private ArrayList<Reply> replyList = new ArrayList<Reply>();
	//to keep track of who's voted what (hashed for quick lookup)
	
	private HashSet<String> voterSet = new HashSet<String>(); 
	
	private transient Listener listener = new Listener(){

		@Override
		public void update() {
			// Default listener that does nothing.
		}
		
	};
	

	
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
		if (picture == null && (pictureData.length != 0))
		{
			picture = new BitmapDrawable(BitmapFactory.decodeByteArray(pictureData, 0, pictureData.length));
		}
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
		notifyListener();
	}
	/**
	 * @param username
	 * Reduces the score and removes the user from the voterset
	 */
	public void unVote(String username) {
		score--;
		voterSet.remove(username);
		notifyListener();
	}
	/**
	 * @param newPicture sets the drawable to the picture
	 */
	public void addPicture(Drawable newPicture) {
		picture = newPicture;
		
		if (newPicture != null)
		{
			Bitmap bitmap = ((BitmapDrawable) newPicture).getBitmap();
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			
			pictureData = stream.toByteArray();
		}
		else
		{
			pictureData = new byte[0];
		}
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
		notifyListener();
	}
	public void addReply(Reply reply) {
		replyList.add(reply);
		notifyListener();
	}
	public void addReplyToStart(Reply reply){
		replyList.add(0, reply);
		notifyListener();
	}
	public void deleteReply(Reply reply) {
		replyList.remove(reply);
		notifyListener();
	}
	/**
	 * @param index
	 * @return the object at index
	 */
	public Reply getReply(int index) {
		return replyList.get(index);
	}
	
	public void setListener(Listener listener) {
		this.listener = listener;
	}
	
	public void notifyListener(){
		listener.update();
	}
	

	
}
