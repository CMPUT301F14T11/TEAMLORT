package com.github.cmput301f14t11.teamlort;


import java.io.File;
import java.io.IOException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
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
import com.github.cmput301f14t11.teamlort.Model.ImageBuilder;
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
extends AppBaseActivity implements LocationListener
{
	private LocationManager locationManager;
	
	private static final String TITLE_BUNDLE_KEY = "COMPOSE_TITLE";
	private static final String DETAIL_BUNDLE_KEY = "COMPOSE_DETAIL";
	private static final String URI_BUNDLE_KEY = "COMPOSE_URI";
	
	private String title, detail;
	private Drawable pic = null;
	private Uri imageFileUri;
	
	private Profile usrProfile;
	private QuestionController qController;
	private GpsLocation location;
	private ImageBuilder iBuilder = new ImageBuilder();
	
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
	protected void onResume()
	{
		if (pic != null)
			imgView.setImageDrawable(pic);
		super.onResume();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		
		outState.putString(TITLE_BUNDLE_KEY, titleEntry.getText().toString());
		outState.putString(DETAIL_BUNDLE_KEY, detailEntry.getText().toString());
		outState.putString(URI_BUNDLE_KEY, imageFileUri.toString());
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle inState)
	{
		super.onRestoreInstanceState(inState);
		
		String title = inState.getString(TITLE_BUNDLE_KEY);
		String detail = inState.getString(DETAIL_BUNDLE_KEY);
		String uri = inState.getString(URI_BUNDLE_KEY);
		
		if (title != null) titleEntry.setText(title);
		if (detail != null) detailEntry.setText(detail);
		if (uri != null) imageFileUri = Uri.parse(uri);
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
		case (ImageBuilder.IMAGE_REQUEST_CODE):
			if (resultCode == RESULT_OK)
			{
				pic = iBuilder.RetrieveImageFromStorage(this);
				/*
				Bitmap rawImg = BitmapFactory.decodeFile(imageFileUri.getPath());
				if (rawImg != null)
				{
					Drawable compressMe = (Drawable) new BitmapDrawable(rawImg);
					new CompressImageTask().execute(compressMe);
				}
				else 
					Toast.makeText(getApplicationContext(), "Oops! Something went wrong with the camera.", Toast.LENGTH_LONG).show();
					*/
			}
			else
			{
				
			}
			break;
			
		default:
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
		
		imgView = (ImageView) this.findViewById(R.id.compose_img_preview);
		
		progressBar = (ProgressBar) this.findViewById(R.id.compose_progress_bar);
		progressBar.setVisibility(View.GONE);
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
		if(!isInputValid()) return;

		Question question;
		
		if (getLocation()) {
			question = ObjectFactory.initQuestion(
				title,
				detail,
				usrProfile.getUsername(),
				location,
				pic
				);
		}
		else
		{
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
	
	/**
	 * Auxiliary method.
	 * Retrieves relavent input fields' data and saves them to local variables.
	 */
	private void getInputFields()
	{
		title = ((EditText) findViewById(R.id.compose_title_entry))
				.getText().toString();
		detail = ((EditText) findViewById(R.id.compose_desc_entry))
				.getText().toString();
		pic = ((ImageView) findViewById(R.id.compose_img_preview))
				.getDrawable();
	}
	
	/**
	 * Auxiliary method.
	 * Checks if the local variables representing the question are valid. Should
	 * be called after getInputFields()
	 */
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
	
	/**
	 * Auxiliary method.
	 * Starts an intent for a camera application, in order to retrieve data.
	 */
	private void getPhoto()
	{	
		iBuilder.SendImageIntent(this);
		
		/*
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		if (intent.resolveActivity(getPackageManager()) != null)
		{
			File photoFile = null;
			
			try
			{
				photoFile = createImgTempFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			if (photoFile != null)
			{
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
				startActivityForResult(intent, ComposeQuestionActivity.IMAGE_REQUEST_CODE);
			}
		}*/
	}
	
	/*
	private File createImgTempFile()
	throws IOException
	{
		String fileName =
				"IMG_" + 
				new SimpleDateFormat("yyyy_MM_dd_-_HHmmss", Locale.getDefault()).format(new Date());
		
		File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		
		File imageFile = File.createTempFile(fileName, ".jpg", dir);
		if (imageFile != null)
		{
			imageFileUri = Uri.fromFile(imageFile);
		}
		
		return imageFile;
	}//*/

	private boolean getLocation() {
		
		location = usrProfile.getLocation(locationManager);
		if (location == new GpsLocation(0,0)) {
			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * Auxiliary method.
	 * Locates and creates (if necessary) a directory that external apps can
	 * use to transfer data to this one.
	 */
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
	
	private class SubmitNewQuestion
	extends AsyncTask<Question, Void, Void>
	{
		@Override
		protected Void doInBackground(Question... args)
		{
			PushQueue.getInstance().pushQuestion(args[0], getApplicationContext());
			//qController.addQuestion(args[0]);
			return null;
		}
	}
	
	/**
	 * Auxiliary class.
	 * Used to compress an image into its required size in the background.
	 *//*
	private class CompressImageTask
	extends AsyncTask<Drawable, Void, Drawable>
	{
		ProgressBar proressBar;
		ImageView imageView;
		
		public CompressImageTask()
		{
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
			
			imageView.setImageDrawable(result);
			ComposeQuestionActivity.this.pic = result;
			
			super.onPostExecute(result);
		}
	}//*/

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
