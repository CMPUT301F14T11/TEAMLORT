package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;

import com.github.cmput301f14t11.teamlort.Model.PersistentDataManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class QuestionViewActivity
extends AppBaseActivity
{
	
	Question question;
	ArrayList<Answer> listofanswer;
	DataController dt = new DataController();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_view);
		
		// TODO Fix how question is being received later
		
		Intent intent = getIntent();
		int questionID = intent.getIntExtra("id", 2); //Need question ID not question position in ListView
		//If no question with questionID found:
		if(questionID == -1){
			
			Toast.makeText(this, "Error: Question not found", Toast.LENGTH_SHORT).show();
			finish();
		}
		
		PersistentDataManager pdm = PersistentDataManager.getInstance();
		
		//Set question title and description on GUI.
		//question = pdm.get(questionID);
		//TODO These are just temporary test question/answers/replies
		question = dt.initQuestion("testing", "testing", "sdfasfds");
		Reply reply = new Reply();
		reply.setBody("test reply");
		reply.setAuthor("test reply author");
		Answer answer = new Answer();
		answer.setBody("dsadasd");
		answer.setAuthor("asdsadas");
		answer.addReply(reply);
		question.addAnswer(answer);
		listofanswer = question.getAnswerList();
		
		ExpandableListView answerListView = (ExpandableListView) findViewById(R.id.answer_list_view);
		LayoutInflater layoutInflater = getLayoutInflater();
		
		OnClickListener onClickListener = new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v.getId() == R.id.favorite_button)
				{
					//addtofavorite
					
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
		
		TextView questionTitleTextView = (TextView) header.findViewById(R.id.UsernameTitleTextView);
		questionTitleTextView.setText(question.getTitle());
		TextView questionBodyTextView = (TextView) header.findViewById(R.id.QuestionBodyTextView);
		questionBodyTextView.setText(question.getBody());
		TextView questionTimeTextView = (TextView) header.findViewById(R.id.QuestionTimeTextView);
		questionTimeTextView.setText(question.getTime().toString());
		
		// TODO make these buttons do things
		
		//Button favoriteButton = (Button) header.findViewById(R.id.favorite_button);
		//Button saveButton = (Button) header.findViewById(R.id.save_button);
		
		//Add the header top top of listview
		answerListView.addHeaderView(header, null, false);
		
		AnswerAdapter answerAdapter = new AnswerAdapter(listofanswer, this);
		answerListView.setAdapter(answerAdapter);

		/*favoriteButton.setOnClickListener(onClickListener);
		saveButton.setOnClickListener(onClickListener);//here, these button will now do things*/
	}
	
}
