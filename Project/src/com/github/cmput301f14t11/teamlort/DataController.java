package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;

import com.github.cmput301f14t11.teamlort.Model.PersistentDataManager;
import com.github.cmput301f14t11.teamlort.test.Question;

public class DataController {

	PersistentDataManager dataManager;
	
	public DataController(){
		
		dataManager = PersistentDataManager.getInstance();
		
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
		Question tempQuestion = dataManager.getQuestion(questionID); //This should get the most up to date tempQuestion
		tempQuestion.addAnswer(answer);			//Note: Possibility of getQuestion locking the real question until update complete
		editQuestion(tempQuestion); //Note: editQuestion should modify immediately to avoid conflicts in updating question?
	}
	
	public void replyToQuestion(int questionID, Reply reply){
		//Note: This may need changing depending on how we are going to modify existing questions.
		Question tempQuestion = dataManager.getQuestion(questionID); //This should get the most up to date tempQuestion
		tempQuestion.addReply(answer);			//Note: Possibility of getQuestion locking the real question until update complete
		editQuestion(tempQuestion); //Note: editQuestion should modify immediately to avoid conflicts in updating question?
	}
	
	public void replyToQuestion(int questionID, Reply reply, int answerIndex){
		//Note: This may need changing depending on how we are going to modify existing questions.
		Question tempQuestion = dataManager.getQuestion(questionID); //This should get the most up to date tempQuestion
		tempQuestion.getAnswer(answerIndex).addReply(reply);	//Note: Possibility of getQuestion locking the real question until update complete
		editQuestion(tempQuestion); //Note: editQuestion should modify immediately to avoid conflicts in updating question?
	}
	
	
}
