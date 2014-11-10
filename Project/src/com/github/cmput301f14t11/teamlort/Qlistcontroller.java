package com.github.cmput301f14t11.teamlort;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;
import java.util.Observer;

import com.github.cmput301f14t11.teamlort.Question;


import android.graphics.drawable.Drawable;

/**
 * responsible for maintaining the list of questions in the homeactivity
 * 
 * @author sbao
 *
 */
public class Qlistcontroller implements Observer{
	
	ElasticManager elc = ElasticManager.getInstance();
	QuestionList questionlist = new QuestionList();

	/**
	 * pulls more question from the server
	 */
	public void getMore()
	{
		// TODO: Research ElasticSearch
		ElasticManager.serverQuery("Foo!");
		
	}

	/**
	 * adds an new question to the local list
	 * @param t
	 */
	public void add(Question t) {
		// TODO Auto-generated method stub
		questionlist.addquestion(t);
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * return size of the question
	 * @return
	 */
	public int returnsize()
	{
		return questionlist.modellist.size();
	}
	/**
	 * sort question based on command string givien, either by date, by upvote, or by having image or not
	 * @param command
	 */
	public void sortQuestions(String command) {
		// TODO Auto-generated method stub
		if(command=="date")
		{
			Collections.sort(questionlist.modellist, new TimeComparator());
		}
		else if (command =="upvote")
		{
			Collections.sort(questionlist.modellist, new ScoreComparator());
		}
		else if (command =="image")
		{
			Collections.sort(questionlist.modellist, new ImageComparator());
		}
		
	}
	/**
	 * reverse order of the list
	 */
	public void reverselist()
	{
		Collections.reverse(questionlist.modellist);
	}

	public ArrayList<Answer> sortAnswers() {
		// TODO Auto-generated method stub
		
		return null;
	}
	public class TimeComparator implements Comparator<Question> {

		@Override
		public int compare(Question arg0, Question arg1) {
			// TODO Auto-generated method stub
			return arg0.getTime().compareTo(arg1.getTime());
		}
	}
	public class ScoreComparator implements Comparator<Question> {

		@Override
		public int compare(Question arg0, Question arg1) {
			// TODO Auto-generated method stub
			return arg0.getScore() - arg1.getScore();
		}
	}
	public class ImageComparator implements Comparator<Question>
	{

		@Override
		public int compare(Question lhs, Question rhs) {
			// TODO Auto-generated method stub
			if (lhs.getPicture()!= null && rhs.getPicture()!=null)
			{
				return 0;
			}
			else if (lhs.getPicture()== null && rhs.getPicture()!=null)
			{
				return 1;
			}
			else if (lhs.getPicture()!= null && rhs.getPicture()==null)
			{
				return -1;
			}
			else if (lhs.getPicture()== null && rhs.getPicture()==null)
			{
				return 0;
			}
			return 0;
		}
		
	}
	
	
}
