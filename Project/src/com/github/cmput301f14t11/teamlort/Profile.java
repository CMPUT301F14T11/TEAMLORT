package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;

public class Profile {
	
	
	private String username = "";
	
	protected ArrayList<Question> savedQuestionList = new ArrayList<Question>();
	protected ArrayList<Question> favedQuestionList = new ArrayList<Question>();
	protected ArrayList<Question> myQuestionList = new ArrayList<Question>();

	public void setUsername(String username) {
		this.username = username;
		
	}
	public String getUsername(){
		return username;
	}
	
	public ArrayList<Question> getSavedQuestionList(){
		return savedQuestionList;
	}
	
	public ArrayList<Question> getFavedQuestionList(){
		return favedQuestionList;
	}
	
	public ArrayList<Question> getMyQuestionList(){
		return myQuestionList;
	}
	
	// test lists method, can be deleted later
	public ArrayList<Question> getTestQuestionList(){
		ArrayList<Question> testQuestionList = new ArrayList<Question>();
		for (int i = 0; i<3; i++){
			ObjectFactory dc = new ObjectFactory();
			testQuestionList.add(dc.initQuestion("Title", "Body", "author"));
		}
		return testQuestionList;
	}
	
	public ArrayList<Question> getTestQuestionList1(){
		ArrayList<Question> testQuestionList = new ArrayList<Question>();
		for (int i = 0; i<3; i++){
			ObjectFactory dc = new ObjectFactory();
			testQuestionList.add(dc.initQuestion("Title1", "Body1", "author1"));
		}
		return testQuestionList;
	}
	
	public ArrayList<Question> getTestQuestionList2(){
		ArrayList<Question> testQuestionList = new ArrayList<Question>();
		for (int i = 0; i<3; i++){
			ObjectFactory dc = new ObjectFactory();
			testQuestionList.add(dc.initQuestion("Title2", "Body2", "author2"));
		}
		return testQuestionList;
	}
	

}
