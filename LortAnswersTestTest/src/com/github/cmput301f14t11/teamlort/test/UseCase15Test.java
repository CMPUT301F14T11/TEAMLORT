package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;

import com.github.cmput301f14t11.teamlort.HomeActivity;
import com.github.cmput301f14t11.teamlort.Controller.Qlistcontroller;
import com.github.cmput301f14t11.teamlort.Model.ElasticManager;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.Question;

public class UseCase15Test extends ActivityInstrumentationTestCase2<HomeActivity>{
	
		public UseCase15Test(){
			super(HomeActivity.class);
			
		}
		public void testCase15(){
			new ArrayList<Question>();
			ArrayList<Question> result = new ArrayList<Question>();
			new ObjectFactory();
			Qlistcontroller ql = new Qlistcontroller();
			ElasticManager em = ElasticManager.getInstance();

			Question question1 = ObjectFactory.initQuestion("Cat", "B1", "A1");
			Question question2 = ObjectFactory.initQuestion("Dog", "B2", "A2");
			Question question3 = ObjectFactory.initQuestion("Horse", "B3", "A3");

			ql.add(question1);
			ql.add(question2);
			ql.add(question3);

			em.addItem(question1);
			em.addItem(question2);
			em.addItem(question3);
			
			//Search "cat" and question1 should append to the result list
			result = em.search("cat", null, 5);
			assertTrue("cat search is not expected", result.size() == 1 &&
					result.get(0) == question1);
		}

}
