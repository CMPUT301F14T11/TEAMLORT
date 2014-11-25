package com.github.cmput301f14t11.teamlort.Model;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.Toast;

import com.github.cmput301f14t11.teamlort.Controller.ProfileController;

/**
 * Singleton for creating a list of questions that were made without network access to the app to be pushed
 * 
 */
public class PushQueue {

	private static PushQueue pushQueue = null;
	private ArrayList<Question> pushList = new ArrayList<Question>();
	//private static Profile profile;
	private ArrayList<PushItemAnswer> answerList = new ArrayList<PushItemAnswer>();
	private ArrayList<PushItemReply> answerReplyList = new ArrayList<PushItemReply>();
	private ArrayList<PushItemReply> questionReplyList = new ArrayList<PushItemReply>();
	private static ProfileController pc = new ProfileController();
	private ElasticManager em = ElasticManager.getInstance();
	private Question chosenQuestion = null;
	private ArrayList<Answer> chosenAnswerList = null; 
	private ArrayList<Reply> chosenAnswerReplyList = null;
	private ArrayList<Reply> chosenQuestionRelayList = null;
	/**
	 * @return The {@link PushQueue} singleton instance.
	 */
	public static PushQueue getInstance() {
		if (pushQueue == null){
			pushQueue = new PushQueue();
		}
		//profile = p;
		//pc.setP(profile);
		return pushQueue;
	}
	
	/**
	 * @return The {@link pushList} currently held in pushQueue.
	 */
	public List<Question> getPushList() {
		return pushList;
	}
	
	/**
	 * Adds the {@link Question} to the {@link pushList}
	 * 
	 * @param addMe
	 */
	public void addQuestionToQueue(Question addMe) {
		
		pushList.add(addMe);
		
	}
	
	public void pushQuestions(){
		Thread thread = new AddThread(pushList); 
		thread.start();
	}
	
	public void pushQuestion(Question question, Context c){
		if(NetworkListener.checkConnection(c)){
			Thread thread = new AddThread(question); 
			thread.start();
		}
		else{
			addQuestionToQueue(question);
			Toast.makeText(c, "Sorry, no network connection! Change saved Locally.", Toast.LENGTH_SHORT).show();
			
		}
	}
	
	public void pushAnswer(int questionID, Question question, Answer answer, Context c)
	{
		if(NetworkListener.checkConnection(c)){
			Thread thread = new AddThread(questionID,answer); 
			thread.start();
		}
		else{
			question.getAnswerList().add(answer);
			addQuestionToQueue(question);
			Toast.makeText(c, "Sorry, no network connection! Change saved Locally.", Toast.LENGTH_SHORT).show();
			
		}
	}
	
	public void pushQuestionReply(int questionID, Question question, Reply r, Context c){
		if(NetworkListener.checkConnection(c)){
			Thread thread = new AddThread(questionID,r); 
			thread.start();
		}
		else{
			question.getReplyList().add(r);
			addQuestionToQueue(question);
			Toast.makeText(c, "Sorry, no network connection!", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void pushAnswerReply(int questionID, Question question, int answerID, Answer answer, Reply r, Context c){
		if(NetworkListener.checkConnection(c)){
			Thread thread = new AddThread(questionID,answerID,r); 
			thread.start();
		}
		else{
			
			Toast.makeText(c, "Sorry, no network connection!", Toast.LENGTH_SHORT).show();
		}
	}
}

class AddThread extends Thread {
	private int _questionID;
	private int _answerID;
	private Question _question;
	private Answer _answer;
	private Reply _questionReply;
	private Reply _answerReply;
	private int currentRequest;
	private ArrayList<Question> _questions;
	
	static final int PUSH_QUESTION = 1;
	static final int PUSH_ANSWER = 2;
	static final int PUSH_QUESTION_REPLY = 3;
	static final int PUSH_ANSWER_REPLY = 4;
	static final int PUSH_QUESTION_LIST = 5;

	public AddThread(Question question) {
		_question = question;
		currentRequest = PUSH_QUESTION;
	}
	
	public AddThread(int questionID, Answer answer) {
		_questionID = questionID;
		_answer = answer;
		currentRequest = PUSH_ANSWER;
	}
	
	public AddThread(int questionID, Reply questionReply){
		_questionID = questionID;
		_questionReply = questionReply;
		currentRequest = PUSH_QUESTION_REPLY;
	}
	
	public AddThread(int questionID, int answerID, Reply answerReply){
		_questionID = questionID;
		_answerID = answerID;
		_answerReply = answerReply;
		currentRequest = PUSH_ANSWER_REPLY;
	}
	
	public AddThread(ArrayList<Question> questions){
		_questions = questions;
		currentRequest = PUSH_QUESTION_LIST;
	}

	@Override
	public void run() {
		if (currentRequest == PUSH_QUESTION){
			ElasticManager.getInstance().addItem(_question);
		}
		
		else if (currentRequest == PUSH_ANSWER){
			_question = ElasticManager.getInstance().getItem(_questionID);
			_question.getAnswerList().add(_answer);
			ElasticManager.getInstance().addItem(_question);
		}
		
		else if (currentRequest == PUSH_QUESTION_REPLY){
			_question = ElasticManager.getInstance().getItem(_questionID);
			_question.getReplyList().add(_questionReply);
			ElasticManager.getInstance().addItem(_question);
		}
		
		else if (currentRequest == PUSH_ANSWER_REPLY){
			_question = ElasticManager.getInstance().getItem(_questionID);
			for(Answer a: _question.getAnswerList()){
				if (a.getID() == _answerID){
					a.getReplyList().add(_answerReply);
				}
			}
			ElasticManager.getInstance().addItem(_question);
		}
		else if(currentRequest == PUSH_QUESTION_LIST){
			
			for (Question q: _questions){
				ElasticManager.getInstance().addItem(q);
			}
			
		}
		else{
			
		}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}