package com.github.cmput301f14t11.teamlort;


import java.io.File;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.github.cmput301f14t11.teamlort.Controller.QuestionController;
import com.github.cmput301f14t11.teamlort.Model.AppCache;
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
	private static final int IMAGE_REQUEST_CODE = 1;
	
	private static final String TITLE_BUNDLE_KEY = "COMPOSE_TITLE";
	private static final String DETAIL_BUNDLE_KEY = "COMPOSE_DETAIL";
	
	private String title, detail;
	private Drawable pic = null;
	private Uri imageFileUri;
	
	private Profile usrProfile;
	private QuestionController qController;
	
	private EditText titleEntry;
	private EditText detailEntry;
	
	private ImageButton addImageButton;
	private ImageButton acceptButton;
	private ImageButton cancelButton;
	private ImageView imgView;

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
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle inState)
	{
		super.onRestoreInstanceState(inState);
		
		String title = inState.getString(TITLE_BUNDLE_KEY);
		String detail = inState.getString(DETAIL_BUNDLE_KEY);
		
		if (title != null) titleEntry.setText(title);
		if (detail != null) detailEntry.setText(detail);
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
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		switch (requestCode)
		{
		case (ComposeQuestionActivity.IMAGE_REQUEST_CODE):
			if (resultCode == RESULT_OK)
			{
				imgView = (ImageView) this.findViewById(R.id.compose_img_preview);
				imgView.setImageDrawable(Drawable.createFromPath(imageFileUri.getPath()));
			}
			break;
			
		default:
			super.onActivityResult(requestCode, resultCode, data);
			break;
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
		
		/*
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
		});//*/
		
		addImageButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				ComposeQuestionActivity.this.getPhoto();
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
	

	/**
	 * Auxiliary method.
	 * When the user clicks Accept, put everything together and send it to the controller.
	 */
	protected void onAcceptButtonClicked()
	{
		getInputFields();
		
		if(!isInputValid()) return;

		Question question = ObjectFactory.initQuestion(
				title,
				detail,
				usrProfile.getUsername(),
				usrProfile.getLocation()
				);
		
//		if (pic != null)
//		{
//			question = ObjectFactory.initQuestion(
//					title,
//					detail,
//					usrProfile.getUsername(),
//					usrProfile.getLocation(),
//					pic
//					);
//			
//		}
		
		PushQueue.getInstance().pushQuestion(question, getApplicationContext());
		new SubmitNewQuestion().execute(question);
		
		this.setResult(RESULT_OK);
		this.finish();
	}
	
	private void getInputFields()
	{
		title = ((EditText) findViewById(R.id.compose_title_entry))
				.getText().toString();
		detail = ((EditText) findViewById(R.id.compose_desc_entry))
				.getText().toString();
		
		pic = ((ImageView) findViewById(R.id.compose_img_preview)).getDrawable();
	}
	
	private boolean isInputValid()
	{
		// Make sure the user has at least entered a title.
		if(title.isEmpty() || title == null)
		{
			Toast.makeText(getApplicationContext(), 
					"You need to enter a title!",
					Toast.LENGTH_SHORT).show();
			
			titleEntry.requestFocus();
			return false;
		}
		
		// Make sure the user has entered some body text.
		if (detail.isEmpty() || detail == null)
		{
			Toast.makeText(getApplicationContext(), 
					"You need to enter some body text!",
					Toast.LENGTH_SHORT).show();
			
			detailEntry.requestFocus();
			return false;
		}
		
		return true;
	}
	
	private void getPhoto()
	{
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		String folder = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/tmp";
		File folderF = new File(folder);
		if (!folderF.exists())
		{
			folderF.mkdir();
		}

		String imageFilePath = folder + "/"
				+ String.valueOf(System.currentTimeMillis()) + ".jpg";
		File imageFile = new File(imageFilePath);
		imageFileUri = Uri.fromFile(imageFile);

		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
		
		startActivityForResult(intent, ComposeQuestionActivity.IMAGE_REQUEST_CODE);
	}
	
	private class SubmitNewQuestion
	extends AsyncTask<Question, Void, Void>
	{
		@Override
		protected Void doInBackground(Question... args)
		{
			qController.addQuestion(args[0]);
			return null;
		}
	}
}
