package com.github.cmput301f14t11.teamlort.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import junit.framework.TestCase;
import android.content.Context;

import com.github.cmput301f14t11.teamlort.Answer;
import com.github.cmput301f14t11.teamlort.AppCache;
import com.github.cmput301f14t11.teamlort.LocalManager;
import com.github.cmput301f14t11.teamlort.Profile;
import com.github.cmput301f14t11.teamlort.ProfileController;
import com.github.cmput301f14t11.teamlort.Question;
import com.github.cmput301f14t11.teamlort.Reply;

public class UseCase171920Test extends TestCase {

	// UseCase 17
	public void testSaveQuestionLocally() {
		Question question = new Question();
		Profile profile = new Profile();
		ProfileController pc = new ProfileController();
		AppCache ac = new AppCache();
		LocalManager lm = new LocalManager();
		
		ac.setProfile(profile);
		
		assertEquals("Profiles do not match", ac.getProfile(), profile);
		
		pc.addSavedQuestion(question);
		
		assertTrue("SaveList does not contain question", profile.getSavedQuestionList().contains(question));
		assertTrue("SaveList size is wrong", profile.getSavedQuestionList().size() != 0);
		
		lm.saveQuestion(question);
		
		assertTrue("Question not saved locally", lm.loadQuestions().contains(question));
		assertTrue("Local Question list is empty", lm.loadQuestions().size() != 0);

	}

	// UseCase 19
	public void testSaveFavoriteLocally() {
		Question question = new Question();
		Profile profile = new Profile();
		ProfileController pc = new ProfileController();
		AppCache ac = new AppCache();
		LocalManager lm = new LocalManager();
		
		ac.setProfile(profile);
		
		assertEquals("Profiles do not match", ac.getProfile(), profile);
		
		pc.addFavedQuestion(question);
		
		assertTrue("FavList does not contain question", profile.getFavedQuestionList().contains(question));
		assertTrue("FavList size is wrong", profile.getFavedQuestionList().size() != 0);
		
		lm.saveQuestion(question);
		
		assertTrue("Question not saved locally", lm.loadQuestions().contains(question));
		assertTrue("Local Question list is empty", lm.loadQuestions().size() != 0);

}

	// UseCase 20
	public void testSaveAuthReplyLocally() {
        Question question = new Question();
        Reply reply = new Reply();
		Profile profile = new Profile();
		ProfileController pc = new ProfileController();
		AppCache ac = new AppCache();
		LocalManager lm = new LocalManager();
        
        ac.setProfile(profile);
		
		assertEquals("Profiles do not match", ac.getProfile(), profile);
		
		question.addReply(reply);
		
		assertTrue("Question does not contain reply", question.getReplyList().contains(reply));
		
		lm.saveQuestion(question);
	
		assertTrue("Question not saved locally", lm.loadQuestions().contains(question));
		assertTrue("Local Question list is empty", lm.loadQuestions().size() != 0);
		
		int index = lm.loadQuestions().indexOf(question);
		
		assertTrue("Reply not saved locally", lm.loadQuestions().get(index).getReplyList().contains(reply));
}

	public void testSaveAuthAnswerLocally() {
        Question question = new Question();
        Answer answer = new Answer();
		Profile profile = new Profile();
		ProfileController pc = new ProfileController();
		AppCache ac = new AppCache();
		LocalManager lm = new LocalManager();
        
        ac.setProfile(profile);
		
		assertEquals("Profiles do not match", ac.getProfile(), profile);
		
		question.addAnswer(answer);
		
		assertTrue("Question does not contain answer", question.getAnswerList().contains(answer));
		
		lm.saveQuestion(question);
	
		assertTrue("Question not saved locally", lm.loadQuestions().contains(question));
		assertTrue("Local Question list is empty", lm.loadQuestions().size() != 0);
		
		int index = lm.loadQuestions().indexOf(question);
		
		assertTrue("Answer not saved locally", lm.loadQuestions().get(index).getAnswerList().contains(answer));
}
}
