package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.view.View;

public class QuestionList extends Observable {
	ArrayList<Question> modellist;
	ArrayList<Question> temp;
	ArrayList<View> oberservers;
	Context ctx;
	View v;
	ElasticManager elasticmanager;
	/**
	 * initializes the questionlist object
	 */
	public QuestionList()
	{
		modellist = new ArrayList<Question>();
	}
	/**
	 * user must provide proper context so model can change the view correctly
	 * @param provided
	 */
	public void providecontext(Context provided)
	{
		ctx = provided;
	}
	/**
	 * adds a view to the oberverlist
	 * @param view
	 */
	public void addview(View view)
	{
		v = view;
		oberservers.add(v);
	}
	/**
	 * adds a single question to the question list
	 * @param provided
	 */
	public void addquestion(Question provided)
	{
		modellist.add(provided);
	}
	/**
	 * sorts the list of question based on if they have an image or not
	 * @return
	 */
	public ArrayList<Question> sortByImage()
	{
		move();
		return modellist;
	}
	@Override
	public void addObserver(Observer observer) {
		// TODO Auto-generated method stub
		super.addObserver(observer);
	}
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		super.notifyObservers();
	}
	@Override
	public void notifyObservers(Object data) {
		// TODO Auto-generated method stub
		super.notifyObservers(data);
	}
	/**
	 * shift all question with no image to another list,seperating the two types of question
	 */
	public void move()
	{
		for(int i = 0; i < modellist.size(); i ++)
		{
			if(modellist.get(i).getPicture()==null)
			{
				temp.add(modellist.get(i));
				modellist.remove(i);
			}
		}
	}
	
	

}
