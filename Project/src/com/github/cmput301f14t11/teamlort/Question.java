package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;

/**
 * @author  eunderhi
 */
public class Question extends RepliableText{
	/**
	 * @uml.property  name="title"
	 */
	private String title = new String();
	/**
	 * @uml.property  name="answerList"
	 */
	private ArrayList<Answer> answerList = new ArrayList<Answer>();
	
	/**
	 * @return
	 * @uml.property  name="title"
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param newTitle
	 * @uml.property  name="title"
	 */
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	/**
	 * @return
	 * @uml.property  name="answerList"
	 */
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
