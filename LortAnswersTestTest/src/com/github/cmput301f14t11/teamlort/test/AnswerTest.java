package com.github.cmput301f14t11.teamlort.test;

import com.github.cmput301f14t11.teamlort.Answer;

import junit.framework.TestCase;

public class AnswerTest extends TestCase {
	public void testCreate() {
		Answer answer = new Answer();
		assertTrue("Answer was not initialized", answer != null);
	}
}
