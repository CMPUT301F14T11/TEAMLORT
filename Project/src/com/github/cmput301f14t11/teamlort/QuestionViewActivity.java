package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;

import com.github.cmput301f14t11.teamlort.Controller.ProfileController;
import com.github.cmput301f14t11.teamlort.Model.Answer;
import com.github.cmput301f14t11.teamlort.Model.AppCache;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.Profile;
import com.github.cmput301f14t11.teamlort.Model.PushQueue;
import com.github.cmput301f14t11.teamlort.Model.Question;
import com.github.cmput301f14t11.teamlort.Model.Reply;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
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
 * 
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
		final AppCache appCache = AppCache.getInstance();
		question = appCache.getQuestion();
		
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
					pc.addSavedQuestion(question);
					finish();
				}
			}
			
		};
		
		//Setup the header
		ViewGroup header = (ViewGroup) layoutInflater.inflate(R.layout.question_view_header, answerListView, false);
		
		final ListView QuestionReplyListView = (ListView) header.findViewById(R.id.question_reply_listview);
		TextView author = (TextView)header.findViewById(R.id.question_author);
		author.setText("Author: "+question.getAuthor());
		
		TextView questionTitleTextView = (TextView) header.findViewById(R.id.UsernameTitleTextView);
		questionTitleTextView.setText(question.getTitle());
		TextView questionBodyTextView = (TextView) header.findViewById(R.id.QuestionBodyTextView);
		questionBodyTextView.setText(question.getBody());
		TextView questionTimeTextView = (TextView) header.findViewById(R.id.QuestionTimeTextView);
		questionTimeTextView.setText(question.getTime().toString());
		ImageButton replyButton = (ImageButton) header.findViewById(R.id.reply_button);
		Button postAnswerButton = (Button) header.findViewById(R.id.post_answer_button);
		final Button upVoteButton = (Button) header.findViewById(R.id.questionUpvoteButton);
		
		upVoteButton.setBackgroundColor(Color.GRAY);
		upVoteButton.setText(String.valueOf(question.getScore()));
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
		 * Listens to the post answer button
		 * When the user clicks the reply button this method
		 * takes whatever is in the answerEditText field and 
		 * makes a new answer with the user's username and 
		 * current date. It also makes sure the textfield is not
		 * before posting a new answer.
		 * */
		postAnswerButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView answerText = (TextView) findViewById(R.id.answerEditText);
				if (answerText.getText().length() == 0) {
					Toast.makeText(getBaseContext(), "No empty questions", Toast.LENGTH_SHORT).show();
					return;
				}
				
				Answer answer = ObjectFactory.initAnswer(answerText.getText().toString(), appCache.getProfile().getUsername());
				//answer.setBody(answerText.getText().toString());
				//answer.setAuthor(appCache.getProfile().getUsername());
				question.addAnswer(answer); 
				answerText.setText("");
				answerAdapter.notifyDataSetChanged();
				//
					Log.v("IMPORTANT", "" + question.getID());
					Log.v("IMPORTANT", "" + answer.getID());
					Log.v("IMPORTANT", "" + getApplicationContext().toString());
					PushQueue.getInstance().pushAnswer(question.getID(), answer, getApplicationContext());
				//
				
				//Toast.makeText(getBaseContext(), "Added Question", Toast.LENGTH_SHORT).show();
			}
		});
		replyButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Build a dialogue for entering a new reply.
				AlertDialog.Builder alertDialogueBuilder = new AlertDialog.Builder(QuestionViewActivity.this);
				alertDialogueBuilder.setCancelable(true);
				alertDialogueBuilder.setTitle("Enter a reply:");
				
				//Text view for user to enter reply into
				final EditText body = new EditText(QuestionViewActivity.this);
				alertDialogueBuilder.setView(body);
				
				alertDialogueBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						Reply reply = ObjectFactory.initReply(body.getText().toString(), appCache.getProfile().getUsername());
						question.addReplyToStart(reply);
						setListViewHeightBasedOnChildren(QuestionReplyListView);
						replyAdapter.notifyDataSetChanged();
						//Refresh the comment counter.
						if (QuestionReplyListView.getVisibility() == View.GONE){
							questionCommentIndicator.setText("[ + ] " + QuestionReplyListView.getCount() + " comments");
						} else {
							questionCommentIndicator.setText("[ - ] " + QuestionReplyListView.getCount() + " comments");
						}
					}
				});
				alertDialogueBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface arg0, int arg1) {

					}	
					
				});
				
				alertDialogueBuilder.show();
				
			}
		});
		upVoteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(question.getVoterSet().contains(question.getAuthor())){
					question.unVote(question.getAuthor());
					upVoteButton.setText(String.valueOf(question.getScore()));
					upVoteButton.setBackgroundColor(Color.GRAY);
				}
				else {
					question.upVote(question.getAuthor());
					upVoteButton.setText(String.valueOf(question.getScore()));
					upVoteButton.setBackgroundColor(Color.GREEN);
				}
			}
		});

	}
	
	public ReplyAdapter getReplyAdapter(){
		return replyAdapter;
	}
	
	public AnswerAdapter getAnswerAdapter(){
		return answerAdapter;
	}
	
	/**
	 * Used for getting listviews that are inside of another scrolling view to be the correct height after changes.
	 * 
	 * @Credit to DougW at:
	 * http://stackoverflow.com/questions/3495890/how-can-i-put-a-listview-into-a-scrollview-without-it-collapsing
	 * 
	 * @param listView
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter(); 
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight = totalHeight + listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(layoutParams);
        listView.requestLayout();
    }
	
}
