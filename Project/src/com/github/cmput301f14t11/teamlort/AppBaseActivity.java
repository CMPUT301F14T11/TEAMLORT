package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;

import com.github.cmput301f14t11.teamlort.Controller.NetworkController;
import com.github.cmput301f14t11.teamlort.Controller.ProfileController;
import com.github.cmput301f14t11.teamlort.Model.AppCache;
import com.github.cmput301f14t11.teamlort.Model.NetworkListener;
import com.github.cmput301f14t11.teamlort.Model.PersistentDataManager;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

/**
 * 
 * overlay actionbar that appears on all activity
 * so user can search, access profile, ask question, at any time
 *
 *
 */
public class AppBaseActivity extends Activity
{
	private LayoutInflater inflater;
	private ImageButton searchButton;
	private PopupMenu pMenu;
	protected ProfileController pc;
	protected NetworkListener nc;
	private ImageButton sortButton;
	AppCache appCache;
	AlertDialog alertDialog = null;
	
	@SuppressLint("InflateParams")
	@Override
	protected void onCreate(Bundle inState)
	{
		super.onCreate(inState);
		this.setContentView(R.layout.activity_app_base);
		//initialize persistent data manager
		//final PersistentDataManager pdm = PersistentDataManager.getInstance();
		// Set the top ActionBar to a custom view.
        this.inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ActionBar ab = this.getActionBar();
        
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ab.setCustomView(inflater.inflate(R.layout.actionbar_top_layout, null));
        // Connect the buttons in the top ActionBar
        final EditText searchinput = (EditText)this.findViewById(R.id.searchfield);
        searchButton = (ImageButton) this.findViewById(R.id.action_search);
        sortButton = (ImageButton) this.findViewById(R.id.action_sort);
		pMenu = new PopupMenu(this, searchButton);
		pMenu.getMenuInflater().inflate(R.menu.app_base_sort, pMenu.getMenu());
		pMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
		{
			@Override
			public boolean onMenuItemClick(MenuItem item)
			{
				return AppBaseActivity.this.onSortMenuItemSelect(item);
			}
		});
		/**
		 * it is come to my realization the result - arraylist of question 
		 * has to be accquired here inorder for the sort function to work
		 * what whill be displayed in the homeactivity will be passed from here
		 */
        searchButton.setOnClickListener(new View.OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				
				//ArrayList<Question>results = pdm.searchQuestions();// we need to somehow grab the input user provided 
				Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
				intent.putExtra(searchinput.getText().toString(), "searchstring");
				//
				//searchinput.getText().toString()
				if(searchinput.getText().toString() == null || searchinput.getText().toString() == "")
				{
					//pass a *
					intent.putExtra("searchstring","*");
				}
				else
				{
					//pass this searchstring to homeactivity
					//we will do the searching in homeactivity
					intent.putExtra("searchstring",searchinput.getText().toString());
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
				AppBaseActivity.this.pMenu.show();
			}
        	
        });
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
		case (R.id.action_favorites):
			Intent favoriteQuestionintent = new Intent(this, ProfileActivity.class);
			startActivity(favoriteQuestionintent);
			return true;
		
		case (R.id.action_new_question):
			Intent intent = new Intent(this,ComposeQuestionActivity.class);
			startActivity(intent);
			return true;
		
		case (R.id.action_profile):
			Intent profileintent = new Intent(this,ProfileActivity.class);
			startActivity(profileintent);
			return true;
		
		case (R.id.action_login):
		AlertDialog.Builder alert = buildlogin();
		alertDialog = alert.show();
			return true;
		
		case (R.id.action_settings):
			return true;
		
		case (R.id.action_about):
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
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
				pc.getP().setUsername(username);
				appCache = AppCache.getInstance();
				appCache.setProfile(pc.getP());
				Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
				intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
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
		
		default:
			return false;
		}
	}
	
}
