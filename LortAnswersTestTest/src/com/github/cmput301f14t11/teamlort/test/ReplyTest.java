package com.github.cmput301f14t11.teamlort.test;

import com.github.cmput301f14t11.teamlort.Reply;

import junit.framework.TestCase;

public class ReplyTest extends TestCase {
	public void testReply() {
		Reply reply = new Reply();
		assertTrue("Reply not initialized", reply != null);
		//Checking to make the date is good to go
		assertTrue("Reply date not initialized",reply.getTime() != null);
	}
}
