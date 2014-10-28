package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;
import java.util.Date;

import com.github.cmput301f14t11.teamlort.Model.PersistentDataManager;

import ca.ualberta.cs.ejlo.todolistforandroid.MainActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
		Button reply = (Button)findViewById(R.id.reply_button);
		
		Intent intent = getIntent();
		int questionID = intent.getIntExtra("id", -1); //Need question ID not question position in ListView
		//If no question with questionID found:
		if(questionID == -1){
			
			Toast.makeText(this, "Error: Question not found", Toast.LENGTH_SHORT).show();
			finish();
		} 
		//Else continue the activity
		PersistentDataManager pdm = PersistentDataManager.getInstance();
		
		//Set question title and description on GUI.
		question = pdm.get(questionID);
		listofanswer = question.getAnswerList();
		
		TextView questionTitleTextView = (TextView) findViewById(R.id.QuestionTitleTextView);
		questionTitleTextView.setText(question.getTitle());
		TextView questionBodyTextView = (TextView) findViewById(R.id.QuestionBodyTextView);
		questionBodyTextView.setText(question.getBody());
		
		//WIP, was looking at wrong date doc, will change
		/*
		TextView questionTimeTextView = (TextView) findViewById(R.id.QuestionTimeTextView);
		Date date = question.getTime();
		String[] splitDateString = date.toString().split("-");
		Date currentDate = new Date();
		String[] splitCurDateString = currentDate.toString().split("-");
		Integer curYear = new Integer(splitCurDateString[0]);
		Integer questionYear = new Integer(splitDateString[0]);
		if(curYear) 
		questionTimeTextView.setText("Posted " + "reply");
		*/
		
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
