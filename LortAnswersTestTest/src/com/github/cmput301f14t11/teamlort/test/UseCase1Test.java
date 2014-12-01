package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;

import com.github.cmput301f14t11.teamlort.HomeActivity;
import com.github.cmput301f14t11.teamlort.Controller.Qlistcontroller;
import com.github.cmput301f14t11.teamlort.Model.ElasticManager;
import com.github.cmput301f14t11.teamlort.Model.Question;
import com.github.cmput301f14t11.teamlort.Model.QuestionList;

public class UseCase1Test extends ActivityInstrumentationTestCase2<HomeActivity> {

	public UseCase1Test() {
		super(HomeActivity.class);
	}
	
	public void testCaseOne(){
		//Test case for Use Case #1: User browses questions
		Qlistcontroller qListController = new Qlistcontroller();
		//If there are no questions, return no questions.
		QuestionList questionList = qListController.getQuestionlist();
		ArrayList<Question> modelList = questionList.getModellist();
		assertTrue("qListController returned questions when there should be none", modelList.size() == 0);
		
		//App can retrieve a list of questions
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
		//App can retrieve a list of questions from qListController
		QuestionList questionList2 = qListController.getQuestionlist();
		ArrayList<Question> partialList = questionList2.getModellist();
		assertTrue("Partial list does not contain question 1 where it should be", partialList.get(0) == question);
		assertTrue("Partial list does not contain question 2 where it should be", partialList.get(1) == question2);
		assertTrue("Partial list does not contain question 3 where it should be", partialList.get(2) == question3);
		assertTrue("Partial list does not contain question 4 where it should be", partialList.get(3) == question4);
		assertTrue("Partial list does not contain question 5 where it should be", partialList.get(4) == question5);

	}

}
