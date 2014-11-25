package com.github.cmput301f14t11.teamlort.Controller;

import com.github.cmput301f14t11.teamlort.Model.Answer;
import com.github.cmput301f14t11.teamlort.Model.ElasticManager;
import com.github.cmput301f14t11.teamlort.Model.Question;
import com.github.cmput301f14t11.teamlort.Model.Reply;

import android.content.Context;

/**
 * Controller for modifying a {@link Question} and it's contents ({@link Answer} and {@link Reply} objects) and related 
 * details.
 * 
 * @author Elvis Lo
 */
public class QuestionController {
	// for testing purposes, I will be skipping the pushque procedure and directly sending questions to elastic manager
	ElasticManager elc = ElasticManager.getInstance();
	NetworkController nc = new NetworkController();
	Context ctx;
	public void providecontext(Context provided)
	{
		ctx = provided;
	}
	
	public void addQuestion(Question question){
		//TODO stub
		//
		//elc.providecontext(ctx);
		elc.addItem(question);
	}
	
	public void addReply(Reply reply, int questionID){
		//TODO stub
	}

}
