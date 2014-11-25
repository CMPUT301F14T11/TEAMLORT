package com.github.cmput301f14t11.teamlort.test;

import junit.framework.Assert;
import android.test.ActivityInstrumentationTestCase2;

import com.github.cmput301f14t11.teamlort.AppBaseActivity;
import com.github.cmput301f14t11.teamlort.Model.LocalManager;
import com.github.cmput301f14t11.teamlort.Model.Profile;
import com.github.cmput301f14t11.teamlort.Model.Question;

public class LocalManagerTester
extends ActivityInstrumentationTestCase2<AppBaseActivity>
{
	public LocalManagerTester(Class<AppBaseActivity> activityClass) 
	{
		super(activityClass);
	}
	
	public void testLMSaveQuestions()
	{
	}
	
	public void testLMSaveProfile()
	{
		LocalManager localManager = new LocalManager();
		Profile p = new Profile();
		String usr = "Yuey";
		
		p.setUsername(usr);
		p.getFavedQuestionList().add(new Question());
		
		localManager.saveProfile(p);
		
		Profile checkp = localManager.loadProfile(usr);
		Assert.assertEquals(usr, checkp.getUsername());
		Assert.assertEquals(1, p.getFavedQuestionList().size());
	}
}