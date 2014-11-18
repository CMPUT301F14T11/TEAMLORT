package com.github.cmput301f14t11.teamlort.test;

import com.github.cmput301f14t11.teamlort.Controller.Qlistcontroller;
import com.github.cmput301f14t11.teamlort.Model.Question;

import junit.framework.TestCase;

public class Qlistcontrollertest extends TestCase{
	
	public void testCreate(){
		Qlistcontroller qt = new Qlistcontroller();
		assertTrue("questioncontroller should not be null",qt != null);
	}
	public void testGetMore()
	{
		
	}
	public void testAdd()
	{
		Qlistcontroller qt = new Qlistcontroller();
		qt.add(new Question());
		assertTrue("no question in questionlist",qt.returnsize() > 0);
	}
	public void testdelete()
	{
		
	}

}
