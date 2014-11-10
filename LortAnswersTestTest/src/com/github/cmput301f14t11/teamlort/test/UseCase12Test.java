package com.github.cmput301f14t11.teamlort.test;

import java.util.Collections;

import com.github.cmput301f14t11.teamlort.Answer;
import com.github.cmput301f14t11.teamlort.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Question;
import com.github.cmput301f14t11.teamlort.QuestionController;

import android.test.ActivityInstrumentationTestCase2;
import junit.framework.TestCase;


public class UseCase12Test extends ActivityInstrumentationTestCase2 
{  
	public UseCase12Test(Class activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}

	//yo elvis thanks buddy!
	//Oct.18 added controller to tests
	public void testUpvote()
	{
		ObjectFactory obj = new ObjectFactory();
		QuestionController qsc = new QuestionController();
		
		Question question = obj.initQuestion("test title", "body", "author");
		Answer answer = null;
		answer.setAuthor("sdfsdfsd");
		answer.setBody("asdsadfsdds");
		question.getAnswerList().add(answer);
		
		assertTrue("question initialized with more than 0 upvotes", question.getAnswerList().get(0).getVoterSet().size() == 0);
		question.getAnswerList().get(0).getVoterSet().add("sdfsdfds");
		if(question.getAnswerList().get(0).getVoterSet().size() == 0)
		{
			fail("Upvote did not change score");
		}
		assertTrue("Same user managed to upvote same question twice", Collections.frequency(question.getAnswerList().get(0).getVoterSet(),"sdfsdfds")> 1);
	}
}
