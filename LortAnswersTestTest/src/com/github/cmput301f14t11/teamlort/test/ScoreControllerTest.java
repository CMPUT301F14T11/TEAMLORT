package com.github.cmput301f14t11.teamlort.test;

import com.github.cmput301f14t11.teamlort.RepliableText;
import com.github.cmput301f14t11.teamlort.ScoreController;
import com.github.cmput301f14t11.teamlort.UserController;

import junit.framework.TestCase;

public class ScoreControllerTest extends TestCase {
	
	public void testCreate (){
		ScoreController sc = new ScoreController();
		assertTrue("nothing is created", sc!=null);
	}
	
	public void testAddToVotedList(){
		RepliableText rt = new RepliableText();
		// test the empty set of string
		assertTrue("Shouldn't have voter now",rt.getVoterSet().size()==0);
		ScoreController sc = new ScoreController();
		// test adding one person's name into the voter list
		sc.addToVotedList(rt,"Ann");
		assertTrue("The size of vote list should be 1",rt.getVoterSet().size() == 1);
		assertTrue("Ann should be in the voter list", rt.getVoterSet().contains("Ann"));
		
	}
	
	public void testRemoveFromVotedList(){
		RepliableText rt = new RepliableText();
		ScoreController sc = new ScoreController();
		sc.addToVotedList(rt, "Ann");
		sc.addToVotedList(rt, "Bob");
		assertTrue("size of voter list should be 2", rt.getVoterSet().size() == 2);
		sc.removeFromVotedList(rt,"Ann");
		assertTrue("size of voter lsit should be 1", rt.getVoterSet().size() == 1);
		assertTrue("Ann should not in the list",!rt.getVoterSet().contains("Ann"));
		
		boolean thrown = false;
		assertFalse("thrown should be false as initilized",thrown);
		try {sc.removeFromVotedList(rt,"Joe");}
		catch (NullPointerException e){
			thrown = true;
		}
		assertTrue("should throw a null pointer exception", thrown);
		sc.removeFromVotedList(rt,"Bob");
		assertTrue("size of voter lsit should be 0", rt.getVoterSet().size() == 0);
		assertTrue("Ann should not in the list",!rt.getVoterSet().contains("Bob"));	
		
	}
	
	public void testIncreaseScore(){
		RepliableText rt = new RepliableText();
		assertTrue("score of rt should be 0", rt.getScore() == 0);
		ScoreController sc = new ScoreController();
		sc.increaseScore(rt);
		assertTrue("socre of rt should be 1", rt.getScore() == 1);
		sc.increaseScore(rt);
		assertTrue("score of rt should be 2", rt.getScore() == 2);
	}
	
	public void testCheckIllegal(){
		ScoreController sc = new ScoreController();
		UserController uc = new UserController();
		RepliableText rt = new RepliableText();
		rt.setAuthor("Ann");
		assertFalse("No user logging in",sc.checkIllegal(rt));
		//First case, verify that author cannot change the score.
		uc.login("Ann");
		assertFalse("Authors can't vote their own questions",sc.checkIllegal(rt));
		//Second case, verify that the user cannot modify the score of same text twice.
		uc.login("Bob");
		assertTrue("Bob now should be able to change score",sc.checkIllegal(rt));
		sc.increaseScore(rt);
		//Since Bob already change the score, he is no longer able to modify the score
		//of this text.
		assertFalse("Bob should not be legal to change the socre",sc.checkIllegal(rt));

	}

}
