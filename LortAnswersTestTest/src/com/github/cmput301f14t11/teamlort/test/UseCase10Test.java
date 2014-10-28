package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;
import java.util.Date;

import com.github.cmput301f14t11.teamlort.HomeActivity;
import com.github.cmput301f14t11.teamlort.Question;
import com.github.cmput301f14t11.teamlort.ScoreController;
import com.github.cmput301f14t11.teamlort.Model.PersistentDataManager;

import android.test.ActivityInstrumentationTestCase2;
import junit.framework.TestCase;


public class UseCase10Test extends ActivityInstrumentationTestCase2<HomeActivity> 
{  
	public UseCase10Test(Class<HomeActivity> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}


	Date date = new Date();
	

	public void testSort()
	{
		PersistentDataManager pdm = PersistentDataManager.getInstance();
		ScoreController sc = new ScoreController();
		final int CONSTANT_UPVOTE = 2;
		final int CONSTANT_DATE = 3;
		ArrayList<Question> questionlist;
		for (int i =0; i<=9; i++)
		{
			Question singlequestion = new Question();
			sc.setvote(singlequestion,i);
			singlequestion.date.setDate(Date.getDate()+i);
			//addressing feedback - checking for keyword
			singlequestion.body = "testing";
			singlequestion.title = "testingtitle";
			questionlist.add(singlequestion);
		}

		pdm.sortQuestions(CONSTANT_UPVOTE);//it makes no sense to me why user would want to see question with the least amount of upvotes(0) first, so we are sorting this one way
		// modified up to here - oct.28
		
		for(int i=1;i<questionlist.size();i++)
		{
			if(questionlist.get(i-1).upvote<=questionlist.get(i).upvote)
			{
				fail("list not properly sorted by upvote");
			}
			
		}); 
		questionlist.sort(CONSTANT_DATE);
		for(int i=1;i<questionlist.size();i++)
		{
			if(questionlist.get(i-1).date<=questionlist.get(i).date)//needs to work on date, sorting it by oldest/newest
			{
				fail("list not properly sorted by date");
			}
			
		}); 
		for(int i=1;i<questionlist.size();i++)
		{
			if(questionlist.get(i).body != "testing" && questionlist.get(i).title != "testingtitle";)//checks keyword
			{
				fail("keyword does not match");
			}
			
		}); 
    		
		//Sort by up votes, dates, images, and?(I'll just get these 3 done for now)
		// we will need a "cheating method" to superficially add upvote count inorder to test sort,this method should be deleted in the release version
	
	}
}
