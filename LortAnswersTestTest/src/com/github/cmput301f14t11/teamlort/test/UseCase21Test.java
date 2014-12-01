package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;

import com.github.cmput301f14t11.teamlort.HomeActivity;
import com.github.cmput301f14t11.teamlort.Model.AppCache;
import com.github.cmput301f14t11.teamlort.Model.ElasticManager;
import com.github.cmput301f14t11.teamlort.Model.LocalManager;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.Profile;
import com.github.cmput301f14t11.teamlort.Model.PushQueue;
import com.github.cmput301f14t11.teamlort.Model.Question;

import junit.framework.TestCase;

public class UseCase21Test extends  ActivityInstrumentationTestCase2<HomeActivity> {
	
	public UseCase21Test(){
		super(HomeActivity.class);
	}
	
	public void TestSaveQuestion() {
		Question q = ObjectFactory.initQuestion("A1", "B1", "User1");
		LocalManager.getManager().saveQuestion(q);
		assertEquals(q, LocalManager.getManager().loadQuestions().get(0));
	}
	public void TestPushSavedQuestion() {
		Question question = ObjectFactory.initQuestion("A1", "B1", "User1");
		PushQueue.getInstance().pushQuestion(question, getActivity().getApplicationContext());
		ArrayList<Question> result = ElasticManager.getInstance().search("A1", "Title", 1);
		assertEquals(question, result.get(0));
		
	}
	
	public void TestUseCase() {
		Question question = ObjectFactory.initQuestion("A1", "B1", "User1");
		LocalManager.getManager().saveQuestion(question);
		LocalManager.getManager().loadQuestions().get(0);
		PushQueue.getInstance().pushQuestion(question, getActivity().getApplicationContext());
		ArrayList<Question> result = ElasticManager.getInstance().search("A1", "Title", 1);
		assertEquals(question, result.get(0));
	}
}
