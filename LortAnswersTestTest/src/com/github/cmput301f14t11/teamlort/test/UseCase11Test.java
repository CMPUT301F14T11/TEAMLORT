package com.github.cmput301f14t11.teamlort.test;

import java.util.Collections;

import android.test.ActivityInstrumentationTestCase2;

import com.github.cmput301f14t11.teamlort.HomeActivity;
import com.github.cmput301f14t11.teamlort.Controller.Qlistcontroller;
import com.github.cmput301f14t11.teamlort.Controller.QuestionController;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.Question;

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
		//Nov.10 - changed test to accept controller changes
		//Dec.1 - final cleanup
		public void testUpvote()
		{
			ObjectFactory obj = new ObjectFactory();
			QuestionController qsc = new QuestionController(null);
			
			Question question = obj.initQuestion("test title", "body", "author");
			qsc.setQuestion(question);
			assertTrue("question initialized with more than 0 upvotes", question.getScore() >= 0);
			qsc.upVoteQuestion("testing");
			assertFalse("Upvote did not change score",  qsc.getquestion().getScore()== 0);
			assertTrue("Same user managed to upvote same question twice", Collections.frequency(question.getVoterSet(),"testing")> 1);
		}
}
