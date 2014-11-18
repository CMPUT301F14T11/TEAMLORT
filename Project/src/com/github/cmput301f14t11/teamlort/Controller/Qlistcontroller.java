package com.github.cmput301f14t11.teamlort.Controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;
import java.util.Observer;

import com.github.cmput301f14t11.teamlort.Model.Answer;
import com.github.cmput301f14t11.teamlort.Model.ElasticManager;
import com.github.cmput301f14t11.teamlort.Model.Question;
import com.github.cmput301f14t11.teamlort.Model.QuestionList;

/**
 * responsible for maintaining the list of questions in the homeactivity
 * 
 * @author sbao
 *
 */
public class Qlistcontroller implements Observer{
	
	private ElasticManager elc = ElasticManager.getInstance();
	private QuestionList questionlist = new QuestionList();

	/**
	 * pulls more question from the server
	 */
	public ArrayList<Question> getMore(int providedid,int amount)
	{
		// TODO: how to trigger getmroe
		getQuestionlist().getModellist().addAll(getElc().get(providedid, amount));
		return getElc().get(providedid, amount);
		// updateview
	}

	/**
	 * adds an new question to the local list
	 * @param t
	 */
	public void add(Question t) {
		// TODO Auto-generated method stub
		getQuestionlist().addquestion(t);
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
		return getQuestionlist().getModellist().size();
	}
	/**
	 * retrieves the question from the questionlist
	 * @param index
	 * @return
	 */
	public Question returnquestion(int index)
	{
		return getQuestionlist().getModellist().get(index);
	}
	/**
	 * sort question based on command string givien, either by date, by upvote, or by having image or not
	 * @param command
	 */
	public void sortQuestions(String command) {
		// TODO Auto-generated method stub
		if(command=="date")
		{
			Collections.sort(getQuestionlist().getModellist(), new TimeComparator());
		}
		else if (command =="upvote")
		{
			Collections.sort(getQuestionlist().getModellist(), new ScoreComparator());
		}
		else if (command =="image")
		{
			Collections.sort(getQuestionlist().getModellist(), new ImageComparator());
		}
		
	}
	/**
	 * reverse order of the list
	 */
	public void reverselist()
	{
		Collections.reverse(getQuestionlist().getModellist());
	}
	/**
	 * sorts an array list of answer based on the command given 
	 * @param provided
	 * @param command
	 * @return provided
	 */
	public ArrayList<Answer> sortAnswers(ArrayList<Answer> provided,String command) {
		// TODO Auto-generated method stub
		
		if(command=="date")
		{
			Collections.sort(provided, new TimeComparator_answer());
		}
		else if (command =="upvote")
		{
			Collections.sort(provided, new ScoreComparator_answer());
		}
		else if (command =="image")
		{
			Collections.sort(provided, new ImageComparator_answer());
		}
		return provided;
		
	}
	public QuestionList getQuestionlist() {
		return questionlist;
	}

	public void setQuestionlist(QuestionList questionlist) {
		this.questionlist = questionlist;
	}
	public ElasticManager getElc() {
		return elc;
	}

	public void setElc(ElasticManager elc) {
		this.elc = elc;
	}
	public class TimeComparator_answer implements Comparator<Answer>
	{

		@Override
		public int compare(Answer lhs, Answer rhs) {
			// TODO Auto-generated method stub
			return lhs.getTime().compareTo(rhs.getTime());
		}
		
	}
	public class ScoreComparator_answer implements Comparator<Answer> {

		@Override
		public int compare(Answer arg0, Answer arg1) {
			// TODO Auto-generated method stub
			return arg0.getScore() - arg1.getScore();
		}
	}
	public class ImageComparator_answer implements Comparator<Answer>
	{

		@Override
		public int compare(Answer lhs, Answer rhs) {
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
