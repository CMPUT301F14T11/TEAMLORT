package com.github.cmput301f14t11.teamlort.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.util.Log;

/**
 * LocalManager -- Auxiliary class that deals with internal file IO.
 * 
 * @author Brandon Yue
 */
public class LocalManager
{
	private static LocalManager manager = new LocalManager();
	
	private static final String FILE_PATH = "sav_";
	private static final String FILE_EXT = ".ser";
	private static final String QUESTIONS_FILE = "questions";
	private static final String PROFILE_FILE   = "profile";
	private static final String DEFAULT_PROFILE= "defaultProfile";

	private Context appContext = null;
	
	/**
	 * Singleton instance getter. This is used in place of new LocalManager()
	 * to ensure that there's only one object performing file IO. 
	 */
	public static LocalManager getManager()
	{
		return manager;
	}
	
	public void SetContext(Context context)
	{
		this.appContext = context;
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
	
	public void saveProfileToDefault(Profile saveMe)
	{
		saveObject(saveMe, FILE_PATH + DEFAULT_PROFILE + FILE_EXT);
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
		prof.setUsername(username);
		
		return prof;
	}
	
	/**
	 * Like loadProfile(Sring username), but loads the default profile (last used on the device).
	 */
	public Profile loadProfile()
	{
		Profile prof = (Profile) loadObject(FILE_PATH + DEFAULT_PROFILE + FILE_EXT);
		
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
			fos = appContext.openFileOutput(filename, Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(saveMe);
			
			oos.close();
			fos.close();
			
			Log.d("com.github.cmput301f14t11.teamlort.LocalManager.saveObject(Serializable, String)",
					"Successfully saved an object to " + filename);
		}
		catch (IOException e)
		{
			Log.e("com.github.cmput301f14t11.teamlort.LocalManager.saveObject(Serializable, String)",
			      "Failed to save object: " + e.getMessage());
		}
		finally
		{
			
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
			fis = appContext.openFileInput(filename);
			ois = new ObjectInputStream(fis);
			
			result = ois.readObject();
			
			ois.close();
			fis.close();
		}
		catch (FileNotFoundException e)
		{
			Log.e("com.github.cmput301f14t11.teamlort.LocalManager.loadObject(String)",
					"Failed to load object (FileNotFoundException): " + e.getMessage());
		}
		catch (ClassNotFoundException e)
		{
			Log.e("com.github.cmput301f14t11.teamlort.LocalManager.loadObject(String)",
					"Failed to load object (ClassNotFoundException): " + e.getMessage());
		}
		catch (IOException e)
		{
			Log.e("com.github.cmput301f14t11.teamlort.LocalManager.loadObject(String)",
					"Failed to load object (IOException): " + e.getMessage());
		}
		
		return result;
	}
}
