package com.github.cmput301f14t11.teamlort.Controller;

import com.github.cmput301f14t11.teamlort.Model.Answer;
import com.github.cmput301f14t11.teamlort.Model.ElasticManager;
import com.github.cmput301f14t11.teamlort.Model.PushQueue;
import com.github.cmput301f14t11.teamlort.Model.Question;
import com.github.cmput301f14t11.teamlort.Model.Reply;

import android.content.Context;
import android.util.Log;

/**
 * Controller for modifying a {@link Question} and it's contents ({@link Answer} and {@link Reply} objects) and related 
 * details.
 * 
 * @author Elvis Lo
 */
public class QuestionController {
	

	Context context;
	Question question;
	PushQueue pushQueue = PushQueue.getInstance();
	
	public QuestionController(Context context) {
		super();
		this.context = context;
	}
	
	public void addQuestion(Question question){
		pushQueue.pushQuestion(question, context);
	}
	
	public void addAnswer(Answer answer){
		
	}
	
	public void addReply(Reply reply, int questionID){

	}
	
	public void addAnswerReply(){
		
	}
	
	public void upvoteQuestion(){
		
	}
	
	public void upvoteAnswer(){
		
	}

}
