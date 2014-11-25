package com.github.cmput301f14t11.teamlort.Model;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.github.cmput301f14t11.teamlort.Controller.ProfileController;

/**
 * Singleton for creating a list of questions that were made without network access to the app to be pushed
 * 
 */
public class PushQueue {

	private static PushQueue pushQueue = null;
	private ArrayList<Question> pushList = new ArrayList<Question>();
	//private static Profile profile;
	private ArrayList<PushItem> answerList = new ArrayList<PushItem>();
	private ArrayList<PushItem> answerReplyList = new ArrayList<PushItem>();
	private ArrayList<PushItem> questionReplyList = new ArrayList<PushItem>();
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
	public void addQuestionToQueue(Question addMe, Context c) {
		pc.addCreatedQuestion(addMe);
		if (!pushList.contains(addMe)){
			pushList.add(addMe);	
		}
		
		
		if (NetworkListener.checkConnection(c))
		{
			for (int i = 0; i< pushList.size(); i++)
			{
				em.addItem(pushList.get(i));
			}
			pushList.clear();
		}
		
		else
		{
			pc.addTempQuestion(addMe);
		}
	}
	
	private void pushQuestion(Question question) {
		em.addItem(question);
		
	}
		
	public void pushAnswer(int questionID,Answer answer, Context c)
	{
		if (NetworkListener.checkConnection(c))
		{
			chosenQuestion =  em.getItem(questionID);
			chosenAnswerList = chosenQuestion.getAnswerList();
			if (!chosenAnswerList.contains(answer))
			{
				chosenAnswerList.add(answer);
				chosenQuestion.setAnswerList(chosenAnswerList);
			}
			pushQuestion(chosenQuestion);
		}
		else
		{
			pc.addTempQuestion(chosenQuestion);
		}
	}
	
	public void pushQuestionReplyList(int questionID, Reply reply, Context c)
	{
		if (NetworkListener.checkConnection(c))
		{
			chosenQuestion = em.getItem(questionID);
			chosenQuestionRelayList = chosenQuestion.getReplyList();
			if (!chosenQuestionRelayList.contains(reply))
			{
				chosenAnswerReplyList.add(reply);
			}
		
			pushQuestion(chosenQuestion);
		}
		else
		{
			pc.addTempQuestion(chosenQuestion);
		}
	}
	
	public void pushAnswerReplyList(int questionID, int answerID, Reply reply, Context c)
	{
		if (NetworkListener.checkConnection(c))
		{		
			chosenQuestion = em.getItem(questionID);
			for(Answer a: chosenQuestion.getAnswerList())
			{
				if(a.getID() == answerID)
				{
					if (!a.getReplyList().contains(reply))
					{
						a.getReplyList().add(reply);
					}
				}
			}
			pushQuestion(chosenQuestion);
		}
		
		else
		{
			pc.addTempQuestion(chosenQuestion);
		}
	}
	
//	public void pushQuestions()
//	{
//		for (Question q : pushList)
//		{
//			em.addItem(q);
//		}
//		
//		pushList.clear();
//	}

	
}
