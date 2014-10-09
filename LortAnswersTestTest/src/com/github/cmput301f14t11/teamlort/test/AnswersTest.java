package com.github.cmput301f14t11.teamlort.test;

import junit.framework.TestCase;

public class AnswersTest extends TestCase {
	
	public void testAnswerCreate(){
		Answer answer = new Answer();
	}
	
	public void testSetDescription(){
		String desc = "Put feathers on arms, flap arms";
		Answer answer = new Answer();
		answer.setDescription(desc);
		assertTrue("Description doesn't match desc", answer.getDescription() == desc);
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
		answer.upvote(username);
		assertTrue("Upvote did not change score", answer.getScore() == 1);
		answer.upvote(username);
		assertTrue("Same user managed to upvote same question twice", answer.getScore() == 1);
	}
	
	public void testDownvote(){
		String username = "SIVLEOL";
		Answer answer = new Answer();
		answer.downvote(username);
		assertTrue("Downvote did not change score", answer.getScore() == -1);
		answer.downvote(username);
		assertTrue("Same user managed to downvote same question twice", answer.getScore() == -1);
		answer.upvote(username);
		assertTrue("User was unable to re-upvote after downvoting", answer.getScore() == 0);
		answer.upvote(username);
		assertTrue("Upvote did not change score after canceling downvote", answer.getScore() == 1);
	}
	
	public void testDate(){
		Answer answer = new Answer();
		assertTrue("question date is null", answer.getDate() != null);
	}
	
	public void testANSPIC()
	{
		Answer answer = new Answer();
		private Bitmap image = new Bitmap();//this is not how bitmap is set up,more work needs to be done to make a proper bitmap
		answer.addpic(i);
		assertTrue("picture file size too large",answer.getpic().getAllocationByteCount () <= 64);
	}

}
