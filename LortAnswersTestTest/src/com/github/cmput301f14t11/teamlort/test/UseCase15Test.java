package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;

import com.github.cmput301f14t11.teamlort.Answer;
import com.github.cmput301f14t11.teamlort.ElasticManager;
import com.github.cmput301f14t11.teamlort.HomeActivity;
import com.github.cmput301f14t11.teamlort.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Qlistcontroller;
import com.github.cmput301f14t11.teamlort.Question;

public class UseCase15Test extends ActivityInstrumentationTestCase2<HomeActivity>{
	
		public UseCase15Test(){
			super(HomeActivity.class);
			
		}
		public void testCase15(){
			//Create existed database
			ArrayList<Question> temp = new ArrayList<Question>();
			ArrayList<Question> result = new ArrayList<Question>();
			ObjectFactory obf = new ObjectFactory();
			Qlistcontroller ql = new Qlistcontroller();
			ElasticManager em = ElasticManager.getInstance();

			Question question1 = obf.initQuestion("Cat", "B1", "A1");
			Question question2 = obf.initQuestion("Dog", "B2", "A2");
			Question question3 = obf.initQuestion("Horse", "B3", "A3");
			Answer answer1 =  obf.initAnswer("mew", "A1");
			Answer answer2 =  obf.initAnswer("bark", "A1");
			Answer answer3 =  obf.initAnswer("neigh", "A1");
			Answer answer4 =  obf.initAnswer("bbar", "A1");

			question1.addAnswer(answer1);
			question2.addAnswer(answer2);
			question3.addAnswer(answer3);
			ql.add(question1,temp);
			ql.add(question2,temp);
			ql.add(question3,temp);
			
			
			//Search "cat" and question1 should append to the result list
			result = em.search("cat");
			assertTrue("cat search is not expected", result.size() == 1 &&
					result.get(0) == question1);
			//When user search "bar", it should return the answer bark and bbar
			result = em.search("bar");
			assertTrue("bark search is not expected", result.size() == 2 &&
					result.contains(answer2) && result.contains(answer4));
		
		}

}
