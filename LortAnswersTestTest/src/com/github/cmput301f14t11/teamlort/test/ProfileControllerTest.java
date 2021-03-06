package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import com.github.cmput301f14t11.teamlort.Controller.ProfileController;
import com.github.cmput301f14t11.teamlort.Model.AppCache;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.Profile;
import com.github.cmput301f14t11.teamlort.Model.Question;

import junit.framework.TestCase;

public class ProfileControllerTest extends TestCase {
		Profile p = null;
		ProfileController pc = null;
	
	public void testCreate(){
		pc = new ProfileController();
		p = new Profile();
		assertTrue("uc should not be null",pc != null);
	}
	

	public void testFavedQuestion() {
		assertTrue("nothing should be in the list now", p.getFavedQuestionList()== new ArrayList<Question>());
		Question q1 = ObjectFactory.initQuestion("A", "B", "C");
		Question q2 = ObjectFactory.initQuestion("D", "E", "C");
		pc.addFavedQuestion(q1);
		assertTrue("q1 in the list now", p.getFavedQuestionList().contains(q1));
		pc.addFavedQuestion(q2);
		assertTrue("q2 in the list now", p.getFavedQuestionList().contains(q2));
		assertTrue("favedList should contain 2 question", p.getFavedQuestionList().size() == 2);
		
	}
	
	public void testRemoveFavedQuestion(){
		assertTrue("nothing should be in the list now", p.getFavedQuestionList()== new ArrayList<Question>());
		Question q1 = ObjectFactory.initQuestion("A", "B", "C");
		Question q2 = ObjectFactory.initQuestion("D", "E", "C");
		pc.addFavedQuestion(q1);
		pc.addFavedQuestion(q2);
		pc.removeCreatedQuestion(q1);
		pc.removeCreatedQuestion(q2);
		assertTrue("nothing should be in the list now", p.getFavedQuestionList().size() == 0);
	}
	
	public void testSavedQuestion() {
		assertTrue("nothing should be in the list now", p.getSavedQuestionList()== new ArrayList<Question>());
		Question q1 = ObjectFactory.initQuestion("A", "B", "C");
		Question q2 = ObjectFactory.initQuestion("D", "E", "C");
		pc.addSavedQuestion(q1);
		assertTrue("q1 in the list now", p.getSavedQuestionList().contains(q1));
		pc.addSavedQuestion(q2);
		assertTrue("q2 in the list now", p.getSavedQuestionList().contains(q2));
		assertTrue("savedlist should contain 2 question", p.getSavedQuestionList().size() == 2);
		
	}
	
	public void testRemoveSavedQuestion(){
		assertTrue("nothing should be in the list now", p.getSavedQuestionList()== new ArrayList<Question>());
		Question q1 = ObjectFactory.initQuestion("A", "B", "C");
		Question q2 = ObjectFactory.initQuestion("D", "E", "C");
		pc.addSavedQuestion(q1);
		pc.addSavedQuestion(q2);
		pc.removeSavedQuestion(q1);
		pc.removeSavedQuestion(q2);
		assertTrue("nothing should be in the list now", p.getSavedQuestionList().size() == 0);
		pc.removeSavedQuestion(q1);
	}
	
	public void testCreatedQuestion() {
		Profile p = new Profile();
		p.setUsername("C");
;		AppCache.getInstance().setProfile(p);
		assertTrue("nothing should be in the list now", p.getMyQuestionList()==new ArrayList<Question>());
		Question q1 = ObjectFactory.initQuestion("A", "B", "C");
		Question q2 = ObjectFactory.initQuestion("D", "E", "C");
		pc.addCreatedQuestion(q1);
		assertTrue("q1 in the list now", p.getMyQuestionList().contains(q1));
		pc.addCreatedQuestion(q2);
		assertTrue("q2 in the list now", p.getMyQuestionList().contains(q2));
		assertTrue("savedlist should contain 2 question", p.getMyQuestionList().size() == 2);
		
	}
	
	public void testRemoveCreatedQuestion(){
		assertTrue("nothing should be in the list now", p.getMyQuestionList()== new ArrayList<Question>());
		Question q1 = ObjectFactory.initQuestion("A", "B", "C");
		Question q2 = ObjectFactory.initQuestion("D", "E", "C");
		pc.addSavedQuestion(q1);
		pc.addSavedQuestion(q2);
		pc.removeCreatedQuestion(q1);
		pc.removeCreatedQuestion(q2);
		assertTrue("nothing should be in the list now", p.getMyQuestionList().size() == 0);
	}

}
