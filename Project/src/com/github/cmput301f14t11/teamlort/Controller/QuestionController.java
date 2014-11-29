package com.github.cmput301f14t11.teamlort.Controller;

import com.github.cmput301f14t11.teamlort.Model.Answer;
import com.github.cmput301f14t11.teamlort.Model.ElasticManager;
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
	public void providecontext(Context context)
	{
		this.context = context;
	}
	
	public void addQuestion(Question question){
		
	}
	
	public void addAnswer(Answer answer){
		
	}
	
	public void addQuestionReply(Reply reply, int questionID){

	}
	
	public void addAnswerReply(){
		
	}

}
