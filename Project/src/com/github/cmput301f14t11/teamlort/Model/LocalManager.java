package com.github.cmput301f14t11.teamlort.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import android.util.Log;

/**
 * LocalManager -- Auxiliary class that deals with internal file IO.
 * 
 * @author Brandon Yue
 */
public class LocalManager
{
	private static LocalManager manager = new LocalManager();
	
	private static final String FILE_PATH = "sav/";
	private static final String FILE_EXT = ".ser";
	private static final String QUESTIONS_FILE = "questions";
	private static final String PROFILE_FILE   = "profile";
	
	/**
	 * Singleton instance getter.
	 */
	public static LocalManager getManager()
	{
		return manager;
	}
	
	/**
	 * Private constructor to prevent arbitrary instantiation.
	 */
	public LocalManager()
	{
		
	}

	/**
	 * Save one question to file.
	 * @param saveMe
	 */
	public void saveQuestion(Question saveMe)
	{
		ArrayList<Question> qList = (ArrayList<Question>) loadObject(FILE_PATH + QUESTIONS_FILE + FILE_EXT);
		if (qList == null) qList = new ArrayList<Question>();
		
		qList.add(saveMe);
		
		saveObject(qList, FILE_PATH + QUESTIONS_FILE + FILE_EXT);
	}
	
	/**
	 * Saves each question in a list to file.
	 * @param saveUs
	 */
	public void saveQuestions(ArrayList<Question> saveUs)
	{
		ArrayList<Question> qList = (ArrayList<Question>) loadObject(FILE_PATH + QUESTIONS_FILE + FILE_EXT);
		if (qList == null) qList = new ArrayList<Question>();
		
		qList.addAll(saveUs);
		
		saveObject(qList, FILE_PATH + QUESTIONS_FILE + FILE_EXT);
	}
	
	/**
	 * Saves a user profile to file. If a profile exists, it is overwritten.
	 * @param saveMe
	 */
	public void saveProfile(Profile saveMe)
	{
		saveObject(saveMe, FILE_PATH + PROFILE_FILE + saveMe.getUsername() + FILE_EXT);
	}

	/**
	 * Loads any Question objects saved to the device in an ArrayList. The saved files are cleared afterwards.
	 * @return An ArrayList containing all saved questions on the device.
	 */
	public ArrayList<Question> loadQuestions() 
	{
		ArrayList<Question> qList = (ArrayList<Question>) loadObject(FILE_PATH + QUESTIONS_FILE + FILE_EXT);
		if (qList == null) qList = new ArrayList<Question>();
		
		// Clear the saved file.
		saveObject(new ArrayList<Question>(), FILE_PATH + QUESTIONS_FILE + FILE_EXT);
		
		return qList;
	}
	
	/**
	 * Loads the profile saved on the device.
	 * @return The loaded profile.
	 */
	public Profile loadProfile(String username)
	{
		Profile prof = (Profile) loadObject(FILE_PATH + PROFILE_FILE + username + FILE_EXT);
		if (prof == null) prof = new Profile();
		
		return prof;
	}
	
	/**
	 * Auxiliary method that saves an arbitrary object to a file.
	 * @param saveMe
	 * @param filename
	 */
	private void saveObject(Serializable saveMe, String filename)
	{
		FileOutputStream fos;
		ObjectOutputStream oos;
		try
		{
			fos = new FileOutputStream(FILE_PATH + filename);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(saveMe);
			
			oos.close();
			fos.close();
		}
		catch (IOException e)
		{
			Log.e("com.github.cmput301f14t11.teamlort.LocalManager.saveObject(Serializable, String)",
			      "Failed to save object: " + e.getMessage());
		}
	}
	
	/**
	 * Auxiliary method that loads an arbitrary object from a file.
	 * @param filename
	 * @return
	 */
	private Object loadObject(String filename)
	{
		Object result = null;
		
		FileInputStream fis;
		ObjectInputStream ois;
		try
		{
			fis = new FileInputStream(FILE_PATH + filename);
			ois = new ObjectInputStream(fis);
			
			result = ois.readObject();
			
			ois.close();
			fis.close();
		}
		catch (FileNotFoundException e)
		{
			Log.e("com.github.cmput301f14t11.teamlort.LocalManager.loadObject(String)",
					"Failed to save object (FileNotFoundException): " + e.getMessage());
		}
		catch (ClassNotFoundException e)
		{
			Log.e("com.github.cmput301f14t11.teamlort.LocalManager.loadObject(String)",
					"Failed to save object (ClassNotFoundException): " + e.getMessage());		}
		catch (IOException e)
		{
			Log.e("com.github.cmput301f14t11.teamlort.LocalManager.loadObject(String)",
					"Failed to save object (IOException): " + e.getMessage());
		}
		
		return result;
	}
}
