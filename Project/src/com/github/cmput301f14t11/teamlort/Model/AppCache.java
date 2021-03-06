package com.github.cmput301f14t11.teamlort.Model;

import java.util.Date;

import android.util.Log;

/**
 * Singleton for passing around one {@link Question} and one {@link Profile}. 
 * Get it by calling {@link #getInstance()}.
 * 
 * @author Elvis Lo
 */
public class AppCache {
	
	private static AppCache appCache = null;
	private Question question = null;
	private Profile profile = null;
	private String cityname = "";
	
	/**
	 * @return The {@link AppCache} singleton instance.
	 */
	public static AppCache getInstance()
	{
		if (appCache == null){
			appCache = new AppCache();
		}
		return appCache;
	}
	
	/**
	 * Sets the {@link Question} in appCache.
	 * 
	 * @param question A {@link Question} to set AppCache's {@link Question} to.
	 */
	public void setQuestion(Question question){
		this.question = question;
	}
	
	/**
	 * @return The {@link Question} currently held in appCache.
	 */
	public Question getQuestion(){
		return question;
	}
	
	/**
	 * Sets the {@link Profile} in appCache.
	 * 
	 * @param profile A {@link Profile} to set AppCache's {@link Profile} to.
	 */
	public void setProfile(Profile profile){
		this.profile = profile;
	}
	
	/**
	 * @return The {@link Profile} currently held in appCache.
	 */
	public Profile getProfile(){
		if(profile == null){
			profile = new Profile();
		}
		return profile;
	}
	public void setname(String provided)
	{
		this.cityname = provided;
	}
	public String returnname()
	{
		return this.cityname;
	}
	
	/**
	 * Loads the last used profile on the device. If no such profile exists,
	 * creates a new Profile with username Guest plus a random number.
	 * @author Brandon Yue
	 */
	public void InitProfile()
	{
		Profile p = LocalManager.getManager().loadProfile();
			
		if (p == null)
		{
			p = new Profile();
			p.setUsername("Guest" + (new Date()).hashCode());
			p.setLocation(0, 0);
			p.setdate(new Date());
			Log.i("LOCATION","location set");
			LocalManager.getManager().saveProfileToDefault(p);
		} else {
			p = LocalManager.getManager().loadProfile(p.getUsername());		
		}
		
		this.profile = p;
	}
}
