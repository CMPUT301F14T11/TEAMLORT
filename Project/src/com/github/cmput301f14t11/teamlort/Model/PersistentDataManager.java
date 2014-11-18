package com.github.cmput301f14t11.teamlort.Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import android.util.Log;


/**
 * @author  Brandon Yue
 */
public class PersistentDataManager
extends Observable
{
	/**
	 * @uml.property  name="managerInstance"
	 * @uml.associationEnd  
	 */
	static private PersistentDataManager managerInstance = new PersistentDataManager();
	public ArrayList<Observer> observers;
	
	/**
	 * @author   Brandon Yue
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
		//ElasticManager.serverQuery("Foo!");
	}
	
	public void pullFromCloud()
	{
		// TODO: Research ElasticSearch
		//ElasticManager.serverQuery("Foo!");
	}
	
	public void addQuestion(Question q)
	{
		data.cacheQuestions.add(q);
		data.uploadBuffer.add(q.getID());
		Save();
	}
	
	public boolean editQuestion(int questionID, Question editedQuestion)
	{
		Question toEdit = null;
		for (Question q : data.cacheQuestions)
		{
			// Check to make sure the question to edit is in the cache.
			if (q.getID() == questionID)
			{
				toEdit = q;
				break;
			}
		}
		
		if (toEdit == null) return false;

		toEdit.setTitle(editedQuestion.getTitle());
		toEdit.setBody(editedQuestion.getBody());
		toEdit.setAuthor(editedQuestion.getAuthor());
		
		return true;
	}
	
	public void getMore()
	{
		// TODO: Research ElasticSearch
		//ElasticManager.serverQuery("Foo!");
	}
	
	public Question get(int id) // Replace Object with datatype of ID
	{
		for (Question q : data.cacheQuestions)
			if (q.getID() == id)
			{
				return q;
			}
		return null;
	}
	
	public void saveQuestion(Question q)
	{
		if (!data.savedQuestions.contains(q.getID()))
			data.savedQuestions.add(q.getID());
	}
	
	public void unsaveQuestion(Question q)
	{
		data.savedQuestions.remove(q);
	}
	
	public void faveQuestion(Question q)
	{
		if (!data.favedQuestions.contains(q.getID()))
			data.favedQuestions.add(q.getID());
	}
	
	public void unfaveQuestion(Question q)
	{
		data.favedQuestions.remove(q);
	}
	
	public ArrayList<Question> getAllQuestions()
	{
		return data.cacheQuestions;
	}
	//added in since it was part of UML - Sam 10/28/2014
	public ArrayList<Question> getQuestion() {
		// TODO Auto-generated method stub
		ArrayList<Question> result = new ArrayList<Question>();
		return result;
	}
	
	public ArrayList<Question> getSavedQuestions()
	{
		ArrayList<Question> result = new ArrayList<Question>();
		
		for (Question q : data.cacheQuestions)
		{
			if (data.savedQuestions.contains(q.getID()))
				result.add(q);
		}
		
		return result;
	}
	
	public ArrayList<Question> getFavedQuestions() 
	{
		ArrayList<Question> result = new ArrayList<Question>();
		
		for (Question q : data.cacheQuestions)
		{
			if (data.favedQuestions.contains(q.getID()))
				result.add(q);
		}
		
		return result;
	}
	
	public ArrayList<Question> searchQuestions(String terms)//changed from String [] to String - sam
	{
		// Going to implement this as searching titles for keywords.
		// WIP.
		//for each question in listofquestion on database
		//look through title
		//if found, return founded
		//if not, return null
		Question founded;
		
		
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
	
	
	/*
	 * LocalManager -- auxiliary class to handle file I/O
	 */
	private static class LocalManager
	{
		private static final URI filePath;
		private LocalManager manager = new LocalManager();
		static
		{
			URI initURI = null;
			
			try
			{
				initURI = new URI("sav/data.ser");
			}
			catch (URISyntaxException e)
			{
				Log.e("com.github.cmput301f14t11.teamlort.Model.PersistentDataManager.LocalManager.staticInitializer", "LocalManager's file URI could not be initialized! (URISyntaxException)");
			}
			
			filePath = initURI;
		}
		
		
		private LocalManager() 
		{
			
		}
		
		public LocalManager getLocalManager()
		{
			return manager;
		}
		
		public static void saveFile(Serializable obj)
		{
			FileOutputStream fos = null;
			ObjectOutputStream oos = null;
			
			try
			{
				fos = new FileOutputStream(filePath.getPath());
				oos = new ObjectOutputStream(fos);
				
				oos.writeObject(obj);
				
				oos.close();
				fos.close();
			}
			catch (IOException e)
			{
				Log.e("com.github.cmput301f14t11.teamlort.Model.PersistentDataManager.LocalManager.saveFile(Serializable)", "Failed to save file!");
			}
		}
		
		public static Object loadFile()
		{
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			UserData result = null;
			
			try
			{
				fis = new FileInputStream(filePath.getPath());
				ois = new ObjectInputStream(fis);
				
				result = (UserData) ois.readObject();
				
				ois.close();
				fis.close();
			}
			catch (StreamCorruptedException e)
			{
				Log.e("com.github.cmput301f14t11.teamlort.Model.PersistentDataManager.LocalManager.loadFile()", "Could not load user data (StreamCorruptedException)");
			}
			catch (IOException e)
			{
				Log.e("com.github.cmput301f14t11.teamlort.Model.PersistentDataManager.LocalManager.loadFile()", "Could not load user data (IOException)");
			}
			catch (ClassNotFoundException e)
			{
				Log.e("com.github.cmput301f14t11.teamlort.Model.PersistentDataManager.LocalManager.loadFile()", "Could not load user data (ClassNotFoundException)");
			}
			
			return result;
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
		public ArrayList<Integer>  savedQuestions;
		public ArrayList<Integer>  favedQuestions;
		public ArrayList<Integer>  uploadBuffer;
	}
	private static UserData data;
	
	
}
