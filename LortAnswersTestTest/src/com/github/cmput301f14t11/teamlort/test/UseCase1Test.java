package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import com.github.cmput301f14t11.teamlort.DataController;
import com.github.cmput301f14t11.teamlort.HomeActivity;
import com.github.cmput301f14t11.teamlort.Question;
import com.github.cmput301f14t11.teamlort.Model.PersistentDataManager;

import android.test.ActivityInstrumentationTestCase2;

public class UseCase1Test extends ActivityInstrumentationTestCase2<HomeActivity> {

	public UseCase1Test() {
		super(HomeActivity.class);
	}
	
	public void testCaseOne(){
		//Test case for Use Case #1: User browses questions
		PersistentDataManager dataManager = PersistentDataManager.getInstance();
		DataController dataController = new DataController();
		//If there are no questions, return no questions.
		ArrayList<Question> emptyList = dataManager.getAllQuestions();
		assertTrue("Data manager returned questions when there should be none", emptyList.size() == 0);
		
		Question question = new Question();
		question.setTitle("Why are there two amulets?");
		Question question2 = new Question();
		question2.setTitle("Didn't Archer already return amulet?");
		Question question3 = new Question();
		question3.setTitle("How does Shirou have amulet?");
		Question question4 = new Question();
		question4.setTitle("Are these spoilers for F/SN?");
		Question question5 = new Question();
		question5.setTitle("Which route is this?");
		dataController.addQuestion(question);
		dataController.addQuestion(question2);
		dataController.addQuestion(question3);
		dataController.addQuestion(question4);
		dataController.addQuestion(question5);
		//App can retrieve a list of questions from PersistentDataManager
		ArrayList<Question> partialList = dataManager.getQuestions(0, 2);
		assertTrue("Partial list does not contain question 1 where it should be", partialList.get(0) == question);
		assertTrue("Partial list does not contain question 2 where it should be", partialList.get(1) == question2);
		assertTrue("Partial list does not contain question 3 where it should be", partialList.get(2) == question3);
		//App can add additional questions to current question list.
		partialList.append(dataManager.getQuestions(2, 4));
		assertTrue("Partial list does not contain question 4 where it should be", partialList.get(3) == question2);
		assertTrue("Partial list does not contain question 5 where it should be", partialList.get(4) == question3);
	}

}
