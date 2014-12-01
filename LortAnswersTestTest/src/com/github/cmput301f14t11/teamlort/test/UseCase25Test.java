package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Toast;

import com.github.cmput301f14t11.teamlort.HomeActivity;
import com.github.cmput301f14t11.teamlort.Controller.Qlistcontroller;
import com.github.cmput301f14t11.teamlort.Controller.Qlistcontroller.Locationcomparator;
import com.github.cmput301f14t11.teamlort.Model.GpsLocation;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.Question;

/**
 * Use Case 25: 
 * Test whether the user can query posts near them (aka if sort by location works)
 */
public class UseCase25Test extends ActivityInstrumentationTestCase2<HomeActivity> implements LocationListener
{  
	public UseCase25Test(Class<HomeActivity> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}

	double latitude = 53.524912;
	double longitude = -113.5343;
			
	Context context;
	LocationManager locationManager = (LocationManager) context.getApplicationContext().getSystemService(context.LOCATION_SERVICE);
	GpsLocation location = new GpsLocation(latitude, longitude);
	
	

	public void testSort()
	{
		Qlistcontroller qc = new Qlistcontroller();
		ObjectFactory obj = new ObjectFactory();
		ArrayList<Question> questionlist;
		for (int i =0; i<=9; i++)
		{

			Question singlequestion = obj.initQuestion("testingtitle", "testing", "tester", location);
			for(int j = 0; j<i; j++)
			{
				singlequestion.upVote("sadsadsa"+j);
				
			}
			
			
			qc.add(singlequestion);
		}

		
		
		for(int i=1;i<qc.returnsize();i++)
		{
			if(qc.returnquestion(i-1).getScore()<=qc.returnquestion(i).getScore())
			{
				fail("list not properly sorted by upvote");
			}
			
		}; 
		qc.sortQuestions("location",locationManager);
		Locationcomparator lc = qc.new Locationcomparator();
		for(int i=1;i<qc.returnsize();i++)
		{
			if(lc.compare(qc.returnquestion(i-1), qc.returnquestion(i))>1 )//needs to work on date, sorting it by oldest/newest
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



	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onProviderEnabled(String provider) {
		

	}

	@Override
	public void onProviderDisabled(String provider) {
		
	}
}
