package com.github.cmput301f14t11.teamlort.test;

import com.github.cmput301f14t11.teamlort.Answer;
import com.github.cmput301f14t11.teamlort.Question;

import junit.framework.TestCase;

public class QuestionTest extends TestCase {
	public void testCreate() {
		Question question = new Question();
		assertTrue("Question is not initialized", question != null);
	}
	public void testAnswerList() {
		Question question = new Question();
		Answer answer = new Answer();
		
		question.addAnswer(answer);
		assertTrue("Answer not added", question.getAnswerList().size() == 1);
		assertTrue("getAnswer returned wrong answer", question.getAnswer(0) == answer);
		question.deleteAnswer(answer); 
		assertTrue("Answer not deleted", question.getAnswerList().size() == 0);
	}
}
