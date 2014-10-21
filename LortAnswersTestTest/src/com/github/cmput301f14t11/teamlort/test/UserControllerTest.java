package com.github.cmput301f14t11.teamlort.test;

import com.github.cmput301f14t11.teamlort.UserController;

import junit.framework.TestCase;

public class UserControllerTest extends TestCase {
	
	public void testCreate(){
		UserController uc = new UserController();
		assertTrue("uc should not be null",uc != null);
	}

}
