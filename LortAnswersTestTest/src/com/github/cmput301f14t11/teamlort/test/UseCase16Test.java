package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;

import com.github.cmput301f14t11.teamlort.LocalManager;
import com.github.cmput301f14t11.teamlort.ObjectFactory;
import com.github.cmput301f14t11.teamlort.ProfileActivity;
import com.github.cmput301f14t11.teamlort.Question;

public class UseCase16Test extends ActivityInstrumentationTestCase2<ProfileActivity>{
	
		public UseCase16Test(){
			super(ProfileActivity.class);
		}
	
	//Recording Authors’ Questions
		public void testCase16(){
			LocalManager lm = new LocalManager();
			ObjectFactory obf = new ObjectFactory();
			ArrayList<Question> authorQuestionList = new ArrayList<Question>();
			
			authorQuestionList = lm.loadQuestions();
			// author has not started a question yet. authorQuestionList should be empty
			assertTrue("author list is not empty", authorQuestionList.size() == 0);
			
			Question question1 = obf.initQuestion("T1", "B1", "A1");
			Question question2 = obf.initQuestion("T2", "B2", "A2");
			lm.saveQuestion(question1);
			lm.saveQuestion(question2);
			//when user exit the program or logout and login later, the question should
			//still traceable.
			authorQuestionList = lm.loadQuestions();
			
			assertTrue("Only one question should stored in the user cache list",
					authorQuestionList.size() == 1);
			assertTrue("Only question1 should in the cache list",
					authorQuestionList.contains(question1));
			
			
		}

}
