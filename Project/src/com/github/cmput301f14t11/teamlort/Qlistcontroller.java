package com.github.cmput301f14t11.teamlort;
import java.net.URI;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.github.cmput301f14t11.teamlort.Question;


import android.graphics.drawable.Drawable;
public class Qlistcontroller implements Observer{
	
	ElasticManager elc = ElasticManager.getInstance();
	QuestionList questionlist = new QuestionList();

	public void getMore()
	{
		// TODO: Research ElasticSearch
		ElasticManager.serverQuery("Foo!");
		
	}

	public void add(Question t) {
		// TODO Auto-generated method stub
		questionlist.addquestion(t);
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}
	public void addfavorite(Question t)
	{
		
	}
	public void addsave(Question t)
	{
		
	}
	
	
}
