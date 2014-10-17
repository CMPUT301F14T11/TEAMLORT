package com.github.cmput301f14t11.teamlort.test;

import junit.framework.TestCase;


public class UseCase11Test extends TestCase 
{  
		//yo elvis thanks buddy!
		public void testUpvote()
		{
			String username = "SIVLEOL";
			Answer answer = new Answer();
			assertTrue("question initialized with more than 0 upvotes", answer.getScore() == 0);
			answer.upvote(username);
			assertTrue("Upvote did not change score", answer.getScore() == 1);
			answer.upvote(username);
			assertTrue("Same user managed to upvote same question twice", answer.getScore() == 1);
		}
}
