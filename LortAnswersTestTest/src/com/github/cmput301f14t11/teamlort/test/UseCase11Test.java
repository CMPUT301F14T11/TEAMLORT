package com.github.cmput301f14t11.teamlort.test;

import java.util.Collections;

import android.test.ActivityInstrumentationTestCase2;

import com.github.cmput301f14t11.teamlort.HomeActivity;
import com.github.cmput301f14t11.teamlort.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Qlistcontroller;
import com.github.cmput301f14t11.teamlort.Question;
import com.github.cmput301f14t11.teamlort.QuestionController;
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
		//nov.10 - changed test to accept controller changes
		public void testUpvote()
		{
			ObjectFactory obj = new ObjectFactory();
			QuestionController qsc = new QuestionController();
			
			Question question = obj.initQuestion("test title", "body", "author");
			assertTrue("question initialized with more than 0 upvotes", question.getScore() >= 0);
			question.upVote("testing");
			assertFalse("Upvote did not change score", question.getScore() == 0);
			assertTrue("Same user managed to upvote same question twice", Collections.frequency(question.getVoterSet(),"testing")> 1);
		}
}
