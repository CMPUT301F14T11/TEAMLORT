package com.github.cmput301f14t11.teamlort.test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import junit.framework.Assert;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.test.ActivityInstrumentationTestCase2;

import com.github.cmput301f14t11.teamlort.AppBaseActivity;
import com.github.cmput301f14t11.teamlort.Controller.AnswerController;
import com.github.cmput301f14t11.teamlort.Controller.QuestionController;
import com.github.cmput301f14t11.teamlort.Model.Answer;
import com.github.cmput301f14t11.teamlort.Model.AppCache;
import com.github.cmput301f14t11.teamlort.Model.GpsLocation;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.Question;
import com.github.cmput301f14t11.teamlort.Model.Reply;

public class TestCase5to8
extends ActivityInstrumentationTestCase2<AppBaseActivity>
{
	public TestCase5to8(Class<AppBaseActivity> activityClass)
	{
		super(activityClass);
		
	}

	public void testSanity()
	{
		Assert.assertEquals(0, 0);
	}
	
	public void testCaseFive()
	{
		Activity activity = getActivity();
		QuestionController qController = new QuestionController(activity.getApplicationContext());
		AnswerController   aController = new AnswerController();
		
		// The user creates a question. Here the views (EditTexts) should have
		// the strings, but since that's not done we'll just make them up.
		
		String author1 = "Dingo Prime";
		String author2 = "Bingo Prime";
		String author3 = "Ringo Prime";
		
		String title = "I have a question about things.";
		String detail = "My thing with the stuff isn't working properly. Help? (See pic).";
		
		GpsLocation loc = new GpsLocation(0, 0);
		
		Question q1 = ObjectFactory.initQuestion(title, detail, author1, loc);
		
		qController.addQuestion(q1);
		
		//*
		Assert.assertTrue(
			AppCache.getInstance()
				.getProfile()
				.getMyQuestionList()
				.contains(q1)
			);//*/
		
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
		int i = 0;
		
		ArrayList<Question> checkResult = AppCache.getInstance().getProfile().getMyQuestionList();
		Question checkQuestion;
		
		do
		{
			if (i == checkResult.size())
			{
				Assert.fail("The Profile did not contain the created question!");
				return;
			}
			checkQuestion = checkResult.get(i);
			i++;
		} while (checkQuestion.getID() != q1.getID());
		
		Assert.assertTrue(
			checkQuestion.getAnswerList().contains(a1)
			);
		Assert.assertTrue(
			checkQuestion.getAnswerList().contains(a2)
			);
	}
	
	public void testCaseSix()
	{
		Activity activity = getActivity();
		QuestionController qController = new QuestionController(activity.getApplicationContext());
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
		GpsLocation loc = new GpsLocation(0, 0);
		
		Question q1 = ObjectFactory.initQuestion(title, detail, author1, loc);
		qController.addQuestion(q1);
		Answer a1 = ObjectFactory.initAnswer(answer1, author2);
		Answer a2 = ObjectFactory.initAnswer(answer2, author3);
		aController.addAnswer(a1, q1.getID());
		aController.addAnswer(a2, q1.getID());
		
		// Now add a response to each.
		Reply r1 = ObjectFactory.initReply("Sorry. I meant where's the washroom in here?", author1);
		Reply r2 = ObjectFactory.initReply("This answer sucks. Doesn't answer the question.", author1);
		Reply r3 = ObjectFactory.initReply("lol", author2);
		
		//TODO Add this back after Elvis finishes with the qController
		//qController.addQuestionReply(r1, q1.getID());
		aController.addReply(r2, q1.getID(), a1.getID());
		aController.addReply(r3, q1.getID(), a2.getID());
		
		// Make sure they're there.
		int i = 0;
		
		ArrayList<Question> checkResult = AppCache.getInstance().getProfile().getMyQuestionList();
		Question checkQuestion;
		Answer checkAnswer1;
		Answer checkAnswer2;
		
		do
		{
			if (i == checkResult.size())
			{
				Assert.fail("The Profile did not contain the created question!");
				return;
			}
			checkQuestion = checkResult.get(i);
			i++;
		} while (checkQuestion.getID() != q1.getID());
		
		i = 0;
		do
		{
			if (i >= checkQuestion.getAnswerList().size())
			{
				Assert.fail("The Question did not contain a created answer!");
				return;
			}
			checkAnswer1 = checkQuestion.getAnswer(i);
			i++;
		} while (checkAnswer1.getID() != a1.getID());
		
		i = 0;
		do
		{
			if (i >= checkQuestion.getAnswerList().size())
			{
				Assert.fail("The Question did not contain a created answer!");
				return;
			}
			checkAnswer2 = checkQuestion.getAnswer(i);
			i++;
		} while (checkAnswer2.getID() != a2.getID());

		Assert.assertTrue(
			checkQuestion.getReplyList().contains(r1)
			);
		Assert.assertTrue(
			checkAnswer1.getReplyList().contains(r2)
			);
		Assert.assertTrue(
			checkAnswer2.getReplyList().contains(r3)
			);
	}
	
	public void testCaseSeven()
	{
		Activity activity = getActivity();
		QuestionController qController = new QuestionController(activity.getApplicationContext());
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
		Drawable img1 = null;
		GpsLocation loc = new GpsLocation(0, 0);
		
		Question q1 = ObjectFactory.initQuestion(title, detail, author1, loc, img1);
		qController.addQuestion(q1);
		Answer a1 = ObjectFactory.initAnswer(answer1, author2);
		Answer a2 = ObjectFactory.initAnswer(answer2, author3);
		aController.addAnswer(a1, q1.getID());
		aController.addAnswer(a2, q1.getID());
		
		// Make sure they're there.
		int i = 0;
		
		ArrayList<Question> checkResult = AppCache.getInstance().getProfile().getMyQuestionList();
		Question checkQuestion;
		
		do
		{
			if (i == checkResult.size())
			{
				Assert.fail("The Profile did not contain the created question!");
				return;
			}
			checkQuestion = checkResult.get(i);
			i++;
		} while (checkQuestion.getID() != q1.getID());
		
		Assert.assertNotNull(
			checkQuestion.getPicture()
			);
	}
	
	public void testCaseEight()
	{
		Activity activity = getActivity();
		QuestionController qController = new QuestionController(activity.getApplicationContext());
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
		Drawable img1 = null;
		
		GpsLocation loc = new GpsLocation(0, 0);
		
		Question q1 = ObjectFactory.initQuestion(title, detail, author1, loc, img1);
		qController.addQuestion(q1);
		Answer a1 = ObjectFactory.initAnswer(answer1, author2);
		Answer a2 = ObjectFactory.initAnswer(answer2, author3);
		aController.addAnswer(a1, q1.getID());
		aController.addAnswer(a2, q1.getID());
		
		// Make sure they're there.
		int i = 0;
		
		ArrayList<Question> checkResult = AppCache.getInstance().getProfile().getMyQuestionList();
		Question checkQuestion;
		
		do
		{
			if (i == checkResult.size())
			{
				Assert.fail("The Profile did not contain the created question!");
				return;
			}
			checkQuestion = checkResult.get(i);
			i++;
		} while (checkQuestion.getID() != q1.getID());
		
		// Convert the Drawable to a byte array.
		Drawable draw = checkQuestion.getPicture();
		Bitmap bmp = ((BitmapDrawable) draw).getBitmap();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] raw = baos.toByteArray();
		
		Assert.assertTrue(
				raw.length < 64000
			);
	}
}
