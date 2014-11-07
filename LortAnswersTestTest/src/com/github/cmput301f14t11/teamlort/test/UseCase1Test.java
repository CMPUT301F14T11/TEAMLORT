package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import com.github.cmput301f14t11.teamlort.Elasticmanager;
import com.github.cmput301f14t11.teamlort.ObjectFactory;
import com.github.cmput301f14t11.teamlort.HomeActivity;
import com.github.cmput301f14t11.teamlort.Qlistcontroller;
import com.github.cmput301f14t11.teamlort.Question;
import com.github.cmput301f14t11.teamlort.elasticmanager;
import com.github.cmput301f14t11.teamlort.Model.PersistentDataManager;

import android.test.ActivityInstrumentationTestCase2;

public class UseCase1Test extends ActivityInstrumentationTestCase2<HomeActivity> {

	public UseCase1Test() {
		super(HomeActivity.class);
	}
	
	public void testCaseOne(){
		//Test case for Use Case #1: User browses questions
		Elasticmanager elasticManager = Elasticmanager.getInstance();
		Qlistcontroller qListController = new Qlistcontroller();
		//If there are no questions, return no questions.
		ArrayList<Question> emptyList = elasticManager.get(0, 10);
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
		qListController.add(question);
		qListController.add(question2);
		qListController.add(question3);
		qListController.add(question4);
		qListController.add(question5);
		//App can retrieve a list of questions from ElasticManager
		ArrayList<Question> partialList = elasticManager.get(0, 10);
		assertTrue("Partial list does not contain question 1 where it should be", partialList.get(0) == question);
		assertTrue("Partial list does not contain question 2 where it should be", partialList.get(1) == question2);
		assertTrue("Partial list does not contain question 3 where it should be", partialList.get(2) == question3);
		assertTrue("Partial list does not contain question 4 where it should be", partialList.get(3) == question2);
		assertTrue("Partial list does not contain question 5 where it should be", partialList.get(4) == question3);
	}

}
