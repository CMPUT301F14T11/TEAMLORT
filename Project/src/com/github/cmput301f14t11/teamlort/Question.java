package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;

public class Question extends RepliableText{
	private String title = new String();
	private ArrayList<Answer> answerList = new ArrayList<Answer>();
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	public ArrayList<Answer> getAnswerList() {
		return answerList;
	}
	public Answer getAnswer(int index) {
		return answerList.get(index);
	}
	public void addAnswer(Answer answer) {
		answerList.add(answer);
	}
	public void deleteAnswer(Answer answer) {
		answerList.remove(answer);
	}
}
