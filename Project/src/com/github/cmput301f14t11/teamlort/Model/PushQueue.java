package com.github.cmput301f14t11.teamlort.Model;

import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.github.cmput301f14t11.teamlort.Controller.ProfileController;

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
	private static ProfileController pc = new ProfileController();
	private ElasticManager em = ElasticManager.getInstance();

	/**
	 * @return The {@link PushQueue} singleton instance.
	 */
	public static PushQueue getInstance() {
		if (pushQueue == null){
			pushQueue = new PushQueue();
		}
		return pushQueue;
	}
	
	public void pushQuestion(Question question, Context c){
		if(NetworkListener.checkConnection(c)){
			Thread thread = new AddThread(question); 
			thread.start();
		}
		else{
			questionList.add(question);
			Toast.makeText(c, "Sorry, no network connection! Change saved Locally.", Toast.LENGTH_SHORT).show();
			
		}
	}
	
	public void pushAnswer(int questionID, Answer answer, Context c)
	{
		if(NetworkListener.checkConnection(c)){
			Thread thread = new AddThread(questionID,answer); 
			thread.start();
		}
		else{
			answerList.add(new PushItemAnswer(answer,questionID));
			Toast.makeText(c, "Sorry, no network connection! Change saved Locally.", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void pushQuestionReply(int questionID, Reply r, Context c){
		if(NetworkListener.checkConnection(c)){
			Thread thread = new AddThread(questionID,r); 
			thread.start();
		}
		else{
			questionReplyList.add(new PushItemReply(r, questionID));
			Toast.makeText(c, "Sorry, no network connection! Change saved Locally.", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void pushAnswerReply(int questionID, int answerID, Reply r, Context c){
		if(NetworkListener.checkConnection(c)){
			Thread thread = new AddThread(questionID,answerID,r); 
			thread.start();
		}
		else{
			answerReplyList.add(new PushItemReply(r, questionID, answerID));
			Toast.makeText(c, "Sorry, no network connection! Change saved Locally.", Toast.LENGTH_SHORT).show();
		}
	}

	public void pushAll()
	{
		// Ensure thread safety
		ArrayList<Question> qListCopy =       (ArrayList<Question>) questionList.clone(); 
		ArrayList<PushItemAnswer> aListCopy = (ArrayList<PushItemAnswer>) answerList.clone();
		ArrayList<PushItemReply> qrListCopy = (ArrayList<PushItemReply>) questionReplyList.clone();
		ArrayList<PushItemReply> arListCopy = (ArrayList<PushItemReply>) answerReplyList.clone();
		
		new PushQuestions().execute(qListCopy);
		new PushAnswers().execute(aListCopy);
		new PushQuestionReplies().execute(arListCopy);
		new PushAnswerReplies().execute(qrListCopy);
	}
	
	private class PushQuestions
	extends AsyncTask<ArrayList<Question>, Void, Void>
	{	
		@Override
		protected void onPreExecute()
		{
			PushQueue.this.questionList.clear();
			super.onPreExecute();
		}
		
		@Override
		protected Void doInBackground(ArrayList<Question>... params)
		{
			for (Question question : params[0])
			{
				ElasticManager.getInstance().addItem(question);
			}
			return null;
		}
	}
	private class PushAnswers
	extends AsyncTask<ArrayList<PushItemAnswer>, Void, Void>
	{
		@Override
		protected void onPreExecute()
		{
			PushQueue.this.answerList.clear();
			super.onPreExecute();
		}
		
		@Override
		protected Void doInBackground(ArrayList<PushItemAnswer>... params)
		{
			for (PushItemAnswer answer : params[0])
			{
				// Do something with each answer
			}
			return null;
		}
	}
	private class PushQuestionReplies
	extends AsyncTask<ArrayList<PushItemReply>, Void, Void>
	{
		@Override
		protected void onPreExecute()
		{
			PushQueue.this.questionReplyList.clear();
			super.onPreExecute();
		}
		
		@Override
		protected Void doInBackground(ArrayList<PushItemReply>... params)
		{
			for (PushItemReply reply : params[0])
			{
				// Do something with each reply
			}
			return null;
		}
	}
	private class PushAnswerReplies
	extends AsyncTask<ArrayList<PushItemReply>, Void, Void>
	{
		@Override
		protected void onPreExecute()
		{
			PushQueue.this.answerReplyList.clear();
			super.onPreExecute();
		}
		
		@Override
		protected Void doInBackground(ArrayList<PushItemReply>... params)
		{
			for (PushItemReply reply : params[0])
			{
				// Do something with each reply
			}
			return null;
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