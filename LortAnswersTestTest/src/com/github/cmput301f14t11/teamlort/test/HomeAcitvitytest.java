package com.github.cmput301f14t11.teamlort.test;

import com.github.cmput301f14t11.teamlort.HomeActivity;
import com.github.cmput301f14t11.teamlort.ProfileActivity;
import com.github.cmput301f14t11.teamlort.Controller.Qlistcontroller;
import com.github.cmput301f14t11.teamlort.Model.Answer;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.Question;
import com.github.cmput301f14t11.teamlort.HomeAdapter;

import android.test.ActivityInstrumentationTestCase2;

public class HomeAcitvitytest extends ActivityInstrumentationTestCase2<HomeActivity>{

	public HomeAcitvitytest(Class<HomeActivity> Homeactivity) {
		super(HomeActivity.class);
		//test button press 
		
		// TODO Auto-generated constructor stub
	}
	public void testdisplayquestion()
	{
		
		HomeActivity activity = (HomeActivity) getActivity();
		ObjectFactory dt = new ObjectFactory();
		Qlistcontroller qlc = new Qlistcontroller();
		HomeAdapter adapter; 
		adapter = new HomeAdapter(activity, qlc.getQuestionlist().getModellist());
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
		
	}
	public void testfavorite()
	{
		
	}
	public void testsave()
	{
		
	}
}
