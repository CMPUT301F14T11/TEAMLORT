package com.github.cmput301f14t11.teamlort;

import com.github.cmput301f14t11.teamlort.Model.PersistentDataManager;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;

public class AppBaseActivity
extends Activity
{
	private LayoutInflater inflater;
	private ImageButton searchButton;
	private PopupMenu pMenu;
	
	@SuppressLint("InflateParams")
	@Override
	protected void onCreate(Bundle inState)
	{
		super.onCreate(inState);
		this.setContentView(R.layout.activity_app_base);
		//initialize persistent data manager
		final PersistentDataManager pdm = PersistentDataManager.getInstance();
		// Set the top ActionBar to a custom view.
        this.inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ActionBar ab = this.getActionBar();
        
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ab.setCustomView(inflater.inflate(R.layout.actionbar_top_layout, null));
        // Connect the buttons in the top ActionBar
        final EditText searchinput = (EditText)this.findViewById(R.id.searchfield);
        searchButton = (ImageButton) this.findViewById(R.id.action_sort);
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
	
        searchButton.setOnClickListener(new View.OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				AppBaseActivity.this.pMenu.show();
				pdm.searchQuestions(searchinput.getText().toString());// we need to somehow grab the input user provided 
				
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
			return true;
		
		case (R.id.action_new_question):
			return true;
		
		case (R.id.action_profile):
			return true;
		
		case (R.id.action_login):
			return true;
		
		case (R.id.action_settings):
			return true;
		
		case (R.id.action_about):
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
		
		default:
			return false;
		}
	}
}
