package com.github.cmput301f14t11.teamlort.test;

import android.test.ActivityInstrumentationTestCase2;

import com.github.cmput301f14t11.teamlort.HomeActivity;
import com.github.cmput301f14t11.teamlort.Controller.Qlistcontroller;
import com.github.cmput301f14t11.teamlort.Model.Answer;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.Question;

public class HomeAcitvitytest extends ActivityInstrumentationTestCase2<HomeActivity>{

	public HomeAcitvitytest(Class<HomeActivity> Homeactivity) {
		super(HomeActivity.class);
		//test button press 
		
		// TODO Auto-generated constructor stub
	}
	public void testdisplayquestion() throws Throwable
	{
		
		HomeActivity activity = (HomeActivity) getActivity();
		ObjectFactory dt = new ObjectFactory();
		Qlistcontroller qlc = new Qlistcontroller();

		//HomeAdapter adapter; 
		//adapter = new HomeAdapter(activity, qlc.getQuestionlist().getModellist());

		//HomeAdapter adapter = activity.getAdapter(); 

        for(int i = 0; i<=19; i++)
        {
        	Question t = dt.initQuestion("sam'squestion", "test some more", "sam");
        	t.setID();
        	Answer answer = new Answer();
        	answer.setBody("dsadasd");
        	answer.setAuthor("asdsadas");
  		
        	t.addAnswer(answer);
        	qlc.add(t);
        	//dt.addQuestions(listofquestions);
        }
        runTestOnUiThread(new Runnable() {
			
			@Override
			public void run() 
			{
				
			}
        }
        );
		
	}
	public void testfavorite()
	{
		
	}
	public void testsave()
	{
		
	}
}
