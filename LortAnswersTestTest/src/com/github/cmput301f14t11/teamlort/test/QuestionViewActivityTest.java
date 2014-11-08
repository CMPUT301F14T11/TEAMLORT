package com.github.cmput301f14t11.teamlort.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.cmput301f14t11.teamlort.Answer;
import com.github.cmput301f14t11.teamlort.AnswerAdapter;
import com.github.cmput301f14t11.teamlort.AppCache;
import com.github.cmput301f14t11.teamlort.Question;
import com.github.cmput301f14t11.teamlort.QuestionViewActivity;
import com.github.cmput301f14t11.teamlort.R;
import com.github.cmput301f14t11.teamlort.Reply;
import com.github.cmput301f14t11.teamlort.ReplyAdapter;

public class QuestionViewActivityTest extends ActivityInstrumentationTestCase2<QuestionViewActivity>{

	public QuestionViewActivityTest() {
		super(QuestionViewActivity.class);
	}

	public void testTextDisplay() throws Throwable{
		//TODO Still have yet to get single tests to run by themselves, so this probably doesn't work. - Elvis
		//TODO possibly split this test into multiple tests.
		//TODO This is missing testing for author text.
		
		Question question = new Question();
		Reply reply = new Reply();
		reply.setBody("test reply");
		reply.setAuthor("test reply author");
		Answer answer = new Answer();
		answer.setBody("answer test");
		answer.setAuthor("asdsadas");
		answer.addReply(reply);
		question.addAnswer(answer);
		question.addAnswer(answer);
		question.addAnswer(answer);
		question.addAnswer(answer);
		question.addAnswer(answer);
		Reply reply2 = new Reply();
		reply2.setBody("test reply to question");
		reply2.setAuthor("test reply author");
		question.addReply(reply2);
		
		AppCache appCache = AppCache.getInstance();
		appCache.setQuestion(question);
		
		runTestOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				QuestionViewActivity activity = (QuestionViewActivity) getActivity();
				
				TextView questionTitleTextView = (TextView) activity.findViewById(R.id.UsernameTitleTextView);
				questionTitleTextView.setText("testing title");
				
				TextView questionBodyTextView = (TextView) activity.findViewById(R.id.QuestionBodyTextView);
				questionBodyTextView.setText("testing body");
				
				TextView questionTimeTextView = (TextView) activity.findViewById(R.id.QuestionTimeTextView);
				questionTimeTextView.setText("testing time");
				
				ReplyAdapter replyAdapter = activity.getReplyAdapter();
				
				assertTrue("First item in replyAdapter not a Reply", replyAdapter.getItem(0) instanceof Reply);
				assertTrue("replyAdapter first item body not test reply to question", "test reply to question" ==
							((Reply) replyAdapter.getItem(0)).getBody());
				
				AnswerAdapter answerAdapter = activity.getAnswerAdapter();
				
				assertTrue("First item in answerAdapter not an Answer", answerAdapter.getGroup(0) instanceof Answer);
				assertTrue("answerAdapter first item body not answer test", "answer test" ==
							((Answer) answerAdapter.getGroup(0)).getBody());
				assertTrue("First item in answer group not an Reply", answerAdapter.getChild(0, 0) instanceof Reply);
				assertTrue("answerAdapter first reply item body not test reply", "test reply" ==
						((Reply) answerAdapter.getChild(0,0)).getBody());
				
			}
		});
		
		QuestionViewActivity activity = (QuestionViewActivity) getActivity();
		
		TextView questionTitleTextView = (TextView) activity.findViewById(R.id.UsernameTitleTextView);
		assertEquals("testing title", questionTitleTextView.getText());
		
		TextView questionBodyTextView = (TextView) activity.findViewById(R.id.QuestionBodyTextView);
		assertEquals("testing body", questionBodyTextView.getText());
		
		TextView questionTimeTextView = (TextView) activity.findViewById(R.id.QuestionTimeTextView);
		assertEquals("testing time", questionTimeTextView.getText());
		
	}
	
	public void testTextOnScreen(){
		//TODO this is missing test for author text.
		
		QuestionViewActivity activity = (QuestionViewActivity) getActivity();
		View screen = (View) activity.getWindow().getDecorView();
		View widget = (View) activity.findViewById(com.github.cmput301f14t11.teamlort.R.id.UsernameTitleTextView);
		ViewAsserts.assertOnScreen(screen, widget);
		
		screen = (View) activity.getWindow().getDecorView();
		widget = (View) activity.findViewById(com.github.cmput301f14t11.teamlort.R.id.QuestionBodyTextView);
		ViewAsserts.assertOnScreen(screen, widget);
		
		screen = (View) activity.getWindow().getDecorView();
		widget = (View) activity.findViewById(com.github.cmput301f14t11.teamlort.R.id.QuestionTimeTextView);
		ViewAsserts.assertOnScreen(screen, widget);
		
	}

}