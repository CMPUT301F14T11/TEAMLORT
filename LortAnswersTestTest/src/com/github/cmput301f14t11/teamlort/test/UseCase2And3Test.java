package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import com.github.cmput301f14t11.teamlort.Answer;
import com.github.cmput301f14t11.teamlort.AppCache;
import com.github.cmput301f14t11.teamlort.Question;
import com.github.cmput301f14t11.teamlort.QuestionViewActivity;
import com.github.cmput301f14t11.teamlort.Reply;
import android.test.ActivityInstrumentationTestCase2;

public class UseCase2And3Test extends ActivityInstrumentationTestCase2<QuestionViewActivity> {
	
	public UseCase2And3Test() {
		super(QuestionViewActivity.class);
	}
	
	public void testCaseTwo(){
		//Test case for Use Case #2: User views a question and its answers
		AppCache appCache = AppCache.getInstance();
		String title = "What was Lancer's noble phantasm?";
		String desc = "His spear did weird stuff, I'm confused";
		Question question = new Question();
		question.setTitle(title);
		question.setBody(desc);
		appCache.setQuestion(question);
		
		//Title and description must be retrievable for display.
		Question testQuestion = appCache.getQuestion();
		assertTrue("Title doesn't match title", testQuestion.getTitle() == title);
		assertTrue("Description doesn't match desc", testQuestion.getBody() == desc);
		
		Answer answer = new Answer();
		answer.setBody("It reverses cause and effect");
		Answer answer2 = new Answer();
		answer2.setBody("Ufotable too flashy");
		testQuestion.addAnswer(answer);
		testQuestion.addAnswer(answer2);
		
		//Any answers the question has must be retrievable for display.
		ArrayList<Answer> list1 = testQuestion.getAnswerList();
		assertTrue("Question does not have answer", list1.get(0) == answer);
		assertTrue("Question does not have answer2", list1.get(1) == answer2);
	}
	
	public void testCaseThree(){
		//Test case for Use Case #3: User views replies to an answer or question
		String title = "How do I fly?";
		String rep = "this is incredibly stupid";
		String rep2 = "this is incredibly silly";
		Question question = new Question();
		question.setTitle(title);
		Reply reply = new Reply();
		reply.setBody(rep);
		Reply reply2 = new Reply();
		reply.setBody(rep2);
		question.addReply(reply);
		question.addReply(reply2);
		//Test getting replies from a question
		ArrayList<Reply> replies = question.getReplyList();
		assertTrue("Answer reply doesn't match reply3", replies.get(0) == reply);
		assertTrue("Answer reply2 doesn't match reply4", replies.get(1) == reply2);
		
		String rep3 = "He's going to fly for the portal again!";
		String rep4 = "John what the heck are you doing.";
		Reply reply3 = new Reply();
		reply3.setBody(rep3);
		Reply reply4 = new Reply();
		reply4.setBody(rep4);
		Answer answer = new Answer();
		answer.setBody("Use the jetpack");
		answer.addReply(reply3);
		answer.addReply(reply4);
		question.addAnswer(answer);
		
		//Test getting replies from an answer
		Answer testAnswer = question.getAnswer(0);
		ArrayList<Reply> replies2 = testAnswer.getReplyList();
		assertTrue("Answer reply doesn't match reply3", replies2.get(0) == reply3);
		assertTrue("Answer reply2 doesn't match reply4", replies2.get(1) == reply4);
		
	}
	
}
