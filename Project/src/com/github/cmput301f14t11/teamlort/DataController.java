package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

import com.github.cmput301f14t11.teamlort.Model.PersistentDataManager;
import com.github.cmput301f14t11.teamlort.Question;

/**
 * {@link Deprecated}, should not be used.
 * 
 * Allows building of {@link Question}, {@link Answer} and {@link Reply} objects.
 * 
 * @author Elvis Lo
 *
 */
public class DataController {

	PersistentDataManager dataManager;
	
	public DataController(){
		
		dataManager = PersistentDataManager.getInstance();
		
	}
	
	public Question initQuestion(String title, String body, String author){
		Question question = new Question();
		
		question.setTitle(title);
		question.setBody(body);
		question.setAuthor(author);
		question.setID();
		return question;
	}
	
	public Question initQuestion(String title, String body, String author, Drawable image){
		//An overload that takes in an image
		Question question = new Question();
		
		question.setTitle(title);
		question.setBody(body);
		question.setAuthor(author);
		question.addPicture(image);
		question.setID();
		return question;
	}
	
	public Answer initAnswer(String body, String author){
		Answer answer = new Answer();
		
		answer.setBody(body);
		answer.setAuthor(author);
		answer.setID();
		return answer;
	}
	
	public void addQuestion(Question question){
		dataManager.getAllQuestions().add(question);
	}

	public void addQuestions(ArrayList<Question> questionList){
		dataManager.getAllQuestions().addAll(questionList);
	}
	
	public void editQuestion(Question editedQuestion){
		//Note: Whether this takes in ID and username depends on dataManager's editQuestion method, modify this method as needed.
		dataManager.editQuestion(editedQuestion.getID(), editedQuestion);
	}
	
	public void answerQuestion(int questionID, Answer answer){
		//Note: This may need changing depending on how we are going to modify existing questions.
		Question tempQuestion = dataManager.get(questionID); //This should get the most up to date tempQuestion
		tempQuestion.addAnswer(answer);			//Note: Possibility of getQuestion locking the real question until update complete
		editQuestion(tempQuestion); //Note: editQuestion should modify immediately to avoid conflicts in updating question?
	}
	
	public void replyToQuestion(int questionID, Reply reply){
		//Note: This may need changing depending on how we are going to modify existing questions.
		Question tempQuestion = dataManager.get(questionID); //This should get the most up to date tempQuestion
		tempQuestion.addReply(reply);			//Note: Possibility of getQuestion locking the real question until update complete
		editQuestion(tempQuestion); //Note: editQuestion should modify immediately to avoid conflicts in updating question?
	}
	
	public void replyToQuestion(int questionID, Reply reply, int answerIndex){
		//Note: This may need changing depending on how we are going to modify existing questions.
		Question tempQuestion = dataManager.get(questionID); //This should get the most up to date tempQuestion
		tempQuestion.getAnswer(answerIndex).addReply(reply);	//Note: Possibility of getQuestion locking the real question until update complete
		editQuestion(tempQuestion); //Note: editQuestion should modify immediately to avoid conflicts in updating question?
	}
	
	
}
