package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * {@link Activity} for viewing a {@link Question} and it's {@link Answer}s and {@link Reply}s, 
 * user can answer the {@link Question} or {@link Reply} to the question or an answer as 
 * well as save/favorite the {@link Question}. Covers use cases 2, 3, 5, 6, 11, 12, 22
 * and involved in cases 7, 13, 18.
 * 
 * @author Elvis Lo
 * 
 * @issues Currently missing a button for entering replies and missing an author textfield in header.
 */
public class QuestionViewActivity
extends AppBaseActivity
{
	
	Question question;
	ArrayList<Answer> answerList;
	ArrayList<Reply> questionReplyList;
	ObjectFactory dt = new ObjectFactory();
	ReplyAdapter replyAdapter;
	AnswerAdapter answerAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_view);
		
		//Set question title and description on GUI.
		//question = pdm.get(questionID);
		//TODO These are just temporary test question/answers/replies
		//question = dt.initQuestion("testing", "testing", "sdfasfds");
		AppCache appCache = AppCache.getInstance();
		question = appCache.getQuestion();
		final String username = appCache.getProfile().getUsername();
		
		answerList = question.getAnswerList();
		questionReplyList = question.getReplyList();
		
		final ProfileController pc = new ProfileController();
		Profile profile = new Profile();
		//pc.setProfile(profile);
				
		ExpandableListView answerListView = (ExpandableListView) findViewById(R.id.answer_list_view);
		LayoutInflater layoutInflater = getLayoutInflater();
		
		OnClickListener onClickListener = new OnClickListener(){

			@Override
			public void onClick(View v) {
				// Adds to favorite List
				if(v.getId() == R.id.favorite_button)
				{
					pc.addFavedQuestion(question);
					finish();
				}
				else if(v.getId() == R.id.save_button);
				{
					//cache
					finish();
				}
			}
			
		};
		
		//Setup the header
		ViewGroup header = (ViewGroup) layoutInflater.inflate(R.layout.question_view_header, answerListView, false);
		
		final ListView QuestionReplyListView = (ListView) header.findViewById(R.id.question_reply_listview);
		
		TextView questionTitleTextView = (TextView) header.findViewById(R.id.UsernameTitleTextView);
		questionTitleTextView.setText(question.getTitle());
		TextView questionBodyTextView = (TextView) header.findViewById(R.id.QuestionBodyTextView);
		questionBodyTextView.setText(question.getBody());
		TextView questionTimeTextView = (TextView) header.findViewById(R.id.QuestionTimeTextView);
		questionTimeTextView.setText(question.getTime().toString());
		ImageButton replyButton = (ImageButton) header.findViewById(R.id.reply_button);
		
		replyAdapter = new ReplyAdapter(questionReplyList, this);
		QuestionReplyListView.setAdapter(replyAdapter);
		
		//By default the reply listview for question should be collapsed
		QuestionReplyListView.setVisibility(View.GONE);
		
		//Add the header top top of listview
		answerListView.addHeaderView(header, null, false);
		
		final AnswerAdapter answerAdapter = new AnswerAdapter(answerList, this);
		answerListView.setAdapter(answerAdapter);		

		final TextView questionCommentIndicator = (TextView) header.findViewById(R.id.commentIndicatorTextView);
		
		//Set the initial question comment indicator.
		questionCommentIndicator.setText("[ + ] " + QuestionReplyListView.getCount() + " comments");
		
		header.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (QuestionReplyListView.getVisibility() == View.GONE){
					questionCommentIndicator.setText("[ - ] " + QuestionReplyListView.getCount() + " comments");
					QuestionReplyListView.setVisibility(View.VISIBLE);
				} else {
					questionCommentIndicator.setText("[ + ] " + QuestionReplyListView.getCount() + " comments");
					QuestionReplyListView.setVisibility(View.GONE);
				}
				
			}
		}  );
		/*
		 * Listens to the question reply button
		 * When the user clicks the reply button this method
		 * takes whatever is in the answerEditText field and 
		 * makes a new answer with the user's username and 
		 * current date. It also makes sure the textfield is not
		 * before posting a new answer.
		 * */
		replyButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView answerText = (TextView) findViewById(R.id.answerEditText);
				if (answerText.getText().length() == 0) {
					Toast.makeText(getBaseContext(), "No empty questions", Toast.LENGTH_SHORT).show();
					return;
				}
				Answer answer = new Answer();
				answer.setBody(answerText.getText().toString());
				answer.setAuthor(username);
				question.addAnswer(answer); 
				answerText.setText("");
				answerAdapter.notifyDataSetChanged();
				//Toast.makeText(getBaseContext(), "Added Question", Toast.LENGTH_SHORT).show();
			}
		});

	}
	
	public ReplyAdapter getReplyAdapter(){
		return replyAdapter;
	}
	
	public AnswerAdapter getAnswerAdapter(){
		return answerAdapter;
	}
	
}
