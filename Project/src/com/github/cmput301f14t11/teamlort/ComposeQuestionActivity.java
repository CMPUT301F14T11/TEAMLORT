package com.github.cmput301f14t11.teamlort;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.github.cmput301f14t11.teamlort.Controller.QuestionController;
import com.github.cmput301f14t11.teamlort.Model.AppCache;
import com.github.cmput301f14t11.teamlort.Model.NetworkListener;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.Profile;
import com.github.cmput301f14t11.teamlort.Model.PushQueue;
import com.github.cmput301f14t11.teamlort.Model.Question;

/**
 * Compose Question Activity: Pretty self explanatory. You compose new
 * questions via this activity.
 * @author Brandon Yue
 */
public class ComposeQuestionActivity
extends AppBaseActivity
{
	
	private static final String TITLE_BUNDLE_KEY = "COMPOSE_TITLE";
	private static final String DETAIL_BUNDLE_KEY = "COMPOSE_DETAIL";
	private static final String TAGS_BUNDLE_KEY = "COMPOSE_TAGS";
	
	private Profile usrProfile;
	private QuestionController qController;
	
	private EditText titleEntry;
	private EditText detailEntry;
	private EditText tagEntry;
	
	private ImageButton addImageButton;
	private ImageButton acceptButton;
	private ImageButton cancelButton;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose_question);
		
		GetProfile();
		GetControllers();
		GetLayoutElements();
		AttachListeners();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		
		outState.putString(TITLE_BUNDLE_KEY, titleEntry.getText().toString());
		outState.putString(DETAIL_BUNDLE_KEY, detailEntry.getText().toString());
		outState.putString(TAGS_BUNDLE_KEY, tagEntry.getText().toString());
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle inState)
	{
		super.onRestoreInstanceState(inState);
		
		String title = inState.getString(TITLE_BUNDLE_KEY);
		String detail = inState.getString(DETAIL_BUNDLE_KEY);
		String tags = inState.getString(TAGS_BUNDLE_KEY);
		
		if (title != null) titleEntry.setText(title);
		if (detail != null) detailEntry.setText(detail);
		if (tags != null) tagEntry.setText(tags);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.action_new_question:
			// Do nothing!
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Auxiliary method.
	 * Gets the current user profile. The username from the profile is
	 * required to construct the new question.
	 */
	private void GetProfile()
	{
		usrProfile = AppCache.getInstance().getProfile();
	}
	
	/**
	 * Auxiliary method.
	 * Get some new controllers for the activity.
	 */
	private void GetControllers()
	{
		qController = new QuestionController();
	}
	
	/**
	 * Auxiliary method.
	 * Gets references to the view's layout elements.
	 */
	private void GetLayoutElements()
	{
		titleEntry  = (EditText) this.findViewById(R.id.compose_title_entry);
		detailEntry = (EditText) this.findViewById(R.id.compose_desc_entry);
		tagEntry    = (EditText) this.findViewById(R.id.compose_tags_entry);
		
		addImageButton = (ImageButton) this.findViewById(R.id.compose_add_img_button);
		acceptButton   = (ImageButton) this.findViewById(R.id.compose_accept_button);
		cancelButton   = (ImageButton) this.findViewById(R.id.compose_cancel_button);
	}
	
	/**
	 * Auxiliary method.
	 * Attaches onClick and onEditorAction listeners to the appropriate views.
	 */
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
				ComposeQuestionActivity.this.onAcceptButtonClicked();
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
	private Runnable doFinishAdd = new Runnable() {
		public void run() {
			finish();
		}
	};
	class AddThread extends Thread {
		private Question question;

		public AddThread(Question question) {
			this.question = question;
		}

		@Override
		public void run() {
			
			PushQueue.getInstance(usrProfile).addQuestionToQueue(question, getApplicationContext());
			
			// Give some time to get updated info
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			runOnUiThread(doFinishAdd);
		}
	}
	/**
	 * Auxiliary method.
	 * When the user clicks Accept, put everything together and send it to the controller.
	 */
	protected void onAcceptButtonClicked()
	{
		// Get all the information from the views.
		String title, detail, tags;
		
		title = ((EditText) findViewById(R.id.compose_title_entry))
				.getText().toString();
		detail = ((EditText) findViewById(R.id.compose_desc_entry))
				.getText().toString();
		tags = ((EditText) findViewById(R.id.compose_tags_entry))
				.getText().toString();
		
		// Make sure the user has at least entered a title.
		if(title.isEmpty() || title == null)
		{
			Toast.makeText(getApplicationContext(), 
					"You need to enter a title!",
					Toast.LENGTH_SHORT).show();
			
			titleEntry.requestFocus();
			return;
		}
		
		// Make sure the user has entered some body text.
		if (detail.isEmpty() || detail == null)
		{
			Toast.makeText(getApplicationContext(), 
					"You need to enter some body text!",
					Toast.LENGTH_SHORT).show();
			
			detailEntry.requestFocus();
			return;
		}
		
		
		
		
		Question testing = ObjectFactory.initQuestion(
				title,
				detail,
				//usrProfile.getUsername()
				"Sam"
				);
		qController.providecontext(getApplicationContext());
		//qController.addQuestion(testing);
		Toast.makeText(getApplicationContext(), "question id is "+testing.getID(), Toast.LENGTH_SHORT).show();
		Thread thread = new AddThread(testing);
		if(NetworkListener.checkConnection(getApplicationContext())==true)
		{
			Toast.makeText(getApplicationContext(), "wifi on", Toast.LENGTH_SHORT).show();
			thread.start();
		}
		else
		{
			Toast.makeText(getApplicationContext(), "no wifi", Toast.LENGTH_SHORT).show();
			
		}
		// jumps back to home screen
		//Intent intent = new Intent(ComposeQuestionActivity.this,HomeActivity.class);
		//startActivity(intent);
	}
}
