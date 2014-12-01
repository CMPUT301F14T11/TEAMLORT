package com.github.cmput301f14t11.teamlort.Model;

import java.util.ArrayList;

import com.github.cmput301f14t11.teamlort.Controller.ProfileController;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Singleton for creating a list of questions that were made without network access to the app to be pushed
 * 
 */
public class PushQueue {

	private static PushQueue pushQueue = null;
	private ArrayList<Question> questionList = new ArrayList<Question>();
	private ArrayList<PushItemAnswer> answerList = new ArrayList<PushItemAnswer>();
	private ArrayList<PushItemReply> answerReplyList = new ArrayList<PushItemReply>();
	private ArrayList<PushItemReply> questionReplyList = new ArrayList<PushItemReply>();
	private ProfileController pc = new ProfileController();
	public static boolean PUSH_REQUEST = false;
//	private ElasticManager em = ElasticManager.getInstance();

	/**
	 * @return The {@link PushQueue} singleton instance.
	 */
	public static PushQueue getInstance() {
		if (pushQueue == null){
			pushQueue = new PushQueue();
		}
		return pushQueue;
	}
	
	/**
	 * check the connection and push a single question to the server if network is available 
	 * @param question the question need to push 
	 * @param c context from the current activity
	 */
	public void pushQuestion(Question question, Context c)
	{
		if(NetworkListener.checkConnection(c))
		{
			new PushQuestions().execute(question);
		}
		else{
			questionList.add(question);
			pc.addTempQuestion(question);
			pc.getProfile().setPushRequest(true);
			Toast.makeText(c, "Sorry, no network connection! Change saved Locally.", Toast.LENGTH_SHORT).show();
		}
	
		
	}
	
	/**
	 * check the connection and push an answer of a question to the server 
	 * if network is available 
	 * @param questionID id of the question
	 * @param answer the answer added to the given question 
	 * @param c context from the current activity
	 */
	public void pushAnswer(int questionID, Answer answer, Context c)
	{
		if(NetworkListener.checkConnection(c))
		{
			Log.i("PushQue","pushanswer being called");
			new PushAnswers().execute(new PushItemAnswer(answer, questionID));
		}
		else{
			pc.getProfile().setPushRequest(true);
			answerList.add(new PushItemAnswer(answer,questionID));
			Toast.makeText(c, "Sorry, no network connection! Change saved Locally.", Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * check the connection and push a reply of a question to the server 
	 * if network is available 
	 * @param questionID id of the question
	 * @param reply the reply added to the given question 
	 * @param c context from the current activity
	 */
	public void pushQuestionReply(int questionID, Reply reply, Context c){
		if(NetworkListener.checkConnection(c))
		{
			new PushQuestionReplies().execute(new PushItemReply(reply, questionID));
		}
		else{
			pc.getProfile().setPushRequest(true);
			questionReplyList.add(new PushItemReply(reply, questionID));
			Toast.makeText(c, "Sorry, no network connection! Change saved Locally.", Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * check the connection and push a reply of an answer to the server 
	 * if network is available 
	 * @param questionID id of the question
	 * @param answerID id of that answer
	 * @param reply the reply added to the given answer 
	 * @param c context from the current activity
	 */
	public void pushAnswerReply(int questionID, int answerID, Reply reply, Context c){
		if(NetworkListener.checkConnection(c))
		{
			new PushAnswerReplies().execute(new PushItemReply(reply, questionID, answerID));
		}
		else{
			pc.getProfile().setPushRequest(true);
			answerReplyList.add(new PushItemReply(reply, questionID, answerID));
			Toast.makeText(c, "Sorry, no network connection! Change saved Locally.", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * push all items in the queue into the server
	 */
	public void pushAll()
	{
		new PushQuestions().execute(questionList.toArray(new Question[questionList.size()]));
		new PushAnswers().execute(answerList.toArray(new PushItemAnswer[answerList.size()]));
		new PushQuestionReplies().execute(questionReplyList.toArray(new PushItemReply[questionReplyList.size()]));
		new PushAnswerReplies().execute(answerReplyList.toArray(new PushItemReply[answerReplyList.size()]));
		AppCache.getInstance().getProfile().getTempQuestionList().clear();
	}
	
	/**
	 * This class used UI thread to push questions from the question list in 
	 * push queue class to the server
	 * @author Branden_Yue
	 *
	 */
	private class PushQuestions
	extends AsyncTask<Question, Void, Void>
	{	
		@Override
		protected void onPreExecute()
		{
			PushQueue.this.questionList.clear();
			super.onPreExecute();
		}
		
		@Override
		protected Void doInBackground(Question... params)
		{
			for (Question question : params)
			{
				Log.i("Pushque","did this run?");
				ElasticManager.getInstance().addItem(question);
			}
			return null;
		}
	}
	/**
	 * This class used UI thread to push answers from the answer list in 
	 * push queue class to the server
	 * @author Branden_Yue
	 *
	 */
	private class PushAnswers
	extends AsyncTask<PushItemAnswer, Void, Void>
	{
		@Override
		protected void onPreExecute()
		{
			PushQueue.this.answerList.clear();
			super.onPreExecute();
		}
		
		@Override
		protected Void doInBackground(PushItemAnswer... params)
		{
			for (PushItemAnswer answer : params)
			{
				Question question = ElasticManager.getInstance().getItem(answer.getQuestionID());
				question.addAnswer(answer.getPushItem());
				ElasticManager.getInstance().addItem(question);
			}
			return null;
		}
	}
	/**
	 * This class used UI thread to push Question replies from the PushItemReply list in 
	 * push queue class to the server
	 * @author Branden_Yue
	 */
	private class PushQuestionReplies
	extends AsyncTask<PushItemReply, Void, Void>
	{
		@Override
		protected void onPreExecute()
		{
			PushQueue.this.questionReplyList.clear();
			super.onPreExecute();
		}
		
		@Override
		protected Void doInBackground(PushItemReply... params)
		{
			for (PushItemReply reply : params)
			{
				Question question = ElasticManager.getInstance().getItem(reply.getQuestionID());
				question.addReplyToStart(reply.getPushItem());
				ElasticManager.getInstance().addItem(question);
			}
			return null;
		}
	}
	/**
	 * This class used UI thread to push answer replies from the PushItemReply list in 
	 * push queue class to the server
	 * @author Branden_Yue
	 */
	private class PushAnswerReplies
	extends AsyncTask<PushItemReply, Void, Void>
	{
		@Override
		protected void onPreExecute()
		{
			PushQueue.this.answerReplyList.clear();
			super.onPreExecute();
		}
		
		@Override
		protected Void doInBackground(PushItemReply... params)
		{
			for (PushItemReply reply : params)
			{
				Question question = ElasticManager.getInstance().getItem(reply.getQuestionID());
				for(Answer a : question.getAnswerList()){
					if (a.getID() == reply.getAnswerID()){
						a.addReplyToStart(reply.getPushItem());
					}
				}
				ElasticManager.getInstance().addItem(question);
			}
			return null;
		}
	}
	public ArrayList<Question> returntemplist()
	{
		return questionList;
	}
}

/*
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
}*/