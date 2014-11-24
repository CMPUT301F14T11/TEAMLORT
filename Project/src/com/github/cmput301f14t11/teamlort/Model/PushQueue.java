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
	private static Profile profile;
	private static ProfileController pc = new ProfileController();
	private ElasticManager em = ElasticManager.getInstance();
	/**
	 * @return The {@link PushQueue} singleton instance.
	 */
	public static PushQueue getInstance(Profile p) {
		if (pushQueue == null){
			pushQueue = new PushQueue();
		}
		profile = p;
		pc.setP(profile);
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
		
		
		if (NetworkListener.checkConnection(c)){
			
			for (int i = 0; i< pushList.size(); i++){
				em.addItem(pushList.get(i));
			}
			pushList.clear();
		}
		
		else{
			//LocalManager.getManager().saveQuestions(pushList);
			pc.addTempQuestion(addMe);
		}
	}
	
	

}
