package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import com.github.cmput301f14t11.teamlort.Model.LocalManager;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.Profile;
import com.github.cmput301f14t11.teamlort.Model.Question;

import junit.framework.TestCase;

public class ProfileTest extends TestCase {
	
	Profile p = null;
	
	public void testCreate(){
		p = new Profile();
		assertTrue("profile is not created", p!=null);
	}
	
	public void testSetUsername(){
		p.setUsername("A");
		assertTrue("username is not properly set", p.getUsername()=="A");
	}
	
	public void testIslogin(){
		p.setUsername("");
		assertTrue("username should be an empty string", !p.isLogin());
		p.setUsername("A");
		assertTrue("username should be A", p.isLogin());
	}
	
	public void testGetSaveQuestionList(){
		assertTrue("nothing should be in the list now", p.getSavedQuestionList()== new ArrayList<Question>());
		Question q = ObjectFactory.initQuestion("A", "B", "C");
		p.setUsername("C");
		LocalManager.getManager().saveQuestion(q);
		assertTrue("one question should be in the list", p.getSavedQuestionList().size() == 1);
		assertTrue("should be equal to q", p.getSavedQuestionList().contains(q));	
	}
	
	public void testGetMyQuestionList(){
		assertTrue("nothing should be in the list now", p.getMyQuestionList()== new ArrayList<Question>());
		Question q1 = ObjectFactory.initQuestion("A", "B", "C");
		Question q2 = ObjectFactory.initQuestion("D", "E", "C");
		Question q3 = ObjectFactory.initQuestion("F", "G", "Z");
		LocalManager.getManager().saveQuestion(q1);
		LocalManager.getManager().saveQuestion(q2);
		LocalManager.getManager().saveQuestion(q3);
		p.setUsername("C");
		assertTrue("should have two question", p.getMyQuestionList().size() == 2);
		assertTrue("question should be q1 and q2", p.getMyQuestionList().contains(q1) && 
				p.getMyQuestionList().contains(q2));
		p.setUsername("Z");
		assertTrue("should have one question", p.getMyQuestionList().size() == 1);
		assertTrue("question should be 3", p.getMyQuestionList().contains(q3));
		p.setUsername("Q");
		assertTrue("should not have question", p.getMyQuestionList().size() == 0);
		
	}
	

	

}
