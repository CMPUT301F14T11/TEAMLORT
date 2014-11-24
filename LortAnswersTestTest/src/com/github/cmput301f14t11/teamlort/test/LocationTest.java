package com.github.cmput301f14t11.teamlort.test;

import com.github.cmput301f14t11.teamlort.Model.Question;

import junit.framework.TestCase;

public class LocationTest extends TestCase {
	
	public void testQuestionSetLocation(){
		
		Question question = new Question();
		assertTrue("Desc not empty by default", question.getBody() == "");
		question.setBody(desc);
		assertTrue("Description doesn't match desc", question.getBody() == desc);
	}

}
