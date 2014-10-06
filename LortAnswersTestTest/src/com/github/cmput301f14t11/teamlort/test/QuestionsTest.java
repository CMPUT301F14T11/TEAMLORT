package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import junit.framework.TestCase;

public class QuestionsTest extends TestCase {

	public void testQuestionCreate(){
		Question question = new Question();
	}
	
	public void testSetTitle(){
		String title = "How do I fly?";
		Question question = new Question();
		question.setTitle(title);
		assertTrue("title doesn't match title", question.getTitle() == title);
	}
	
	public void testSetDescription(){
		String desc = "I have some feathers halp";
		Question question = new Question();
		question.setDescription(desc);
		assertTrue("Description doesn't match desc", question.getDescription() == desc);
	}
	
	public void testSetAuthor(){
		String username = "SIVLEOL";
		Question question = new Question();
		question.setAuthor(username);
		assertTrue("Author doesn't match username", question.getAuthor() == username);
	}
	
	public void testUpvote(){
		String username = "SIVLEOL";
		Question question = new Question();
		assertTrue("question initialized with more than 0 upvotes", question.getScore() == 0);
		question.upvote(username);
		assertTrue("Upvote did not change score", question.getScore() == 1);
		question.upvote(username);
		assertTrue("Same user managed to upvote same question twice", question.getScore() == 1);
	}
	
	public void testDownvote(){
		String username = "SIVLEOL";
		Question question = new Question();
		question.downvote(username);
		assertTrue("Downvote did not change score", question.getScore() == -1);
		question.downvote(username);
		assertTrue("Same user managed to downvote same question twice", question.getScore() == -1);
		question.upvote(username);
		assertTrue("User was unable to re-upvote after downvoting", question.getScore() == 0);
		question.upvote(username);
		assertTrue("Upvote did not change score after canceling downvote", question.getScore() == 1);
	}
	
	public void testDate(){
		Question question = new Question();
		assertTrue("question date is null", question.getDate() != null);
	}
	
	public void testAddAnswer(){
		Question question = new Question();
		Answer answer = new Answer();
		question.addAnswer(answer);
		ArrayList<Answer> list1 = question.getAnswers();
		assertTrue("Question does not have answer", list1.get(0) == answer);
	}
	
	public void testRemoveAnswer(){
		Question question = new Question();
		Answer answer = new Answer();
		question.addAnswer(answer);
		question.removeAnswer(answer);
		ArrayList<Answer> list1 = question.getAnswers();
		assertTrue("Question remove failed", list1.size() == 0);
	}
	
	public void testTetAnswers(){
		Question question = new Question();
		Answer answer = new Answer();
		ArrayList<Answer> list1 = question.getAnswers();
		assertTrue("getAnswer() returned > 0 when should be empty", list1.size() == 0);
		question.addAnswer(answer);
		assertTrue("getAnswer() did not return list with answer", list1.get(0) == answer);
	}
	
}
