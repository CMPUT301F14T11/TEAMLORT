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
	public QuestionList()
	{
		modellist = new ArrayList<Question>();
	}
	public void providecontext(Context provided)
	{
		ctx = provided;
	}
	public void addview(View view)
	{
		v = view;
		oberservers.add(v);
	}
	public void addquestion(Question provided)
	{
		modellist.add(provided);
	}
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
