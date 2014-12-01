package com.github.cmput301f14t11.teamlort.test;

import com.github.cmput301f14t11.teamlort.Controller.ProfileController;
import com.github.cmput301f14t11.teamlort.Model.AppCache;
import com.github.cmput301f14t11.teamlort.Model.Profile;

import android.view.View;
import android.widget.TextView;
import junit.framework.TestCase;

public class UseCase23Test extends TestCase {
	public void testSetUsername() {
		String username = "Test Username";
		
		Profile profile = AppCache.getInstance().getProfile();
		ProfileController.setP(profile);
		assertEquals(username, AppCache.getInstance().getProfile().getUsername());
	}

}
