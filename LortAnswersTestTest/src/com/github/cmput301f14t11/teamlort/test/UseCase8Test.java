package com.github.cmput301f14t11.teamlort.test;

import junit.framework.TestCase;


public class UseCase8Test extends TestCase 
{  
		public void testQUEPIC()  
	 	{  
			String title = "testsize";
	 		Question question = new Question(title); 
	 		Bitmap bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888 ); 
	 		question.addImage(bitmap); 
	 		assertTrue("picture file size too large",question.getpic().getAllocationByteCount () <= 64);  
	 	}  

}