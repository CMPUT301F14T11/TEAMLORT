package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import com.github.cmput301f14t11.teamlort.Model.Answer;
import com.github.cmput301f14t11.teamlort.Model.Reply;

public class UseCase22Test extends TestCase {
	public void testQuestionSort() {
		Answer answer = new Answer();
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < 5; i++) {
			Reply reply = new Reply();
			reply.setBody(Integer.toString(i));
			list.add(Integer.toString(i));
			answer.addReplyToStart(reply);
		}
		//Check that the list of replies is ordered freshest first.
		for(int i = 0; i < 5; i++) {
			for(int j = 4; i > -1; i--){
				assertTrue(answer.getReply(i).toString() == list.get(j));
			}
		}
	}
}
