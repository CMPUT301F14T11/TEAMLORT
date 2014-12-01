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
	
	/**
	 * Constructor for {@link QuestionController}.
	 * <br><br>
	 * Usage example from within an activity: <br>
	 * QuestionController controller = new QuestionController
	 * ({@link android.content.ContextWrapper#getApplicationContext() getApplicationContext()});
	 * 
	 * @param context
	 */
	public QuestionController(Context context) {
		super();
		this.context = context;
	}
	
	/**
	 * Sets the {@link Question} QuestionController has reference to and will be used to modify.
	 * <br><br>
	 * Usage example: <br>
	 * QuestionController controller = new QuestionController
	 * ({@link android.content.ContextWrapper#getApplicationContext() getApplicationContext()}); <br>
	 * controller.setQuestion(aQuestion);
	 * 
	 * @param question
	 */
	public void setQuestion(Question question){
		this.question = question;
	}
	
	/**
	 * Calls {@link PushQueue#pushQuestion(Question, Context)} to post the {@link Question} online.
	 * 
	 * @param question
	 */
	public void addQuestion(Question question){
		pushQueue.pushQuestion(question, context);
	}
	
	/**
	 * Adds the {@link Answer} to {@link Question} that QuestionController has reference to, {@link #setQuestion} must be
	 * called before this method or an error will be thrown. <br><br>
	 * Calls {@link PushQueue#pushAnswer(questionID, Answer, Context) pushAnswer(question.getID(), answer, context)} 
	 * to post the {@link Answer} to the {@link Question} online.
	 * 
	 * @param answer
	 */
	public void addAnswer(Answer answer){
		question.addAnswer(answer);
		pushQueue.pushAnswer(question.getID(), answer, context);
	}
	
	/**
	 * Adds the {@link Reply} to {@link Question} that QuestionController has reference to, {@link #setQuestion} must be
	 * called before this method or an error will be thrown. <br><br>
	 * Calls {@link PushQueue#pushQuestionReply(int, Reply, Context) pushQuestionReply(question.getID(), reply, context)} 
	 * to post the {@link Reply} to the {@link Question} online.
	 * 
	 * @param answer
	 */
	public void addQuestionReply(Reply reply){
		question.addReplyToStart(reply);
		pushQueue.pushQuestionReply(question.getID(), reply, context);
	}
	
	/**
	 * Adds the {@link Reply} to an {@link Answer} to the {@link Question} that QuestionController has reference to, 
	 * {@link #setQuestion} must be called before this method or an error will be thrown. <br><br>
	 * Calls {@link PushQueue#pushAnswerReply(int, int, Reply, Context) pushQuestionReply(question.getID(), reply, context)} 
	 * to post the {@link Reply} to an {@link Answer} online.
	 * 
	 * @param answer
	 */
	public void addAnswerReply(Reply reply, int answerPosition){
		question.getAnswer(answerPosition).addReplyToStart(reply);
		PushQueue.getInstance().pushAnswerReply(question.getID(), question.getAnswer(answerPosition).getID(), reply, context);
	}
	
	
	/**
	 * Calls {@link RepliableText#upVote(username) upVote(username)} on the {@link Question} that QuestionController has reference to,
	 * {@link #setQuestion} must be called before this method or an error will be thrown.
	 * 
	 * @param username
	 */
	public void upVoteQuestion(String username){
		question.upVote(username);
	}
	
	/**
	 * Calls {@link RepliableText#unVote(username) unVote(username)} on the {@link Question} that QuestionController has reference to,
	 * {@link #setQuestion} must be called before this method or an error will be thrown.
	 * 
	 * @param username
	 */
	public void unVoteQuestion(String username){
		question.unVote(username);
	}
	
	/**
	 * Calls {@link RepliableText#upVote(username) upVote(username)} on an {@link Answer} to the {@link Question} that QuestionController has reference to,
	 * {@link #setQuestion} must be called before this method or an error will be thrown.
	 * 
	 * @param username
	 */
	public void upVoteAnswer(String username, int answerPosition){
		question.getAnswer(answerPosition).upVote(username);
	}
	
	/**
	 * Calls {@link RepliableText#unVote(username) unVote(username)} on an {@link Answer} to the {@link Question} that QuestionController has reference to,
	 * {@link #setQuestion} must be called before this method or an error will be thrown.
	 * 
	 * @param username
	 */
	public void unVoteAnswer(String username, int answerPosition){
		question.getAnswer(answerPosition).unVote(username);
	}
	/**
	 * returns question the controller is handling right now
	 * {@link #setQuestion} must be called before this method or an error will be thrown.
	 * @return question
	 */
	public Question getquestion()
	{
		return this.question;
	}

}
