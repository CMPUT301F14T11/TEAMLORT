package com.github.cmput301f14t11.teamlort.Model;

import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
/**
 * 
 * @author emmett, Elvis Lo
 * Vote class that pushes vote changes of an answer to
 * cloud. Ex. When a user upvotes a question, that information
 * is sent to elasticSearch. 
 */
public class Vote {
	
	private static String username;
	
	public Vote() {	
	}
	
	/**
	 * This method checks for if there is connection, and if there is, 
	 * make a thread ({@link AsyncTask}) to push an upvote or downvote 
	 * for a question up to server through {@link ElasticManager}.
	 * 
	 * @param questionID The ID of the question the answer belongs to
	 * @param userName
	 * @param context Usually 'this' is originally passed in from an activity.
	 */
	public void voteQuestion(int questionID, String userName, Context context){
		if(NetworkListener.checkConnection(context)) {
			username = userName;
			new PushQuestionVote().execute(questionID);
		}
		else {
			Toast.makeText(context, "No connection vote won't be saved", Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * This method checks for if there is connection, and if there is, 
	 * make a thread ({@link AsyncTask}) to push an upvote or downvote 
	 * for a answer up to server through {@link ElasticManager}.
	 * 
	 * @param questionID The ID of the question the answer belongs to
	 * @param userName
	 * @param context Usually 'this' is originally passed in from an activity.
	 */
	public void voteAnswer(int questionID, Answer answer, String userName, Context context){
		if(NetworkListener.checkConnection(context)) {
			username = userName;
			PushItemAnswer pushItem = new PushItemAnswer(answer, questionID);
			new PushAnswerVote().execute(pushItem);
		}
		else {
			Toast.makeText(context, "No connection vote won't be saved", Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * A thread to upvote or unvote a question.
	 * <br><br>
	 * Usage example: new PushQuestionVote().execute(questionID);
	 * 
	 * @author Elvis Lo
	 */
	private class PushQuestionVote
	extends AsyncTask<Integer, Void, Void>
	{	
		
		@Override
		protected Void doInBackground(Integer... params)
		{
			for (int questionID : params)
			{
				Question question = ElasticManager.getInstance().getItem(questionID);
				if(question.getVoterSet().contains(username)){
					question.unVote(username);
				} else {
					question.upVote(username);
				}
				ElasticManager.getInstance().addItem(question);
			}
			return null;
		}
	}
	
	/**
	 * A thread to upvote or unvote an answer.
	 * <br><br>
	 * Usage example: new PushAnswerVote().execute({@link PushItemAnswer});
	 * 
	 * @author Elvis Lo
	 */
	private class PushAnswerVote
	extends AsyncTask<PushItemAnswer, Void, Void>
	{	
		
		@Override
		protected Void doInBackground(PushItemAnswer... params)
		{
			for (PushItemAnswer item : params)
			{
				Question question = ElasticManager.getInstance().getItem(item.getQuestionID());
				ArrayList<Answer> answerList = question.getAnswerList();
				for (Answer answer : answerList){
					if (answer.getID() == item.getPushItem().getID()){
						if(answer.getVoterSet().contains(username)){
							answer.unVote(username);
						} else {
							answer.upVote(username);
						}
					}
				}
				ElasticManager.getInstance().addItem(question);
			}
			return null;
		}
	}
	
}
