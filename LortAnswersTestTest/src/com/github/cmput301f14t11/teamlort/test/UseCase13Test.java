package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import com.github.cmput301f14t11.teamlort.Answer;
import com.github.cmput301f14t11.teamlort.ElasticManager;
import com.github.cmput301f14t11.teamlort.HomeActivity;
import com.github.cmput301f14t11.teamlort.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Qlistcontroller;
import com.github.cmput301f14t11.teamlort.Question;

public class UseCase13Test extends  ActivityInstrumentationTestCase2<HomeActivity> {
	
	public UseCase13Test() {
		super(HomeActivity.class);
	}

	// Use case 13: Display the Most Upvoted Questions and Answer
	public void testCase13(){
		
		ArrayList<Question> highestScoreQuestionList = new ArrayList<Question>();
		ArrayList<Answer> highestScoreAnswerList = new ArrayList<Answer>();
		Qlistcontroller ql = new Qlistcontroller();
		ElasticManager.getInstance();
		
		Question question1 = ObjectFactory.initQuestion("T1", "B1", "A1");
		Question question2 = ObjectFactory.initQuestion("T2", "B2", "A2");
		Question question3 = ObjectFactory.initQuestion("T3", "B3", "A3");
		Answer answer1 =  ObjectFactory.initAnswer("B1", "A1");
		Answer answer2 =  ObjectFactory.initAnswer("B1", "A1");
		Answer answer3 =  ObjectFactory.initAnswer("B1", "A1");
		
		question1.addAnswer(answer1);
		question2.addAnswer(answer2);
		question3.addAnswer(answer3);
		ql.add(question1);
		ql.add(question2);
		ql.add(question3);
		
		// After user press upvoted button for question1 and answer2
		question1.upVote("A1");
		answer2.upVote("A2");
		
		// After users press "Sort By Score" button.
		ql.sortQuestions("upVote");
		for (int i = 0; i < ql.returnsize(); i++)
		{
			ql.sortAnswers(ql.returnquestion(i).getAnswerList(),"upVote");
		}
		
		
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

}
