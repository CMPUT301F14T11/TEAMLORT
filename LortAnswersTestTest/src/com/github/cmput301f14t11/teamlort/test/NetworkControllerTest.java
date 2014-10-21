package com.github.cmput301f14t11.teamlort.test;

import com.github.cmput301f14t11.teamlort.NetworkController;

import junit.framework.TestCase;

public class NetworkControllerTest extends TestCase {
	
	public void testCreate(){
		NetworkController nc = new NetworkController();
		assertTrue("nc should not be null",nc != null);
	}

}
