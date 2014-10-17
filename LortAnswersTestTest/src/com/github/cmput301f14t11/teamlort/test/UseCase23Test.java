package com.github.cmput301f14t11.teamlort.test;

import junit.framework.TestCase;

public class UseCase23Test extends TestCase {
	public void testSetUsername() {
		String username = "Test Username";
		PersistentDataManager pdm = new PersistentDataManager();
		pdm.setUsername(username);
		assertEquals(username, pdm.getUsername());
	}
}
