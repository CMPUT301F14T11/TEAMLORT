package com.github.cmput301f14t11.teamlort;


import java.io.File;
import java.io.IOException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.github.cmput301f14t11.teamlort.Controller.QuestionController;
import com.github.cmput301f14t11.teamlort.Model.AppCache;
import com.github.cmput301f14t11.teamlort.Model.GpsLocation;
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
	
	private LocationManager locationManager;
	
	private static final String TITLE_BUNDLE_KEY = "COMPOSE_TITLE";
	private static final String DETAIL_BUNDLE_KEY = "COMPOSE_DETAIL";
	
	private String title, detail;
	private Drawable pic = null;
	private Uri imageFileUri;
	
	private Profile usrProfile;
	private QuestionController qController;
	private GpsLocation location;
	
	private EditText titleEntry;
	private EditText detailEntry;
	
	private ProgressBar progressBar;
	private ImageButton addImageButton;
	private ImageButton acceptButton;
	private ImageButton cancelButton;
	private ImageView imgView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose_question);
		
		locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		
		GetProfile();
		GetControllers();
		GetLayoutElements();
		GetTmpFileDir();
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
			case R.id.action_help :
	        	helpscreen = getResources().getDrawable(R.drawable.helpscreen_ask);
	        	AlertDialog.Builder alert = buildhelp(helpscreen);
				alertDialog = alert.show();
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
				Drawable compressMe = Drawable.createFromPath(imageFileUri.getPath());
				if (compressMe != null)
					new CompressImageTask().execute(compressMe);
				else 
					Toast.makeText(getApplicationContext(), "Oops! Something went wrong with the camera.", Toast.LENGTH_LONG).show();
			}
			else
			{
				
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
		qController = new QuestionController(getApplicationContext());
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
		
		progressBar = (ProgressBar) this.findViewById(R.id.compose_progress_bar);
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
	private void onAcceptButtonClicked()
	{
		getInputFields();
		
		String msg = "Constructing a new question with params:"
				+ " Title:" + title
				+ " Body:" + detail
				+ " Author:" + usrProfile.getUsername()
				+ " Pic:"
				;
		if (pic == null)
			msg += "NULL";
		else
			msg += "NOT_NULL";
		
		Log.d("com.github.cmput301f14t11.teamlort.ComposeQuestionActivity.onAcceptButtonClicked()", msg);
		
		Question question;
		
		if(!isInputValid()) return;
		
		
		if (getLocation()) {
			question = ObjectFactory.initQuestion(
				title,
				detail,
				usrProfile.getUsername(),
				location,
				pic
				);
		} else {
			question = ObjectFactory.initQuestion(
					title,
					detail,
					usrProfile.getUsername(),
					pic
					);
		}
		
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
		pic = ((ImageView) findViewById(R.id.compose_img_preview))
				.getDrawable();
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

		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
		
		startActivityForResult(intent, ComposeQuestionActivity.IMAGE_REQUEST_CODE);
	}
	
	private boolean getLocation() {
		
		location = usrProfile.getLocation(locationManager);
		if (location == new GpsLocation(0,0)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private class SubmitNewQuestion
	extends AsyncTask<Question, Void, Void>
	{
		@Override
		protected Void doInBackground(Question... args)
		{
			String msg = "Submitting a new question with params:"
					+ " Title:" + args[0].getTitle()
					+ " Body:" + args[0].getBody()
					+ " Author:" + args[0].getAuthor()
					+ " Pic:"
					;
			if (args[0].getPicture() == null)
				msg += "NULL";
			else
				msg += "NOT_NULL";
			
			Log.d("com.github.cmput301f14t11.teamlort.ComposeQuestionActivity.SubmitNewQuestion.doInBackground(Question...)",
					msg);
			PushQueue.getInstance().pushQuestion(args[0], getApplicationContext());
			//qController.addQuestion(args[0]);
			return null;
		}
	}
	
	private void GetTmpFileDir()
	{
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
		
		if (!imageFile.exists())
			try
			{
				imageFile.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		
		imageFileUri = Uri.fromFile(imageFile);
	}
	
	private class CompressImageTask
	extends AsyncTask<Drawable, Void, Drawable>
	{
		ProgressBar proressBar;
		ImageView imageView;
		
		public CompressImageTask()
		{
			super();
			progressBar = (ProgressBar) ComposeQuestionActivity.this.findViewById(R.id.compose_progress_bar);
			imageView   = (ImageView)   ComposeQuestionActivity.this.findViewById(R.id.compose_img_preview);
		}
		
		@Override
		protected void onPreExecute()
		{
			//proressBar.setVisibility(View.VISIBLE);
			//imageView.setVisibility(View.GONE);
			super.onPreExecute();
		}
		
		@Override
		protected Drawable doInBackground(Drawable... params)
		{
			Drawable result = params[0];
			if (result == null) return null;
			
			Bitmap bitmap = ((BitmapDrawable) result).getBitmap();
			
			while (bitmap.getByteCount() > 64000)
			{
				long resizeHeight = Math.round(bitmap.getHeight() * 0.9);
				long resizeWidth  = Math.round(bitmap.getWidth()  * 0.9);
				
				bitmap = Bitmap.createScaledBitmap(bitmap, (int) resizeWidth, (int) resizeHeight, false);
			}
			
			return (Drawable) new BitmapDrawable(bitmap);
		}
		
		@Override
		protected void onCancelled(Drawable result)
		{
			//progressBar.setVisibility(View.GONE);
			//imageView.setVisibility(View.VISIBLE);
			
			super.onCancelled(result);
		}
		
		@Override
		protected void onPostExecute(Drawable result)
		{
			//progressBar.setVisibility(View.GONE);
			//imageView.setVisibility(View.VISIBLE);
			
			ComposeQuestionActivity.this.imgView.setImageDrawable(result);
			ComposeQuestionActivity.this.pic = result;
			
			super.onPostExecute(result);
		}
	}
}
