package com.github.cmput301f14t11.teamlort;

/**
 * Singleton for passing around one {@link Question} and one {@link Profile}.
 * 
 * @author Elvis Lo
 */
public class AppCache {
	
	private static AppCache appCache = null;
	private Question question = null;
	private Profile profile = null;
	
	/**
	 * @return The {@link AppCache} singleton instance.
	 */
	public static AppCache getInstance(){
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
	
}
