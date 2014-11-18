package com.github.cmput301f14t11.teamlort.test;

import android.graphics.drawable.Drawable;

import com.github.cmput301f14t11.teamlort.Model.RepliableText;
import com.github.cmput301f14t11.teamlort.Model.Reply;

import junit.framework.TestCase;

public class RepliableTextTest extends TestCase {
	public void testCreate() {
		RepliableText rp = new RepliableText();
		assertTrue(rp != null);
	}
	public void testScore() {
		RepliableText rp = new RepliableText();
		String username = "Bobby";
		assertTrue("Score not initialized to 0",rp.getScore() == 0);
		rp.upVote(username);
		assertTrue("Person not added to voter list", rp.getVoterSet().contains(username));
		assertTrue("Did not upvote properly",rp.getScore() == 1);
		rp.unVote(username);
		assertFalse("Person not removed from voter list", rp.getVoterSet().contains(username));
		assertTrue("Did not unvote properly",rp.getScore() == 0);
	}
	public void testPicture() {
		RepliableText rp = new RepliableText();
		assertTrue("Picture not initialized to null", rp.getPicture() == null);
		assertFalse("hasPicture sending false positive", rp.hasPicture());
		Drawable drawnImage = null;
		rp.addPicture(drawnImage);
		assertTrue("Picture not set", rp.getPicture() == drawnImage);
		
		//TODO: 
		//Should have an asserttrue(has pic) but that won't work till
		//there's a test picture to work with because I'm just setting
		//it to null right now.
		
		rp.deletePicture(); 
		assertTrue("Picture not deleted", rp.getPicture() == null);
	}
	public void testReply() {
		RepliableText rp = new RepliableText();
		assertTrue("Did not start with no replies",rp.getReplyList().size() == 0);
		Reply reply = new Reply();
		reply.setBody("Something something");
		rp.addReply(reply);
		assertTrue("Didn't add reply", rp.getReplyList().size() == 1);
		assertTrue("getReply returned wrong reply", rp.getReply(0) == reply);
		rp.deleteReply(reply);
		assertTrue("Didn't delete reply", rp.getReplyList().size() == 0);
	}
}
