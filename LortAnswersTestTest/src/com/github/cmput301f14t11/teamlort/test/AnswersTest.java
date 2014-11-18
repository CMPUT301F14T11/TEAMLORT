package com.github.cmput301f14t11.teamlort.test;

import com.github.cmput301f14t11.teamlort.Model.Answer;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import junit.framework.TestCase;

public class AnswersTest extends TestCase {
	
	public void testAnswerCreate(){
		Answer answer = new Answer();
	}
	
	public void testSetDescription(){
		String desc = "Put feathers on arms, flap arms";
		Answer answer = new Answer();
		answer.setBody(desc);
		assertTrue("Description doesn't match desc", answer.getBody() == desc);
	}
	
	public void testSetAuthor(){
		String username = "SIVLEOL";
		Answer answer = new Answer();
		answer.setAuthor(username);
		assertTrue("Author doesn't match username", answer.getAuthor() == username);
	}
	
	public void testUpvote(){
		String username = "SIVLEOL";
		Answer answer = new Answer();
		assertTrue("question initialized with more than 0 upvotes", answer.getScore() == 0);
		answer.upVote(username);
		assertTrue("Upvote did not change score", answer.getScore() == 1);
		answer.upVote(username);
		assertTrue("Same user managed to upvote same question twice", answer.getScore() == 1);
	}
	
	public void testDate(){
		Answer answer = new Answer();
		assertTrue("question date is null", answer.getTime() != null);
	}
	
	public void testANSPIC()
	{
		Answer answer = new Answer();
		Bitmap image = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888 ); 
		Drawable drawable = new BitmapDrawable(image);
 		answer.addPicture(drawable); 
		assertTrue("picture file size too large", ((BitmapDrawable) answer.getPicture()).getBitmap().getByteCount() <= 64);
	}

}
