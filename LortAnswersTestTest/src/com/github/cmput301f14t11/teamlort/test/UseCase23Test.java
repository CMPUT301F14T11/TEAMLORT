package com.github.cmput301f14t11.teamlort.test;

import junit.framework.TestCase;

public class UseCase23Test extends TestCase {
	public void testSetUsername() {
		String username = "Test Username";
		PersistentDataManager pdm = PersistentDataManager.getInstance();
		UserNameController unc = new userController();
		unc.getPersistentDataManager();
		unc.setUsername(username);
		assertEquals(username, pdm.getUsername());
	}
}
