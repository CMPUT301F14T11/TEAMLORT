package com.github.cmput301f14t11.teamlort.test;

import android.view.View;
import android.widget.TextView;
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
	public void testSetOnscreen() {
		String username = "Test Username";
		PersistentDataManager pdm = PersistentDataManager.getInstance();
		UserNameController unc = new userController();
		unc.getPersistentDataManager();
		unc.setUsername(username);
		assertEquals(username, pdm.getUsername());
		ProfileActivity activity = (ProfileActivity) getActivity();
		TextView UserNameView = (TextView) findViewById(R.id.usernametext);
		assertEquals(username, UserNameView.getText());
	}
}
