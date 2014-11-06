package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import android.widget.TextView;

import com.github.cmput301f14t11.teamlort.QuestionViewActivity;

import junit.framework.TestCase;

public class UseCase13Through16Test extends TestCase {
	
	// Use case 13: Display the Most Upvoted Questions and Answer
	public void testCase13(){
		
		//Create existed database
		PersistentDataManager dataManager = new PersistentDataManager();
		ObjectFactory inputManager = new ObjectFactory();
		ScoreController sc = new ScoreController();
		ArrayList<Question> highestScoreQuestionList = new ArrayList<Question>();
		ArrayList<Answer> highestScoreAnswerList = new ArrayList<Answer>();
		
		Question question1 = new Question("Q1");
		Question question2 = new Question("Q2");
		Question question3 = new Question("Q3");
		Answer answer1 = new Answer("A1");
		Answer answer2 = new Answer("A2");
		Answer answer3 = new Answer("A3");
		
		question1.addAnswer(answer1);
		question2.addAnswer(answer2);
		question3.addAnswer(answer3);
		inputManager.addQuestion(question1);
		inputManager.addQuestion(question2);
		inputManager.addQuestion(question3);
		
		// After user press upvoted button for question1 and answer2
		sc.increaseScore(question1);
		sc.increaseScore(answer2);
		
		// After users press "Sort By Score" button.
		highestScoreQuestionList = dataManager.sortQuestions(sortByScore());
		highestScoreAnswerList = dataManager.sortAnswers(sortByScore());
		
		// Test if the expected question and answer is on the list or not
		assertTrue("The most upvoted question: question1 is not on the list",
				highestScoreQuestionList.contains(question1));
		assertFalse("question2 and question3 is on the list",
				highestScoreQuestionList.contains(question2) ||
				highestScoreQuestionList.contains(question3));
		assertFalse("The most upvoted answer: answer2 is not on the list",
				highestScoreAnswerList.contains(answer2));
		assertFalse("answer1 and answer3 is on the list",
				highestScoreAnswerList.contains(answer1) ||
				highestScoreAnswerList.contains(answer3));
	}
	
	//Display the number of Answers for each question
	public void testCase14(){
		//Create the existed database
		PersistentDataManager dataManager = new PersistentDataManager();
		ObjectFactory inputManager = new ObjectFactory();
		
		QuestionViewActivity activity = (QuestionViewActivity) getAcetivity();
		TextView tv = (TextView)activity.findViewById(R.id.textView3);
		
		Question question1 = new Question("Q1");
		Question question2 = new Question("Q2");
		Question question3 = new Question("Q3");
		Answer answer1 = new Answer("A1Q1");
		Answer answer2 = new Answer("A1Q2");
		Answer answer3 = new Answer("A2Q1");
		Answer answer4 = new Answer("A3Q1");
		
		assertEquals(tv.getText().toString(), 
				String.valueOf(dataManager.countAnswer(question1)) );
		
		question1.addAnswer(answer1);
		question2.addAnswer(answer2);
		question1.addAnswer(answer3);
		question1.addAnswer(answer4);
		inputManager.addQuestion(question1);
		inputManager.addQuestion(question2);
		inputManager.addQuestion(question3);
		// And now users should see the nsumber of answers for each question in
		// the question detail screen.
		assertFalse("wrong number of answers for question1",
				dataManager.countAnswer(question1) != 3);
		assertEquals(tv.getText().toString(), 
				String.valueOf(dataManager.countAnswer(question1)) );
		
		assertFalse("wrong number of answers for question2",
				dataManager.countAnswer(question2) != 1);
		assertEquals(tv.getText().toString(), 
				String.valueOf(dataManager.countAnswer(question1)) );
		
		assertFalse("wrong number of answers for question3",
				dataManager.countAnswer(question3) != 0);
		assertEquals(tv.getText().toString(), 
				String.valueOf(dataManager.countAnswer(question1)) );
		
	
	}
	
	//Search Answers or Questions
	public void testCase15(){
		//Create existed database
		PersistentDataManager dataManager = new PersistentDataManager();
		ObjectFactory inputManager = new ObjectFactory();
		ArrayList<Question> result = new ArrayList<Question>();

		Question question1 = new Question("Cat");
		Question question2 = new Question("Dog");
		Question question3 = new Question("Horse");
		Answer answer1 = new Answer("mew");
		Answer answer2 = new Answer("bark");
		Answer answer3 = new Answer("neigh");
		Answer answer4 = new Answer("bbar");
		
		question1.addAnswer(answer1);
		question2.addAnswer(answer2);
		question3.addAnswer(answer3);
		question2.addAnswer(answer4);
		inputManager.addQuestion(question1);
		inputManager.addQuestion(question2);
		inputManager.addQuestion(question3);
		
		//Search "cat" and question1 should append to the result list
		result = dataManager.searchQuestion("cat");
		assertTrue("cat search is not expected", result.size() == 1 &&
				result.get(0) == question1);
		//When user search "bar", it should return the answer bark and bbar
		result = dataManager.searchAnswer("bar");
		assertTrue("bark search is not expected", result.size() == 2 &&
				result.contains(answer2) && result.contains(answer4));
	
	}
	//Recording Authors’ Questions
	public void testCase16(){
		PersistentDataManager dataManager = new PersistentDataManager();
		ObjectFactory inputManager = new ObjectFactory();
		UserController uc = new UserController(dataManager);
		ArrayList<Question> authorQuestionList = new ArrayList<Question>();
		
		uc.login("author");
		authorQuestionList = uc.loadCacheData();
		// author has not started a question yet. authorQuestionList should be empty
		assertTure("author list is not empty", authorQuestionList.size() == 0);
		
		Question question1 = new Question("Q1");
		Answer answer1 = new Answer("A1");
		question1.addAnswer(answer1);
		inputManager.addQuestion(question1);
		uc.saveCacheData();
		//when user exit the program or logout and login later, the question should
		//still traceable.
		uc.logout();
		Question question2 = new Question("Q1");
		inputManager.addQuestion(question2);
		uc.login("author");
		authorQuestionList = uc.loadCacheData();
		
		assertTrue("Only one question should stored in the user cache list",
				authorQuestionList.size() == 1);
		assertTure("Only question1 should in the cache list",
				authorQuestionList.contains(question1));
		
		
	}

}
