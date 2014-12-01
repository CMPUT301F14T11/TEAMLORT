package com.github.cmput301f14t11.teamlort.Controller;

import com.github.cmput301f14t11.teamlort.Model.Answer;
import com.github.cmput301f14t11.teamlort.Model.ElasticManager;
import com.github.cmput301f14t11.teamlort.Model.PushQueue;
import com.github.cmput301f14t11.teamlort.Model.Question;
import com.github.cmput301f14t11.teamlort.Model.Reply;

import android.content.Context;
import android.util.Log;

/**
 * Controller for modifying a {@link Question} and it's contents ({@link Answer} and {@link Reply} objects), modifications
 * include: <br>
 * - adding {@link Answer}s or {@link Reply}s to the {@link Question}
 * <br>
 * - adding {@link Reply} objects to {@link Answer}s.
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
	
	public void setQuestion(Question question){
		this.question = question;
	}
	
	public void addQuestion(Question question){
		pushQueue.pushQuestion(question, context);
	}
	
	public void addAnswer(Answer answer){
		question.addAnswer(answer);
		pushQueue.pushAnswer(question.getID(), answer, context);
	}
	
	public void addQuestionReply(Reply reply){
		question.addReplyToStart(reply);
		pushQueue.pushQuestionReply(question.getID(), reply, context);
	}
	
	public void addAnswerReply(Reply reply, int answerPosition){
		question.getAnswer(answerPosition).addReplyToStart(reply);
		PushQueue.getInstance().pushAnswerReply(question.getID(), question.getAnswer(answerPosition).getID(), reply, context);
	}
	
	public void upVoteQuestion(String username){
		question.upVote(username);
	}
	
	public void unVoteQuestion(String username){
		question.unVote(username);
	}
	
	public void upVoteAnswer(String username, int answerPosition){
		question.getAnswer(answerPosition).upVote(username);
	}
	
	public void unVoteAnswer(String username, int answerPosition){
		question.getAnswer(answerPosition).unVote(username);
	}

}
