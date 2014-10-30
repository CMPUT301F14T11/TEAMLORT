package com.github.cmput301f14t11.teamlort;

import com.github.cmput301f14t11.teamlort.Model.PersistentDataManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

public class ComposeQuestionActivity
extends AppBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PersistentDataManager pdm = PersistentDataManager.getInstance();
		final DataController dt = new DataController();
		//final UserController us = new UserController();
		setContentView(R.layout.activity_compose_question);
		ImageButton accept = (ImageButton)findViewById(R.id.accept_button);
		ImageButton cancel = (ImageButton)findViewById(R.id.cancel_button);
		final EditText title = (EditText)findViewById(R.id.compose_title_entry);
		final EditText content = (EditText)findViewById(R.id.compose_desc_entry);
		
		OnClickListener onClickListener = new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v.getId() == R.id.accept_button)
				{
					//addquestion
					dt.dataManager.addQuestion(dt.initQuestion(title.getText().toString(), content.getText().toString(), dt.dataManager.getUsername()));
					finish();
				}
				else if(v.getId() == R.id.cancel_button);
				{
					finish();
				}
			}
			
		};
		accept.setOnClickListener(onClickListener);
		cancel.setOnClickListener(onClickListener);

			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose_question, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
