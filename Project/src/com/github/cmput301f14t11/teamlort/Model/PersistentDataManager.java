package com.github.cmput301f14t11.teamlort.Model;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import com.github.cmput301f14t11.teamlort.Question;

/**
 * @author  eunderhi
 */
public class PersistentDataManager
{
	/**
	 * @uml.property  name="managerInstance"
	 * @uml.associationEnd  
	 */
	static private PersistentDataManager managerInstance = new PersistentDataManager();
	
	/**
	 * @author   eunderhi
	 */
	static public enum SORT_METHOD
	{
		/**
		 * @uml.property  name="bY_DATE"
		 * @uml.associationEnd  
		 */
		BY_DATE,
		/**
		 * @uml.property  name="bY_SCORE"
		 * @uml.associationEnd  
		 */
		BY_SCORE,
		/**
		 * @uml.property  name="bY_PICTURE"
		 * @uml.associationEnd  
		 */
		BY_PICTURE,
		/**
		 * @uml.property  name="bY_REPLIES"
		 * @uml.associationEnd  
		 */
		BY_REPLIES
	}
	
	static PersistentDataManager getInstance()
	{
		return managerInstance;
	}
	
	private PersistentDataManager() {}
	
	public void pushToCloud() { }
	public void pullFromCloud() { }
	public void addQuestion() { }
	public Question get(Object id) { return null; } // Replace Object with datatype of ID
	public ArrayList<Question> getAllQuestions() { return null; }
	public ArrayList<Question> getSavedQuestions() { return null; }
	public ArrayList<Question> getFavedQuestions() { return null; }
	public ArrayList<Question> searchQuestions() { return null; }
	public void sortQuestions(SORT_METHOD method) { }
	public String getUsername() { return null; }
	public void setUsername(String name) { }
	public void notifyObservers() { }
	
	/*
	 * ElasticManager -- auxiliary class to handle all server I/O
	 */
	private static class ElasticManager
	{
		private static final URI serverAddress = null;
		
		public static Object serverQuery(String query)
		{
			//TODO
			return null;
		}
	}
	
	/*
	 * LocalManager -- auxiliary class to handle file I/O
	 */
	private static class LocalManager
	{
		private static final URI filePath = null;
		
		public static void saveFile(Serializable obj)
		{
			//TODO
		}
		
		public static Object loadFile()
		{
			//TODO
			return null;
		}
	}
	
	/*
	 * UserData container class that makes saving to file simpler.
	 */
	private class UserData
	implements Serializable
	{
		private static final long serialVersionUID = 1L;
		
		public String userName;
		public ArrayList<Question> cacheQuestions;
		public ArrayList<Question> savedQuestions;
		public ArrayList<Question> favedQuestions;
	}
}
