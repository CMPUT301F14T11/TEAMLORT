package com.github.cmput301f14t11.teamlort;

public class AppCache {

	private static AppCache appCache = null;
	private Question question = null;
	
	public static AppCache getInstance(){
		if (appCache == null){
			appCache = new AppCache();
		}
		return appCache;
	}
	
	public void setQuestion(Question question){
		this.question = question;
	}
	
	public Question getQuestion(){
		return question;
	}
	
}
