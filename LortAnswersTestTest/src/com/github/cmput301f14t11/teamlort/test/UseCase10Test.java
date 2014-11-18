package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;
import java.util.Date;

import com.github.cmput301f14t11.teamlort.HomeActivity;
import com.github.cmput301f14t11.teamlort.Controller.Qlistcontroller;
import com.github.cmput301f14t11.teamlort.Controller.QuestionController;
import com.github.cmput301f14t11.teamlort.Controller.ScoreController;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.PersistentDataManager;
import com.github.cmput301f14t11.teamlort.Model.Question;

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
		Qlistcontroller qc = new Qlistcontroller();
		ObjectFactory obj = new ObjectFactory();
		QuestionController qsc = new QuestionController();
		final int CONSTANT_UPVOTE = 2;
		final int CONSTANT_DATE = 3;
		ArrayList<Question> questionlist;
		for (int i =0; i<=9; i++)
		{

			Question singlequestion = obj.initQuestion("testingtitle", "testing", "tester");
			for(int j = 0; j<i; j++)
			{
				singlequestion.upVote("sadsadsa"+j);
				
			}
			
			
			qc.add(singlequestion);
		}

		qc.sortQuestions("upvote");//it makes no sense to me why user would want to see question with the least amount of upvotes(0) first, so we are sorting this one way
		// modified up to here - oct.28
		
		for(int i=1;i<qc.returnsize();i++)
		{
			if(qc.returnquestion(i-1).getScore()<=qc.returnquestion(i).getScore())
			{
				fail("list not properly sorted by upvote");
			}
			
		}; 
		qc.sortQuestions("date");
		for(int i=1;i<qc.returnsize();i++)
		{
			if(qc.returnquestion(i-1).getTime().compareTo(qc.returnquestion(i).getTime())>1 )//needs to work on date, sorting it by oldest/newest
			{
				fail("list not properly sorted by date");
			}
			
		}; 
		for(int i=1;i<qc.returnsize();i++)
		{
			if(qc.returnquestion(i-1).getTitle() != "testing" && qc.returnquestion(i).getBody() != "testingtitle")//checks keyword
			{
				fail("keyword does not match");
			}
			
		}; 
    		
		//Sort by up votes, dates, images, and?(I'll just get these 3 done for now)
		// we will need a "cheating method" to superficially add upvote count inorder to test sort,this method should be deleted in the release version
	
	}
}
