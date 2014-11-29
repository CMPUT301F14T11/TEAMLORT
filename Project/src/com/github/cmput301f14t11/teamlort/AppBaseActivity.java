package com.github.cmput301f14t11.teamlort;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.github.cmput301f14t11.teamlort.Controller.ProfileController;
import com.github.cmput301f14t11.teamlort.Model.AppCache;
import com.github.cmput301f14t11.teamlort.Model.LocalManager;
import com.github.cmput301f14t11.teamlort.Model.NetworkListener;

/**
 * A base activity used to maintain consistency of the menu bars throughout
 * the application. Additionally, this Activity implements save-in-place for
 * some user data that must be maintained throughout the application.
 * 
 * @author Brandon Yue
 * @author sbao
 *
 */
public class AppBaseActivity extends Activity
{
	private LayoutInflater inflater;
	private ImageButton searchButton;
	private PopupMenu mPopupMenu;
	private EditText mSearchInput;
	private ImageButton sortButton;
	
	protected ProfileController mProfileController;
	protected NetworkListener mNetworkListener;
	protected AppCache appCache = AppCache.getInstance();
	protected AlertDialog alertDialog = null;
	private ImageView helpimage;
	
	@SuppressLint("InflateParams")
	@Override
	protected void onCreate(Bundle inState)
	{
		super.onCreate(inState);
		this.setContentView(R.layout.activity_app_base);
		getWindow().getDecorView().setBackgroundColor(0xbbcc67);
		// Set the top ActionBar to a custom view.
        this.inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ActionBar ab = this.getActionBar();
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ab.setCustomView(inflater.inflate(R.layout.actionbar_top_layout, null));
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setSplitBackgroundDrawable(new ColorDrawable(Color.parseColor("#958dba")));
        
        // Connect the buttons in the top ActionBar
        getLayoutResources();
		attachListeners();
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
		
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		LocalManager.getManager().saveProfileToDefault(appCache.getProfile());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.app_base, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case (R.id.actionbar_app_icon):
	        NavUtils.navigateUpFromSameTask(this);
	        return true;
		case (R.id.action_favorites):
			Intent favoriteQuestionintent = new Intent(this, ProfileActivity.class);
			startActivity(favoriteQuestionintent);
			return true;
		case (R.id.action_new_question):
			Intent intent = new Intent(this,ComposeQuestionActivity.class);
			startActivity(intent);
			return true;
		case (R.id.action_help):
			return true;
		case (R.id.action_profile):
			Intent profileintent = new Intent(this,ProfileActivity.class);
			startActivity(profileintent);
			return true;
		
		case (R.id.action_login):
			AlertDialog.Builder alert = buildlogin();
			alertDialog = alert.show();
			return true;
		
		case (R.id.action_geolocation):
			Intent settingsintent = new Intent(this,SettingsActivity.class);
			startActivity(settingsintent);
			return true;
		
		case (R.id.action_about):
			AlertDialog.Builder about = buildabout();
			alertDialog = about.show();
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	protected boolean onSortMenuItemSelect(MenuItem item)
	{
		switch (item.getItemId())
		{
		case (R.id.action_sort_by_date):
			return true;
		
		case (R.id.action_sort_by_score):
			return true;
		
		case (R.id.action_sort_by_pictures):
			return true;
		
		case (R.id.action_sort_by_location):
			return true;
		
		case (R.id.action_sort_by_replies):
			return true;
		default:
			return false;
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle inState)
	{
		super.onRestoreInstanceState(inState);
	}
	protected AlertDialog.Builder buildhelp(Drawable provided) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Help");
		
		//alert.setMessage("Please type your username: ");
		
		helpimage = (ImageView) this.findViewById(R.id.compose_img_preview);
		helpimage.setImageDrawable(provided);
		alert.setView(helpimage);
		alert.setPositiveButton("Got it", new DialogInterface.OnClickListener() 
		{
		
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				
			}
		});
		return alert;
	}
	
	protected AlertDialog.Builder buildlogin() {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Username");
		alert.setMessage("Please type your username: ");
		final EditText input = new EditText(this);
		alert.setView(input);
		alert.setPositiveButton("Log in", new DialogInterface.OnClickListener() 
		{
		
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				String username = input.getText().toString();
				//Log.i("r1231231",username);
				mProfileController.getP().setUsername(username);
				appCache = AppCache.getInstance();	
				appCache.setProfile(mProfileController.getP());
				Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
				startActivity(intent);
				
			}
		});
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		return alert;
	}
	protected AlertDialog.Builder buildabout() {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("About this App");
		alert.setMessage("Beta 1.0 - Team LORT, Nov/2014");
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
	 * Auxiliary method that retrieves layout resources. Used on activity
	 * creation.
	 */
	private void getLayoutResources()
	{
		mSearchInput = (EditText)this.findViewById(R.id.searchfield);
        searchButton = (ImageButton) this.findViewById(R.id.action_search);
        sortButton = (ImageButton) this.findViewById(R.id.action_sort);
		mPopupMenu = new PopupMenu(this, searchButton);
		mPopupMenu.getMenuInflater().inflate(R.menu.app_base_sort, mPopupMenu.getMenu());
		LocalManager.getManager().SetContext(getApplicationContext());
	}
	
	/**
	 * Auxiliary method that attaches the relevant listeners to layout
	 * resources. Should be called after getLayoutResources().
	 */
	private void attachListeners()
	{
		mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
		{
			@Override
			public boolean onMenuItemClick(MenuItem item)
			{
				return AppBaseActivity.this.onSortMenuItemSelect(item);
			}
		});
		
        searchButton.setOnClickListener(new View.OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				
				//ArrayList<Question>results = pdm.searchQuestions();// we need to somehow grab the input user provided 
				Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
				intent.putExtra(mSearchInput.getText().toString(), "searchstring");
				//
				//searchinput.getText().toString()
				if(mSearchInput.getText().toString() == null || mSearchInput.getText().toString() == "")
				{
					//pass a *
					intent.putExtra("searchstring","*");
				}
				else
				{
					//pass this searchstring to homeactivity
					//we will do the searching in homeactivity
					intent.putExtra("searchstring",mSearchInput.getText().toString());
				}
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
        
        sortButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				AppBaseActivity.this.mPopupMenu.show();
			}
        	
        });
	}
}
