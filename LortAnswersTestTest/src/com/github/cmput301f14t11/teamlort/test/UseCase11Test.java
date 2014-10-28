package com.github.cmput301f14t11.teamlort.test;

import android.test.ActivityInstrumentationTestCase2;

import com.github.cmput301f14t11.teamlort.HomeActivity;
import com.github.cmput301f14t11.teamlort.Question;
import com.github.cmput301f14t11.teamlort.ScoreController;
import com.github.cmput301f14t11.teamlort.Model.PersistentDataManager;

import junit.framework.TestCase;


public class UseCase11Test extends ActivityInstrumentationTestCase2<HomeActivity>  
{  
		public UseCase11Test(Class<HomeActivity> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}

		//yo elvis thanks buddy!
		//Oct.18 - added controller to tests
		//Oct.28 - fixing controller errors, adding methods overlooked by teammates
		public void testUpvote()
		{
			Question question = new Question();
			ScoreController sc = new ScoreController();
			assertTrue("question initialized with more than 0 upvotes", sc.getScore(question) >= 0);
			sc.increaseScore(question);
			assertFalse("Upvote did not change score", sc.getScore(question) == 0);
			assertTrue("Same user managed to upvote same question twice", sc.checkIllegal(question) == true);
		}
}
