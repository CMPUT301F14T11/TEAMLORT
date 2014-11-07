package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.github.cmput301f14t11.teamlort.Answer;
import com.github.cmput301f14t11.teamlort.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Qlistcontroller;
import com.github.cmput301f14t11.teamlort.Question;
import com.github.cmput301f14t11.teamlort.QuestionViewActivity;

public class UseCase14Test extends ActivityInstrumentationTestCase2<QuestionViewActivity>{
	
	
		public UseCase14Test(){
			super(QuestionViewActivity.class);
		}
	
	//Display the number of Answers for each question
		public void testCase14(){
			ObjectFactory obf = new ObjectFactory();
			ArrayList<Question> temp = new ArrayList<Question>();
			Qlistcontroller ql = new Qlistcontroller();
			
			Question question1 = obf.initQuestion("T1", "B1", "A1");
			Question question2 = obf.initQuestion("T2", "B2", "A2");
			Question question3 = obf.initQuestion("T3", "B3", "A3");
			Answer answer1 =  obf.initAnswer("B1", "A1");
			Answer answer2 =  obf.initAnswer("B1", "A1");
			Answer answer3 =  obf.initAnswer("B1", "A1");
			Answer answer4 =  obf.initAnswer("B1", "A1");
			
			question1.addAnswer(answer1);
			question2.addAnswer(answer2);
			question1.addAnswer(answer3);
			question1.addAnswer(answer4);
			ql.add(question1,temp);
			ql.add(question2,temp);
			ql.add(question3,temp);

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
