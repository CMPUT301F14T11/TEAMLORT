package com.github.cmput301f14t11.teamlort;
import java.net.URI;
import java.util.ArrayList;

import com.github.cmput301f14t11.teamlort.Question;


import android.graphics.drawable.Drawable;
public class Qlistcontroller {

	public void getMore()
	{
		// TODO: Research ElasticSearch
		elasticManager.serverQuery("Foo!");
	}

	public void add(Question t, ArrayList<Question> listofquestions) {
		// TODO Auto-generated method stub
		listofquestions.add(t);
	}
	
	
}
