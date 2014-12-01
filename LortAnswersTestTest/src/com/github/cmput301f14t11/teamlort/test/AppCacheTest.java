package com.github.cmput301f14t11.teamlort.test;

import com.github.cmput301f14t11.teamlort.QuestionViewActivity;
import com.github.cmput301f14t11.teamlort.Model.AppCache;
import com.github.cmput301f14t11.teamlort.Model.Profile;
import com.github.cmput301f14t11.teamlort.Model.Question;

import android.test.ActivityInstrumentationTestCase2;

public class AppCacheTest extends ActivityInstrumentationTestCase2<QuestionViewActivity> {

	public AppCacheTest() {
		super(QuestionViewActivity.class);
	}
	
	public void testGetInstance(){
		AppCache appCache = AppCache.getInstance();
		assertTrue("AppCache is null", appCache != null);
	}
	
	public void testSetQuestion(){
		Question question = new Question();
		question.setTitle("test title");
		Question question2 = new Question();
		question.setTitle("test title 2");
		AppCache appCache = AppCache.getInstance();
		appCache.setQuestion(question);
		assertTrue("appCache did not set question properly", appCache.getQuestion() == question);
		appCache.setQuestion(question2);
		assertTrue("appCache did not set question2 properly", appCache.getQuestion() == question2);
		
		AppCache appCache2 = AppCache.getInstance();
		assertTrue("appCache is not a singleton", appCache2.getQuestion() == question2);
		
	}
	
	public void testGetQuestion(){
		Question question = new Question();
		question.setTitle("test title");
		AppCache appCache = AppCache.getInstance();
		assertTrue("appCache's question not null when it should be", appCache.getQuestion() == null);
		appCache.setQuestion(question);
		assertTrue("appCache did not get question properly", appCache.getQuestion() == question);
	}
	
	public void testSetProfile(){
		Profile profile = new Profile();
		profile.setUsername("testUser");
		Profile profile2 = new Profile();
		profile.setUsername("testUser2");
		AppCache appCache = AppCache.getInstance();
		appCache.setProfile(profile);
		assertTrue("appCache did not set profile properly", appCache.getProfile() == profile);
		appCache.setProfile(profile2);
		assertTrue("appCache did not set profile2 properly", appCache.getProfile() == profile2);
		
		AppCache appCache2 = AppCache.getInstance();
		assertTrue("appCache did not set profile2 properly", appCache2.getProfile() == profile2);
		
	}
	
	public void testGetProfile(){
		Profile profile = new Profile();
		profile.setUsername("testUser");
		AppCache appCache = AppCache.getInstance();
		assertTrue("appCache's profile not null when it should be", appCache.getProfile() == null);
		appCache.setProfile(profile);
		assertTrue("appCache did not get profile properly", appCache.getProfile() == profile);
	}

}
