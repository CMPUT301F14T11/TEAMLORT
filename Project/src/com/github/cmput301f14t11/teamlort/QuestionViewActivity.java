package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.cmput301f14t11.teamlort.Controller.ProfileController;
import com.github.cmput301f14t11.teamlort.Controller.QuestionController;
import com.github.cmput301f14t11.teamlort.Model.Answer;
import com.github.cmput301f14t11.teamlort.Model.AppCache;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.Profile;
import com.github.cmput301f14t11.teamlort.Model.PushQueue;
import com.github.cmput301f14t11.teamlort.Model.Question;
import com.github.cmput301f14t11.teamlort.Model.Reply;

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
extends AppBaseActivity implements LocationListener
{
	
	Question question;
	ArrayList<Answer> answerList;
	ArrayList<Reply> questionReplyList;
	ObjectFactory dt = new ObjectFactory();
	ReplyAdapter replyAdapter;
	AnswerAdapter answerAdapter;
	LocationManager locationManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_view);
		
		final AppCache appCache = AppCache.getInstance();
		final String username = appCache.getProfile().getUsername();
		question = appCache.getQuestion();
		
		answerList = question.getAnswerList();
		questionReplyList = question.getReplyList();
		
		final QuestionController questionController = new QuestionController(getApplicationContext());
		questionController.setQuestion(question);
		
		locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		
		
		final ProfileController profileController = new ProfileController();
				
		ExpandableListView answerListView = (ExpandableListView) findViewById(R.id.answer_list_view);
		LayoutInflater layoutInflater = getLayoutInflater();
		
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
		final ImageButton saveButton = (ImageButton) header.findViewById(R.id.save_button);
		final ImageButton favoriteButton = (ImageButton) header.findViewById(R.id.favorite_button);
		Button viewImageButton = (Button) header.findViewById(R.id.viewImageButton);
		
		Collections.sort(answerList, new AnswerComparator());
		
		if(profileController.getProfile().containsFavedQuestion(question.getID())){
			favoriteButton.setBackgroundColor(Color.GREEN);
		}
		if(profileController.getProfile().containsSavedQuestion(question.getID())){
			saveButton.setBackgroundColor(Color.GREEN);
		}
		
		if(question.getVoterSet().contains(username)){
			upVoteButton.setBackgroundColor(Color.GREEN);
		}
		else {
			upVoteButton.setBackgroundColor(Color.GRAY);
		}
		upVoteButton.setText(String.valueOf(question.getScore()));
			
		upVoteButton.setText(String.valueOf(question.getScore()));
		replyAdapter = new ReplyAdapter(questionReplyList, this);
		QuestionReplyListView.setAdapter(replyAdapter);
		
		//By default the reply listview for question should be collapsed
		QuestionReplyListView.setVisibility(View.GONE);
		setListViewHeightBasedOnChildren(QuestionReplyListView);
		
		//Only show button for showing images if image exists.
		if(!question.hasPicture()){
			viewImageButton.setVisibility(View.GONE);
		}
		
		//Add the header top top of listview
		answerListView.addHeaderView(header, null, false);
		
		final AnswerAdapter answerAdapter = new AnswerAdapter(answerList, this, question);
		answerListView.setAdapter(answerAdapter);		

		final TextView questionCommentIndicator = (TextView) header.findViewById(R.id.commentIndicatorTextView);
		
		//Set the initial question comment indicator.
		questionCommentIndicator.setText("[ + ] " + QuestionReplyListView.getCount() + " comments");
		
		//Set model listeners
		question.setListener(new Listener(){

			@Override
			public void update() {
				replyAdapter.notifyDataSetChanged();	
				if(question.getVoterSet().contains(username)){
					upVoteButton.setBackgroundColor(Color.GREEN);
				}
				else {
					upVoteButton.setBackgroundColor(Color.GRAY);
				}
				upVoteButton.setText(String.valueOf(question.getScore()));
				setListViewHeightBasedOnChildren(QuestionReplyListView);
			}
			
		});
		
		for (Answer answer : answerList){
		
			answer.setListener(new Listener(){

				@Override
				public void update() {
					answerAdapter.notifyDataSetChanged();				
				}
			
			});
		
		}
		
		//Set view listeners
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
		viewImageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder alert = buildPicture();
				alertDialog = alert.show();
			}
			
		});
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
				TextView answerText = (TextView) findViewById(R.id.answerEditText);
				Answer answer;
				Profile prof = appCache.getProfile();
				
				if (answerText.getText().length() == 0) {
					Toast.makeText(getBaseContext(), "No empty questions", Toast.LENGTH_SHORT).show();
					return;
				}
				
				if (prof.getLocationService()) {
					answer = ObjectFactory.initAnswer(answerText.getText().toString(), prof.getUsername(), prof.getLocation(locationManager));
				} else {
					answer = ObjectFactory.initAnswer(answerText.getText().toString(), appCache.getProfile().getUsername());
				}

				questionController.addAnswer(answer); 
				answerText.setText("");
				
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
						questionController.addQuestionReply(reply);
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
				if(question.getVoterSet().contains(username)){
					questionController.unVoteQuestion(username);
				}
				else {
					questionController.upVoteQuestion(username);
				}
				PushQueue.getInstance().pushQuestion(question,getApplicationContext());

			}
		});
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Adds to saved List
				if(appCache.getProfile().containsSavedQuestion(question.getID())){
					profileController.removeSavedQuestion(question);
					saveButton.setBackgroundResource(R.drawable.ic_action_favorite);
				} else {
					profileController.addSavedQuestion(question);
					saveButton.setBackgroundColor(Color.GREEN);
				}
			}
			
		});
		favoriteButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Adds to favorite List
				if(appCache.getProfile().containsFavedQuestion(question.getID())){
					profileController.removeFavedQuestion(question);
					favoriteButton.setBackgroundResource(R.drawable.ic_action_favorite);					
				} else{
					profileController.addFavedQuestion(question);
					favoriteButton.setBackgroundColor(Color.GREEN);
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
	protected AlertDialog.Builder buildPicture() {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		
		
		
		alert.setTitle("Picture");
		ImageView imageView = new ImageView(getApplicationContext());
		imageView.setImageDrawable(question.getPicture());
		alert.setView(imageView);	
		alert.setPositiveButton("Got it", new DialogInterface.OnClickListener() 
		{
		
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				
			}
		});
		return alert;
	}
	/**
	 * Used for getting a listview that is inside of another scrolling view to be the correct height after changes.
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
	//custom sorting method for answer scores
	public class AnswerComparator implements Comparator<Answer> {

		@Override
		public int compare(Answer b, Answer a) {
			// TODO Auto-generated method stub
			return a.getScore() - b.getScore();
		}
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
		Toast.makeText(this, "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(this, "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}
	
}
