package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;

/**
 * LocalManager -- Auxiliary class that deals with internal file IO.
 * 
 * @author Brandon Yue
 */
public class LocalManager
{
	private static LocalManager manager = new LocalManager();
	
	private static final String FILE_PATH = "sav/";
	private static final String QUESTIONS_FILE = "questions.ser";
	private static final String PROFILE_FILE   = "profile.ser";
	
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
	private LocalManager()
	{
		
	}

	/**
	 * Save one question to file.
	 * @param saveMe
	 */
	public void saveQuestion(Question saveMe)
	{
		
	}
	
	/**
	 * Saves each question in a list to file.
	 * @param saveUs
	 */
	public void saveQuestions(ArrayList<Question> saveUs)
	{
		
	}
	
	/**
	 * Saves a user profile to file. If a profile exists, it is overwritten.
	 * @param saveMe
	 */
	public void saveProfile(Profile saveMe)
	{
		
	}

	/**
	 * Loads any Question objects saved to the device in an ArrayList. The saved files are cleared afterwards.
	 * @return An ArrayList containing all saved questions on the device.
	 */
	public ArrayList<Question> loadQuestions() 
	{
		return null;
	}
	
	/**
	 * Loads the profile saved on the device.
	 * @return The loaded profile.
	 */
	public Profile loadProfile()
	{
		return null;
	}
	
	/**
	 * Auxiliary method that saves an arbitrary object to a file.
	 * @param saveMe
	 * @param filename
	 */
	private void saveObject(Object saveMe, String filename)
	{
		
	}
	
	/**
	 * Auxiliary method that loads an arbitrary object from a file.
	 * @param filename
	 * @return
	 */
	private Object loadobject(String filename)
	{
		return null;
	}
}
