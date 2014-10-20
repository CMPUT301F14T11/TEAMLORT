package com.github.cmput301f14t11.teamlort.test;

import com.github.cmput301f14t11.teamlort.RepliableText;

import junit.framework.TestCase;

public class RepliableTextTest extends TestCase {
	public void testCreate() {
		RepliableText rp = new RepliableText();
		assertTrue(rp != null);
	}
}
