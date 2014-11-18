package com.github.cmput301f14t11.teamlort.Model;

import java.util.ArrayList;

/**
 * @author  eunderhi
 */

public class Question extends RepliableText{
	/**
	 *Model of a quesiton
	 */
	private static final long serialVersionUID = 1L;
	
	private String title = new String();
	/**
	 *List of answers to question
	 */
	private ArrayList<Answer> answerList = new ArrayList<Answer>();
	
	/**
	 * @return the title of the question
	 * 
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param newTitle 
	 * Sets title of the question
	 */
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	/**
	 * @return the list of answers to the question
	 */
	public ArrayList<Answer> getAnswerList() {
		return answerList;
	}
	/**
	 * @param index
	 * @return the answer at index index
	 */
	public Answer getAnswer(int index) {
		return answerList.get(index);
	}
	/**
	 * @param answer 
	 * adds answer to answerlist
	 */
	public void addAnswer(Answer answer) {
		answerList.add(answer);
	}
	
	/**
	 * @param answer
	 * removes answer from answerlist
	 */
	public void deleteAnswer(Answer answer) {
		answerList.remove(answer);
	}
}
