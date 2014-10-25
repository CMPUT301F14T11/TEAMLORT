package com.github.cmput301f14t11.teamlort.Model;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.github.cmput301f14t11.teamlort.Question;

public class PersistentDataManager
extends Observable
{
	static private PersistentDataManager managerInstance = new PersistentDataManager();
	public ArrayList<Observer> observers;
	
	static public enum SORT_METHOD
	{
		BY_DATE,
		BY_SCORE,
		BY_PICTURE
	}
	
	public static PersistentDataManager getInstance()
	{
		return managerInstance;
	}
	
	private PersistentDataManager() {}
	
	public void pushToCloud()
	{
		// TODO: Research ElasticSearch
		ElasticManager.serverQuery("Foo!");
	}
	
	public void pullFromCloud()
	{
		// TODO: Research ElasticSearch
		ElasticManager.serverQuery("Foo!");
	}
	
	public void addQuestion(Question q)
	{
		data.uploadBuffer.add(q);
		Save();
	}
	
	public void getMore()
	{
		// TODO: Research ElasticSearch
		ElasticManager.serverQuery("Foo!");
	}
	
	public Question get(Object id) // Replace Object with datatype of ID
	{
		for (Question q : data.cacheQuestions)
			if (q.getId() == id)
			{
				return q;
			}
		return null;
	}
	
	public void saveQuestion(Question q)
	{
		if (!data.savedQuestions.contains(q))
			data.savedQuestions.add(q);
	}
	
	public void unsaveQuestion(Question q)
	{
		data.savedQuestions.remove(q);
	}
	
	public void faveQuestion(Question q)
	{
		if (!data.favedQuestions.contains(q))
			data.favedQuestions.add(q);
	}
	
	public void unfaveQuestion(Question q)
	{
		data.favedQuestions.remove(q);
	}
	
	public ArrayList<Question> getAllQuestions()
	{
		ArrayList<Question> result = new ArrayList<Question>();
		
		result.addAll(data.cacheQuestions);
		result.addAll(data.savedQuestions);
		result.addAll(data.favedQuestions);
		
		return result;
	}
	
	public ArrayList<Question> getSavedQuestions()
	{
		return data.savedQuestions;
	}
	
	public ArrayList<Question> getFavedQuestions() 
	{
		return data.favedQuestions;
	}
	
	public ArrayList<Question> searchQuestions(String [] terms)
	{
		// Going to implement this as searching titles for keywords.
		// WIP.
		return null;
	}
	
	public void sortQuestions(SORT_METHOD method)
	{
		switch (method)
		{
		case BY_DATE:
			break;

		case BY_SCORE:
			break;
			
		case BY_PICTURE:
			break;
			
		default:
			break;
			
		}
	}
	
	public String getUsername()
	{
		return data.userName;
	}
	
	public void setUsername(String name)
	{
		data.userName = name;
	}
	
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
	private static void Save()
	{
		LocalManager.saveFile(PersistentDataManager.data);
	}
	private static void Load()
	{
		PersistentDataManager.data = (UserData) LocalManager.loadFile();
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
		public ArrayList<Question> uploadBuffer;
	}
	private static UserData data;
}
