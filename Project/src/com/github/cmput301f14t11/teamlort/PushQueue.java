package com.github.cmput301f14t11.teamlort;

import java.util.List;

/**
 * Singleton for creating a list of questions that were made without network access to the app to be pushed
 * 
 */
public class PushQueue {

	private static PushQueue pushQueue = null;
	private List<Question> pushList;
	
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
	public void addToQueue(Question addMe) {
		//TODO stub
	}
}
