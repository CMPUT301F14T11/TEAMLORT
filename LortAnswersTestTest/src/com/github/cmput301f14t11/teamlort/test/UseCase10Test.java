package com.github.cmput301f14t11.teamlort.test;

import junit.framework.TestCase;


public class UseCase10Test extends TestCase 
{  
	Date date = new Date();
	

	public void testSort()
	{
		public static final int CONSTANT_UPVOTE = 2;
		public static final int CONSTANT_DATE = 3;
		ArrayList<Question> questionlist;
		for (int i =0; x<=9; x++)
		{
			Question singlequestion = new Question()
			singlequestion.setvote(i);
			singlequestion.date.setDate(date.getDate()+i);
			//addressing feedback - checking for keyword
			singlequestion.body = "testing";
			singlequestion.title = "testingtitle";
			pdm.add(singlequestion);
		}

		questionlist.sort(CONSTANT_UPVOTE);//it makes no sense to me why user would want to see question with the least amount of upvotes(0) first, so we are sorting this one way
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
