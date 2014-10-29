package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;

import com.github.cmput301f14t11.teamlort.Model.PersistentDataManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionViewActivity
extends AppBaseActivity
{
	
	Question question;
	ArrayList<Answer> listofanswer;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_view);
		
		// TODO Fix how question is being received later
		
		Intent intent = getIntent();
		int questionID = intent.getIntExtra("id", -1); //Need question ID not question position in ListView
		//If no question with questionID found:
		if(questionID == -1){
			
			Toast.makeText(this, "Error: Question not found", Toast.LENGTH_SHORT).show();
			finish();
		}
		
		PersistentDataManager pdm = PersistentDataManager.getInstance();
		
		//Set question title and description on GUI.
		question = pdm.get(questionID);
		listofanswer = question.getAnswerList();
		
		ListView answerListView = (ListView) findViewById(R.id.answer_list_view);
		LayoutInflater layoutInflater = getLayoutInflater();
		
		//Setup the header
		ViewGroup header = (ViewGroup) layoutInflater.inflate(R.layout.question_view_header, answerListView, false);
		
		TextView questionTitleTextView = (TextView) header.findViewById(R.id.QuestionTitleTextView);
		questionTitleTextView.setText(question.getTitle());
		TextView questionBodyTextView = (TextView) header.findViewById(R.id.QuestionBodyTextView);
		questionBodyTextView.setText(question.getBody());
		TextView questionTimeTextView = (TextView) header.findViewById(R.id.QuestionTimeTextView);
		questionTimeTextView.setText(question.getTime().toString());
		
		// TODO make these buttons do things
		
		Button replyButton = (Button) header.findViewById(R.id.reply_button);
		Button favoriteButton = (Button) header.findViewById(R.id.favorite_button);
		Button saveButton = (Button) header.findViewById(R.id.save_button);
		
		//Add the header top top of listview
		answerListView.addHeaderView(header, null, false);
		
		AnswerAdapter answerAdapter = new AnswerAdapter(question.getAnswerList(), this);
		answerListView.setAdapter(answerAdapter);
		
		//I don't think this should be here?
		/*
		reply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Intent intent = new Intent(QuestionViewActivity.this,ReplyActivity.class);
            	startActivity(intent);
            }
        });
		*/
	}
	
}
