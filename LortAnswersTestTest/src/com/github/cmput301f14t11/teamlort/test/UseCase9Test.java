package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import com.github.cmput301f14t11.teamlort.HomeActivity;
import com.github.cmput301f14t11.teamlort.Controller.Qlistcontroller;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.Question;

import android.R.drawable;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.test.ActivityInstrumentationTestCase2;
import junit.framework.TestCase;


public class UseCase9Test extends ActivityInstrumentationTestCase2<HomeActivity> 
{  
	public UseCase9Test(Class<HomeActivity> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}

	public void testSort()
	{
		ObjectFactory obj = new ObjectFactory();
		Qlistcontroller qc = new Qlistcontroller();
		for (int i =0; i<=9; i++)
		{
			if(i%2 == 0)
			{
				Bitmap bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888 );
	
				Drawable drawable = new BitmapDrawable(bitmap);
				
				
	

 				Question singlequestion = obj.initQuestion("testing", "body", "author", drawable);
 				qc.add(singlequestion);
			}
			;
		}

  	qc.sortQuestions("image", null);
  	

		for(int i=1;i<qc.returnsize()-1;i++)
		{
			if(qc.returnquestion(i-1).getPicture()!=qc.returnquestion(i-1).getPicture()&&qc.returnquestion(i).getPicture()!=qc.returnquestion(i).getPicture())
			{
				fail("list not properly sorted by image");
			}
			
		}
	}
}
