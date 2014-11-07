package com.github.cmput301f14t11.teamlort.test;

import junit.framework.Assert;
import android.graphics.drawable.Drawable;
import android.test.ActivityInstrumentationTestCase2;

import com.github.cmput301f14t11.teamlort.Answer;
import com.github.cmput301f14t11.teamlort.AnswerController;
import com.github.cmput301f14t11.teamlort.HomeActivity;
import com.github.cmput301f14t11.teamlort.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Question;
import com.github.cmput301f14t11.teamlort.QuestionController;
import com.github.cmput301f14t11.teamlort.Reply;

public class TestCase5to8
extends ActivityInstrumentationTestCase2<HomeActivity>
{
	public TestCase5to8(Class<HomeActivity> activityClass)
	{
		super(activityClass);
		
	}

	public void testSanity()
	{
		Assert.assertEquals(0, 0);
	}
	
	public void testCaseFive()
	{
		QuestionController qController = new QuestionController();
		AnswerController   aController = new AnswerController();
		
		// The user creates a question. Here the views (EditTexts) should have
		// the strings, but since that's not done we'll just make them up.
		
		String author1 = "Dingo Prime";
		String author2 = "Bingo Prime";
		String author3 = "Ringo Prime";
		
		String title = "I have a question about things.";
		String detail = "My thing with the stuff isn't working properly. Help? (See pic).";
		
		Question q1 = ObjectFactory.initQuestion(title, detail, author1);
		
		qController.addQuestion(q1);
		
		/*
		Assert.assertNotNull(
				data.getQuestion(q1.id())
				);*/
		
		// Suppose we've navigated to the question somehow.
		
		// Now make an answer.
		String answer1 = "Elaborate please? (Also you don't have a pic).";
		String answer2 = "tl;dr";
		
		Answer a1 = ObjectFactory.initAnswer(answer1, author2);
		Answer a2 = ObjectFactory.initAnswer(answer2, author3);
		
		// Get the data controller to add the answers.
		aController.addAnswer(a1, q1.getID());
		aController.addAnswer(a2, q1.getID());
		
		// Make sure they're there.
		/*
		Collection<Answer> answers = data.getAnswers(q1.id());
		
		Assert.assertEquals(2, answers.size());
		Assert.assertEquals(answer1, answers.get(0).getText());
		Assert.assertEquals(answer1, answers.get(1).getText());
		*/
	}
	
	public void testCaseSix()
	{
		QuestionController qController = new QuestionController();
		AnswerController   aController = new AnswerController();
		
		// This part is just like test case 5.
		// Create a question with some answers
		String author1 = "Dingo Prime";
		String author2 = "Bingo Prime";
		String author3 = "Ringo Prime";
		String title = "I have a question about things.";
		String detail = "My thing with the stuff isn't working properly. Help? (See pic).";
		String answer1 = "Elaborate please? (Also you don't have a pic).";
		String answer2 = "tl;dr";
		
		Question q1 = ObjectFactory.initQuestion(title, detail, author1);
		qController.addQuestion(q1);
		Answer a1 = ObjectFactory.initAnswer(answer1, author2);
		Answer a2 = ObjectFactory.initAnswer(answer2, author3);
		aController.addAnswer(a1, q1.getID());
		aController.addAnswer(a2, q1.getID());
		
		// Now add a response to each.
		Reply r1 = ObjectFactory.initReply("Sorry. I meant where's the washroom in here?", author1);
		Reply r2 = ObjectFactory.initReply("This answer sucks. Doesn't answer the question.", author1);
		Reply r3 = ObjectFactory.initReply("lol", author2);
		
		qController.addReply(r1, q1.getID());
		aController.addReply(r2, q1.getID(), a1.getID());
		aController.addReply(r3, q1.getID(), a2.getID());
		
		// Make sure they're there.
		/*
		Question myQuestion = data.getQuestion(q1.id());
		Collection<Answer> myAnswers = data.getAnswers(q1.id());
		
		ArrayList<Reply> replies;
		
		replies.addAll(myQuestion.getReplies());
		for (Answer a : myAnswers)
			replies.addAll(a.getReplies());
		
		Assert.assertEquals(3, replies.size());

		Assert.assertEquals("Sorry. I meant where's the washroom in here?", replies.get(0));
		Assert.assertEquals("This answer sucks. Doesn't answer the question.", replies.get(1));
		Assert.assertEquals("lol", replies.get(2));*/
	}
	
	public void testCaseSeven()
	{
		QuestionController qController = new QuestionController();
		AnswerController   aController = new AnswerController();
		
		// This part is just like test case 5.
		// Create a question with some answers
		String author1 = "Dingo Prime";
		String author2 = "Bingo Prime";
		String author3 = "Ringo Prime";
		String title = "I have a question about things.";
		String detail = "My thing with the stuff isn't working properly. Help? (See pic).";
		String answer1 = "Elaborate please? (Also you don't have a pic).";
		String answer2 = "tl;dr";
		Drawable img1;
		Drawable img2;
		
		Question q1 = ObjectFactory.initQuestion(title, detail, author1, img1);
		qController.addQuestion(q1);
		Answer a1 = ObjectFactory.initAnswer(answer1, author2);
		Answer a2 = ObjectFactory.initAnswer(answer2, author3);
		aController.addAnswer(a1, q1.getID());
		aController.addAnswer(a2, q1.getID());
		
		// Make sure they're there.
		/*
		Question myQuestion = data.getQuestion(q1.id());
		Collection<Answer> myAnswers = data.getAnswers(q1.id());
		
		Assert.assertNotNull(myQuestion.getImg());
		Assert.assertNotNull(myAnswers.get(0).getImg());
		Assert.assertNotNull(myAnswers.get(0).getImg());
		*/
	}
	
	public void testCaseEight()
	{
		
	}
}
