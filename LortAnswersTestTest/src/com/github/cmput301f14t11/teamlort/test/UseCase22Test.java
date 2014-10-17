package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import junit.framework.TestCase;

public class UseCase22Test extends TestCase {
	public void testQuestionSort() {
		Answer answer = new Answer();
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < 5; i++) {
			list.add(Integer.toString(i));
			Reply reply = new reply(Integer.toString(i));
			answer.addReply(reply);
		}
		answer.sortByDate();
		//for each reply check it's string is equal to list entry which is in date order
		for(int i = 0; i < 5; i++) {
			assertTrue(answer.getReplies[i].toString() == list[i]);
		}
	}
}
