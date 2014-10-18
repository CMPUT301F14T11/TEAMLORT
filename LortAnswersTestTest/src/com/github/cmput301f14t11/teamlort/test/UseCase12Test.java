package com.github.cmput301f14t11.teamlort.test;

import junit.framework.TestCase;


public class UseCase12Test extends TestCase 
{  
	//yo elvis thanks buddy!
	//Oct.18 added controller to tests
	public void testUpvote()
	{
		
		PersistentDataManager pdm = PersistentDataManager.getInstance();
		ScoreController sc = new ScoreController();
		sc.getPersistentDataManager();
		assertTrue("question initialized with more than 0 upvotes", sc.getScore() == 0);
		sc.upvote();
		fail("Upvote did not change score", answer.getScore() == 0);
		assertTrue("Same user managed to upvote same question twice", sc.checkIllegal() == 1);
	}
}
