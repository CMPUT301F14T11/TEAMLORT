package com.github.cmput301f14t11.teamlort.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import junit.framework.Assert;
import junit.framework.TestCase;
import android.media.Image;

public class TestCase5to8
extends TestCase
{
	public void testSanity()
	{
		Assert.assertEquals(0, 0);
	}
	
	public void testCaseFive()
	{
		PersistentDataManager data = PersistentDataManager.getInstance();
		ObjectFactory inputManager = new ObjectFactory();
		
		// The user creates a question. Here the views (EditTexts) should have
		// the strings, but since that's not done we'll just make them up.
		
		String title = "I have a question about things.";
		String detail = "My thing with the stuff isn't working properly. Help? (See pic).";
		
		Question q1 = new Question(title, detail);
		
		inputManager.addQuestion(q1);
		
		Assert.assertNotNull(
				data.getQuestion(q1.id())
				);
		
		// Assume we've navigated to the question somehow.
		
		// Now make an answer.
		String answer1 = "Elaborate please? (Also you don't have a pic).";
		String answer2 = "tl;dr";
		
		Answer a1 = new Answer(answer1);
		Answer a2 = new Answer(answer2);
		
		// Get the data controller to add the answers.
		inputManager.answer(q1.id(), a1);
		inputManager.answer(q1.id(), a2);
		
		// Make sure they're there.
		Collection<Answer> answers = data.getAnswers(q1.id());
		
		Assert.assertEquals(2, answers.size());
		Assert.assertEquals(answer1, answers.get(0).getText());
		Assert.assertEquals(answer1, answers.get(1).getText());
	}
	
	public void testCaseSix()
	{
		PersistentDataManager data = PersistentDataManager.getInstance();
		ObjectFactory inputManager = new ObjectFactory();
		
		// This portion is the same as testCase5 -- make the Q&A
		String title = "I have a question about things.";
		String detail = "My thing with the stuff isn't working properly. Help? (See pic).";
		Question q1 = new Question(title, detail);
		inputManager.addQuestion(q1);
		
		String answer1 = "Elaborate please? (Also you don't have a pic).";
		String answer2 = "tl;dr";
		Answer a1 = new Answer(answer1);
		Answer a2 = new Answer(answer2);
		inputManager.answer(q1.id(), a1);
		inputManager.answer(q1.id(), a2);
		
		// Now add a response to each.
		inputManager.replyTo(q1.id(), "Sorry. I meant where's the washroom in here?");
		
		// We may need a separate method here since the IDs between questions and
		// replies are not necessarily unique.
		inputManager.replyToAnswer(q1.id(), a1.id(), "This answer sucks. Doesn't answer the question.");
		inputManager.replyToAnswer(q1.id(), a2.id(), "lol");
		
		// Make sure they're there.
		Question myQuestion = data.getQuestion(q1.id());
		Collection<Answer> myAnswers = data.getAnswers(q1.id());
		
		ArrayList<Reply> replies;
		
		replies.addAll(myQuestion.getReplies());
		for (Answer a : myAnswers)
			replies.addAll(a.getReplies());
		
		Assert.assertEquals(3, replies.size());

		// Might not be ordered. TODO: Brandon
		Assert.assertEquals("Sorry. I meant where's the washroom in here?", replies.get(0));
		Assert.assertEquals("This answer sucks. Doesn't answer the question.", replies.get(1));
		Assert.assertEquals("lol", replies.get(2));
	}
	
	public void testCaseSeven()
	{
		PersistentDataManager data = PersistentDataManager.getInstance();
		ObjectFactory inputManager = new ObjectFactory();
		
		// This portion is the same as testCase5 -- make the Q&A
		// Except we want to add a picture to the Q&A this time.
		String title = "I have a question about things.";
		String detail = "My thing with the stuff isn't working properly. Help? (See pic).";
		
		Image img1 = ImageIO.read(new File("strawberry.jpg"));
		Image img2 = ImageIO.read(new File("banana.jpg"));
		Image img3 = ImageIO.read(new File("orange.jpg"));
		
		Question q1 = new Question(title, detail, img1);
		inputManager.addQuestion(q1);
		
		String answer1 = "Elaborate please?";
		String answer2 = "tl;dr";
		Answer a1 = new Answer(answer1, img2);
		Answer a2 = new Answer(answer2, img3);
		inputManager.answer(q1.id(), a1);
		inputManager.answer(q1.id(), a2);
		
		// Make sure they're there.
		Question myQuestion = data.getQuestion(q1.id());
		Collection<Answer> myAnswers = data.getAnswers(q1.id());
		
		Assert.assertNotNull(myQuestion.getImg());
		Assert.assertNotNull(myAnswers.get(0).getImg());
		Assert.assertNotNull(myAnswers.get(0).getImg());
	}
}
