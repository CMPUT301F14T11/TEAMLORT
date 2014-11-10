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
		AppCache ac = new AppCache();

		Question q1 = new Question();
		Question q2 = new Question();
		Question q3 = new Question();

		ac.setProfile(profile);

		assertEquals("Profiles do not match", ac.getProfile(), profile);

		assertTrue("Should be an empty list", profile.getFavedQuestionList()
				.size() == 0);

		pc.addFavedQuestion(q1);
		pc.addFavedQuestion(q2);
		pc.addFavedQuestion(q3);

		assertEquals("Not saved to correct profile", pc.getProfile(), profile);
		assertTrue("Nothing was added to favoriteList", profile
				.getFavedQuestionList().size() != 0);
		assertTrue("Not all questions were added to favoriteList", profile
				.getFavedQuestionList().size() == 3);
		assertTrue("q1 was not added",
				profile.getFavedQuestionList().contains(q1));
		assertTrue("q2 was not added",
				profile.getFavedQuestionList().contains(q2));
		assertTrue("q3 was not added",
				profile.getFavedQuestionList().contains(q3));

	}

	public void testRemoveFavorite() {
		Profile profile = new Profile();
		ProfileController pc = new ProfileController();
		AppCache ac = new AppCache();

		Question q1 = new Question();
		Question q2 = new Question();
		Question q3 = new Question();

		ac.setProfile(profile);

		assertEquals("Profiles do not match", ac.getProfile(), profile);

		assertTrue("Should be an empty list", profile.getFavedQuestionList().size() == 0);

		pc.removeFavedQuestion(q1);
		pc.removeFavedQuestion(q2);
		pc.removeFavedQuestion(q3);

		assertTrue("Should contain 3 items", profile.getFavedQuestionList()
				.size() == 3);

		pc.removeFavedQuestion(q1);
		pc.removeFavedQuestion(q2);
		pc.removeFavedQuestion(q3);

		assertEquals("Not saved to correct profile", pc.getProfile(), profile);
		assertTrue("Something was not removed from favoriteList", profile
				.getFavedQuestionList().size() == 0);
		assertTrue("q1 was not removed", !profile.getFavedQuestionList()
				.contains(q1));
		assertTrue("q2 was not removed", !profile.getFavedQuestionList()
				.contains(q2));
		assertTrue("q3 was not removed", !profile.getFavedQuestionList()
				.contains(q3));

	}

}
