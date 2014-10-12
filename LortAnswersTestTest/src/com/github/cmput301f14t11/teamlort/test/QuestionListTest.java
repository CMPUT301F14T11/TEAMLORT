package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import junit.framework.TestCase;

public class QuestionListTest extends TestCase {

	public void testCreateQuestion(){
		QuestionList questionList = new QuestionList();
	}
	
	public void testAddQuestion(){
		QuestionList questionList = new QuestionList();
		Question question = new Question("Why are there two amulets?");
		questionList.addQuestion(question);
		assertTrue("Question list did not add question", questionList.getQuestion(0) == question);
	}
	
	public void testGetQuestion(){
		QuestionList questionList = new QuestionList();
		Question question = new Question("Why are there two amulets?");
		Question question2 = new Question("Didn't Archer already return amulet?");
		Question question3 = new Question("How does Shirou have amulet?");
		questionList.addQuestion(question);
		questionList.addQuestion(question2);
		questionList.addQuestion(question3);
		assertTrue("Question list could not get question 1", questionList.getQuestion(0) == question);
		assertTrue("Question list could not get question 2", questionList.getQuestion(1) == question2);
		assertTrue("Question list could not get question 3", questionList.getQuestion(2) == question3);
	}
	
	public void testGetQuestions(){
		QuestionList questionList = new QuestionList();
		Question question = new Question("Why are there two amulets?");
		Question question2 = new Question("Didn't Archer already return amulet?");
		Question question3 = new Question("How does Shirou have amulet?");
		questionList.addQuestion(question);
		questionList.addQuestion(question2);
		questionList.addQuestion(question3);
		ArrayList<Question> partialList = questionList.getQuestions(0, 2);
		assertTrue("Partial list does not contain question 1 where it should be", partialList.get(0) == question);
		assertTrue("Partial list does not contain question 2 where it should be", partialList.get(1) == question2);
		assertTrue("Partial list does not contain question 3 where it should be", partialList.get(2) == question3);
		ArrayList<Question> partialList2 = questionList.getQuestions(1, 2);
		assertTrue("Partial list 2 does not contain question 2 where it should be", partialList.get(0) == question2);
		assertTrue("Partial list 2 does not contain question 3 where it should be", partialList.get(1) == question3);
	}
	
}
