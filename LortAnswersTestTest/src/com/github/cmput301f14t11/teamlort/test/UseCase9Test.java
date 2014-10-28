package com.github.cmput301f14t11.teamlort.test;

import junit.framework.TestCase;


public class UseCase9Test extends ActivityInstrumentationTestCase2 
{  
	public void testSort()
	{
		public static final int CONSTANT_IMAGE = 1;
		String title = "testing";
		ArrayList<Question> questionlist;
		for (int i =0; x<=9; x++)
		{
			if(i%2 == 0)
			{
				Bitmap bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888 ); 
 				Question question = new Question(title); 
 				question.addImage(bitmap); 
			}
			pdm.add(singlequestion);//persistent data manager
		}

  	questionlist.sort(CONSTANT_IMAGE);

		for(int i=1;i<questionlist.size()-1;i++)
		{
			if(questionlist.get(i-1).getpic()!=questionlist.get(i).getpic()&&questionlist.get(i).getpic()!=questionlist.get(i+1).getpic())
			{
				fail("list not properly sorted by image");
			}
			
		}); 
	}
}
