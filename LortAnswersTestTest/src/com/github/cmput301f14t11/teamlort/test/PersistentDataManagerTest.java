package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import junit.framework.TestCase;

public class PersistentDataManagerTest extends TestCase {

	public void testPersistentDataManagerCreate(){
		PersistentDataManager dataManager = new PersistentDataManager();
	}
	
	public void testAddQuestion(){
		PersistentDataManager dataManager = new PersistentDataManager();
		Question question = new Question("Why are there two amulets?");
		dataManager.addQuestion(question);
		assertTrue("Question list did not add question", dataManager.getQuestion(0) == question);
	}
	
	public void testAddQuestions(){
		PersistentDataManager dataManager = new PersistentDataManager();
		ArrayList<Question> list1 = new ArrayList();
		Question question = new Question("Why are there two amulets?");
		Question question2 = new Question("Didn't Archer already return amulet?");
		Question question3 = new Question("How does Shirou have amulet?");
		list1.append(question);
		list1.apend(question2);
		list1.append(question3);
		dataManager.addQuestions(list1);
		assertTrue("Question list did not add question 1", dataManager.getQuestion(0) == question);
		assertTrue("Question list did not add question 2", dataManager.getQuestion(0) == question2);
		assertTrue("Question list did not add question 2", dataManager.getQuestion(0) == question3);
	}
	
	public void testGetQuestion(){
		PersistentDataManager dataManager = new PersistentDataManager();
		Question question = new Question("Why are there two amulets?");
		Question question2 = new Question("Didn't Archer already return amulet?");
		Question question3 = new Question("How does Shirou have amulet?");
		dataManager.addQuestion(question);
		dataManager.addQuestion(question2);
		dataManager.addQuestion(question3);
		assertTrue("Question list could not get question 1", dataManager.getQuestion(0) == question);
		assertTrue("Question list could not get question 2", dataManager.getQuestion(1) == question2);
		assertTrue("Question list could not get question 3", dataManager.getQuestion(2) == question3);
	}
	
	public void testGetQuestions(){
		PersistentDataManager dataManager = new PersistentDataManager();
		Question question = new Question("Why are there two amulets?");
		Question question2 = new Question("Didn't Archer already return amulet?");
		Question question3 = new Question("How does Shirou have amulet?");
		dataManager.addQuestion(question);
		dataManager.addQuestion(question2);
		dataManager.addQuestion(question3);
		ArrayList<Question> partialList = dataManager.getQuestions(0, 2);
		assertTrue("Partial list does not contain question 1 where it should be", partialList.get(0) == question);
		assertTrue("Partial list does not contain question 2 where it should be", partialList.get(1) == question2);
		assertTrue("Partial list does not contain question 3 where it should be", partialList.get(2) == question3);
		ArrayList<Question> partialList2 = dataManager.getQuestions(1, 2);
		assertTrue("Partial list 2 does not contain question 2 where it should be", partialList.get(0) == question2);
		assertTrue("Partial list 2 does not contain question 3 where it should be", partialList.get(1) == question3);
	}
	
}
