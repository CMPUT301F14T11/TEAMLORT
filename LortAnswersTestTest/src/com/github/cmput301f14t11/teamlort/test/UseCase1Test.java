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
		ElasticManager elasticManager = ElasticManager.getInstance();
		Qlistcontroller qListController = new Qlistcontroller();
		//App can retrieve a list of questions from ElasticManager
		QuestionList questionList = qListController.getQuestionlist();
		ArrayList<Question> modelList = questionList.getModellist();
		assertTrue("Data manager returned questions when there should be none", modelList.size() > 1);

	}

}
