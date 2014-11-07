package com.github.cmput301f14t11.teamlort.test;

import junit.framework.TestCase;

import com.github.cmput301f14t11.teamlort.AppCache;
import com.github.cmput301f14t11.teamlort.Profile;
import com.github.cmput301f14t11.teamlort.ProfileController;
import com.github.cmput301f14t11.teamlort.Question;
/**
 * Tests for UseCase 18
 * 
 * @author morganpatzelt
 *
 */
public class FavoriteTest extends TestCase {

	public void testAddFavorite() {
		Profile profile = new Profile();
		ProfileController pc = new ProfileController();
		
		Question q1 = new Question();
		Question q2 = new Question();
		Question q3 = new Question();
		
		pc.setProfile(profile);
		
		assertEquals("Profile not set", pc.getProfile(), profile);
		
		assertTrue("Should be an empty list", profile.getFavedQuestionList().size() == 0);
		
		pc.addFavorite(q1);
		pc.addFavorite(q2);
		pc.addFavorite(q3);
		
		assertTrue("Nothing was added to favoriteList", profile.getFavedQuestionList().size() != 0);
		assertTrue("Not all questions were added to favoriteList", profile.getFavedQuestionList().size() == 3);
		assertTrue("q1 was not added", profile.getFavedQuestionList().contains(q1));
		assertTrue("q2 was not added", profile.getFavedQuestionList().contains(q2));
		assertTrue("q3 was not added", profile.getFavedQuestionList().contains(q3));
		
	}
	
	public void testRemoveFavorite() {
		Profile profile = new Profile();
		ProfileController pc = new ProfileController();
		
		Question q1 = new Question();
		Question q2 = new Question();
		Question q3 = new Question();
		
		pc.setProfile(profile);
		
		assertEquals("Profile not set", pc.getProfile(), profile);
		
		assertTrue("Should be an empty list", profile.getFavedQuestionList().size() == 0);
	
		pc.addFavorite(q1);
		pc.addFavorite(q2);
		pc.addFavorite(q3);
		
		assertTrue("Should contain 3 items", profile.getFavedQuestionList().size() == 3);
		
		pc.removeFavorite(q1);
		pc.removeFavorite(q2);
		pc.removeFavorite(q3);
		
		assertTrue("Something was not removed from favoriteList", profile.getFavedQuestionList().size() == 0);
		assertTrue("q1 was not removed", !profile.getFavedQuestionList().contains(q1));
		assertTrue("q2 was not removed", !profile.getFavedQuestionList().contains(q2));
		assertTrue("q3 was not removed", !profile.getFavedQuestionList().contains(q3));
		
	}

}
