package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import com.github.cmput301f14t11.teamlort.DataController;
import com.github.cmput301f14t11.teamlort.Question;

import android.graphics.Bitmap;

import junit.framework.TestCase;

public class UseCase1Through4Test extends TestCase {

	public void testCaseOne(){
		//Test case for Use Case #1: User browses questions
		PersistentDataManager dataManager = PersistentDataManager.getInstance();
		DataController dataController = new DataController();
		//If there are no questions, return no questions.
		ArrayList<Question> emptyList = dataManager.getAllQuestions();
		assertTrue("Data manager returned questions when there should be none", emptyList.size() == 0);
		
		Question question = new Question("Why are there two amulets?");
		Question question2 = new Question("Didn't Archer already return amulet?");
		Question question3 = new Question("How does Shirou have amulet?");
		Question question4 = new Question("Are these spoilers for F/SN?");
		Question question5 = new Question("Which route is this?");
		dataController.addQuestion(question);
		dataController.addQuestion(question2);
		dataController.addQuestion(question3);
		dataController.addQuestion(question4);
		dataController.addQuestion(question5);
		//App can retrieve a list of questions from PersistentDataManager
		ArrayList<Question> partialList = dataManager.getQuestions(0, 2);
		assertTrue("Partial list does not contain question 1 where it should be", partialList.get(0) == question);
		assertTrue("Partial list does not contain question 2 where it should be", partialList.get(1) == question2);
		assertTrue("Partial list does not contain question 3 where it should be", partialList.get(2) == question3);
		//App can add additional questions to current question list.
		partialList.append(dataManager.getQuestions(2, 4));
		assertTrue("Partial list does not contain question 4 where it should be", partialList.get(3) == question2);
		assertTrue("Partial list does not contain question 5 where it should be", partialList.get(4) == question3);
	}
	
	public void testCaseTwo(){
		//Test case for Use Case #2: User views a question and its answers
		PersistentDataManager dataManager = PersistentDataManager.getInstance();
		DataController dataController = new DataController();
		String title = "What was Lancer's noble phantasm?";
		String desc = "His spear did weird stuff, I'm confused";
		Question question = new Question(title);
		question.setDescription(desc);
		dataController.addQuestion(question);
		
		//Title and description must be retrievable for display.
		Question testQuestion = dataManager.getQuestion(0);
		assertTrue("Title doesn't match title", question.getTitle() == title);
		assertTrue("Description doesn't match desc", question.getDescription() == desc);
		
		Answer answer = new Answer("It reverses cause and effect");
		Answer answer2 = new Answer("Ufotable too flashy");
		question.addAnswer(answer);
		question.addAnswer(answer2);
		
		//Any answers the question has must be retrievable for display.
		ArrayList<Answer> list1 = question.getAnswers();
		assertTrue("Question does not have answer", list1.get(0) == answer);
		assertTrue("Question does not have answer2", list1.get(1) == answer2);
	}
	
	public void testCaseThree(){
		//Test case for Use Case #3: User views replies to an answer or question
		String title = "How do I fly?";
		String rep = "this is incredibly stupid";
		String rep2 = "this is incredibly silly";
		Question question = new Question(title);
		Reply reply = new Reply(rep);
		Reply reply2 = new Reply(rep2);
		question.addReply(reply);
		question.addReply(reply2);
		//Test getting replies from a question
		ArrayList<Reply> replies = question.getReplies();
		assertTrue("Answer reply doesn't match reply3", replies.get(0) == reply);
		assertTrue("Answer reply2 doesn't match reply4", replies.getReply(1) == reply2);
		
		String rep3 = "He's going to fly for the portal again!";
		String rep4 = "John what the heck are you doing.";
		Reply reply3 = new Reply(rep3);
		Reply reply4 = new Reply(rep4);
		Answer answer = new Answer("Use the jetpack");
		answer.addReply(reply3);
		answer.addReply(reply4);
		question.addAnswer(answer);
		
		//Test getting replies from an answer
		Answer testAnswer = question.getAnswer(0);
		ArrayList<Reply> replies2 = testAnswer.getReplies();
		assertTrue("Answer reply doesn't match reply3", replies2.get(0) == reply3);
		assertTrue("Answer reply2 doesn't match reply4", replies2.getReply(1) == reply4);
		
	}
	
	public void testCaseFour(){
		//Test case for Use Case #4: Author makes a question
		
		//Test that user can enter title into question
		String title = "How do I fly?";
		Question question = new Question(title);
		assertTrue("title doesn't match title", question.getTitle() == title);
		
		//Test that user can enter description into question
		String desc = "I have some feathers halp";
		assertTrue("Desc not empty by default", question.getDescription() == "");
		question.setDescription(desc);
		assertTrue("Description doesn't match desc", question.getDescription() == desc);
		
		//Test add image to question.
		Bitmap bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888 );
		question.addImage(bitmap);
		assertTrue("Bitmap does not match bitmap or getImage failed", question.getImage() == bitmap);
		
	}
	
}
