package com.github.cmput301f14t11.teamlort.test;

import com.github.cmput301f14t11.teamlort.TextPrimitive;

import junit.framework.TestCase;

public class TextPrimitiveTest extends TestCase {
	public void testCreateTextPrimitive() {
		TextPrimitive tp = new TextPrimitive();
		assertTrue(tp != null);
	}
	public void testAddBody() {
		TextPrimitive tp = new TextPrimitive();
		String body = "Why is the sky blue?";
		tp.setBody(body);
		assertTrue(body == tp.getBody());
	}
	public void testAddAuthor() {
		TextPrimitive tp = new TextPrimitive();
		String author = "Some guy or girl";
		
		tp.setAuthor(author);
		assertTrue(author == tp.getAuthor());
	}
	public void testTime() {
		TextPrimitive tp = new TextPrimitive();
		assertTrue("tp.getTime is null", tp.getTime() != null);
	}
}
