package com.github.cmput301f14t11.teamlort;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class ComposeQuestionActivity
extends AppBaseActivity {
	
	//private Profile usrProfile;
	
	//private QuestionController qController;
	//private ProfileController pController;
	
	private EditText titleEntry;
	private EditText detailEntry;
	private EditText tagEntry;
	
	private Button addImageButton;
	private Button acceptButton;
	private Button cancelButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose_question);
		
		GetModel();
		GetControllers();
		GetLayoutElements();
		AttachListeners();
	}

	/*
	 * Auxiliary
	 */
	private void GetModel()
	{
		
	}
	private void GetControllers()
	{
		
	}
	private void GetLayoutElements()
	{
		titleEntry  = (EditText) this.findViewById(R.id.compose_title_entry);
		detailEntry = (EditText) this.findViewById(R.id.compose_desc_entry);
		tagEntry    = (EditText) this.findViewById(R.id.compose_tags_entry);
		
		addImageButton = (Button) this.findViewById(R.id.compose_add_img_button);
		acceptButton   = (Button) this.findViewById(R.id.compose_accept_button);
		cancelButton   = (Button) this.findViewById(R.id.compose_cancel_button);
	}
	private void AttachListeners()
	{
		titleEntry.setOnEditorActionListener(new OnEditorActionListener()
		{	
			@Override
			public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2)
			{
				if (arg1 == EditorInfo.IME_ACTION_NEXT)
				{
					ComposeQuestionActivity.this.detailEntry.requestFocus();
					return true;
				}
				return false;
			}
		});
		
		detailEntry.setOnEditorActionListener(new TextView.OnEditorActionListener()
		{			
			@Override
			public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2)
			{
				if (arg1 == EditorInfo.IME_ACTION_NEXT)
				{
					ComposeQuestionActivity.this.tagEntry.requestFocus();
					return true;
				}
				return false;
			}
		});
		
		/* Probably don't need this one.
		tagEntry.setOnEditorActionListener(new OnEditorActionListener()
		{
			@Override
			public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2)
			{
				
				return false;
			}
		});//*/
		
		addImageButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// TODO: Send intent for content.
			}
		});
		
		acceptButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// Get all the information from the views.
				String title, detail, tags;
				
				title = ((EditText) ComposeQuestionActivity.this.
						findViewById(R.id.compose_title_entry))
						.getText().toString();
				detail = ((EditText) ComposeQuestionActivity.this.
						findViewById(R.id.compose_desc_entry))
						.getText().toString();
				tags = ((EditText) ComposeQuestionActivity.this.
						findViewById(R.id.compose_tags_entry))
						.getText().toString();
				
				// Make sure the user has at least entered a title.
				if(title.isEmpty() || title == null)
				{
					Toast.makeText(getApplicationContext(), 
							"You need to enter a title!",
							Toast.LENGTH_SHORT).show();
					
					ComposeQuestionActivity.this.titleEntry.requestFocus();
					return;
				}
				
				//ComposeQuestionActivity.this.qController
				//.newQuestion(args);
			}
		});
		
		cancelButton.setOnClickListener(new View.OnClickListener()
		{	
			@Override
			public void onClick(View arg0)
			{
				// Cancel and finish the activity.
				ComposeQuestionActivity.this.setResult(RESULT_CANCELED);
				ComposeQuestionActivity.this.finish();
			}
		});
	}

}
