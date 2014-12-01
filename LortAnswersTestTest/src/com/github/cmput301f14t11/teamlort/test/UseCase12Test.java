package com.github.cmput301f14t11.teamlort.test;

import java.util.Collections;

import com.github.cmput301f14t11.teamlort.Controller.QuestionController;
import com.github.cmput301f14t11.teamlort.Model.Answer;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.Question;

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
	//Dec.1 final cleanup
	public void testUpvote()
	{
		ObjectFactory obj = new ObjectFactory();
		QuestionController qsc = new QuestionController(null);
		
		Question question = obj.initQuestion("test title", "body", "author");
		Answer answer = obj.initAnswer("testing body", "author");
		qsc.setQuestion(question);
		qsc.addAnswer(answer);
		
		assertTrue("question initialized with more than 0 upvotes", question.getAnswerList().get(0).getVoterSet().size() == 0);
		qsc.upVoteAnswer("tester", 0);
		if(question.getAnswerList().get(0).getVoterSet().size() == 0)
		{
			fail("Upvote did not change score");
		}
		assertTrue("Same user managed to upvote same question twice", Collections.frequency(question.getAnswerList().get(0).getVoterSet(),"sdfsdfds")> 1);
	}
}
