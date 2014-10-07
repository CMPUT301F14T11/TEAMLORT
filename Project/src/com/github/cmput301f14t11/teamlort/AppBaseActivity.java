package com.github.cmput301f14t11.teamlort;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class AppBaseActivity
extends Activity
{
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
}
