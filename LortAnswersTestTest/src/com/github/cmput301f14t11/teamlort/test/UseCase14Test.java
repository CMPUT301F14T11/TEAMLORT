package com.github.cmput301f14t11.teamlort.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.github.cmput301f14t11.teamlort.QuestionViewActivity;
import com.github.cmput301f14t11.teamlort.Controller.Qlistcontroller;
import com.github.cmput301f14t11.teamlort.Model.Answer;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.Question;

public class UseCase14Test extends ActivityInstrumentationTestCase2<QuestionViewActivity>{
	
	
		public UseCase14Test(){
			super(QuestionViewActivity.class);
		}
	
	//Display the number of Answers for each question
		public void testCase14(){
			Qlistcontroller ql = new Qlistcontroller();
			
			Question question1 = ObjectFactory.initQuestion("T1", "B1", "A1");
			Question question2 = ObjectFactory.initQuestion("T2", "B2", "A2");
			Question question3 = ObjectFactory.initQuestion("T3", "B3", "A3");
			Answer answer1 =  ObjectFactory.initAnswer("B1", "A1");
			Answer answer2 =  ObjectFactory.initAnswer("B1", "A1");
			Answer answer3 =  ObjectFactory.initAnswer("B1", "A1");
			Answer answer4 =  ObjectFactory.initAnswer("B1", "A1");
			
			question1.addAnswer(answer1);
			question2.addAnswer(answer2);
			question1.addAnswer(answer3);
			question1.addAnswer(answer4);
			ql.add(question1);
			ql.add(question2);
			ql.add(question3);

			// And now users should see the number of answers for each question in
			// the question detail screen.
			
			QuestionViewActivity activity = (QuestionViewActivity) getActivity();
			TextView tv = (TextView)activity.findViewById(com.github.cmput301f14t11.teamlort
					.R.id.answer_comment_count_textview);
			
			assertFalse("wrong number of answers for question1",
					question1.getAnswerList().size() != 3);
			assertEquals(tv.getText().toString(), 
					String.valueOf(question1.getAnswerList().size()) );
			
			assertFalse("wrong number of answers for question2",
					question2.getAnswerList().size() != 1);
			assertEquals(tv.getText().toString(), 
					String.valueOf(question2.getAnswerList().size()) );
			
			assertFalse("wrong number of answers for question3",
					question3.getAnswerList().size() != 0);
			assertEquals(tv.getText().toString(), 
					String.valueOf(question3.getAnswerList().size()) );
			
		
		}
		

}
