package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ProfileActivity extends AppBaseActivity {
	// Profile Activity is kind of same as home activity, so the listview adapter and entering single question activity
	// code are directed used in home activity
	static customadapter adapter; 
	ArrayList<Question> displayQuestionList = new ArrayList<Question>();
	ListView lv;
	
		
    //initialize controllers
	ProfileController pc = new ProfileController();
	ObjectFactory dt = new ObjectFactory();
	Profile p = new Profile();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		adapter = new customadapter(getApplicationContext(),p.getTestQuestionList());
		lv = (ListView) findViewById(R.id.ProfileQuestionListView);
		lv.setOnItemClickListener(new OnItemClickListener()//did the user press any questions?
			{

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					//JUMP TO QUESTION VIEW,FILLING DATA ACCORDING TO QUESTION ID - NEED MORE DISCUSSION
					Intent intent = new Intent(getApplicationContext(),QuestionViewActivity.class);
					Single_Home_Question holder = (Single_Home_Question) view.getTag();
					Question temp = (Question) holder.title.getTag();
					intent.putExtra("position", position);
					//intent.putExtra("id", 0);// corrected the id confusion as requested - Sam
					startActivity(intent);
					
				}
				
			});
		lv.setAdapter(adapter);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
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
	
	public void savedListButtonPressed(View view){
		adapter = new customadapter(getApplicationContext(),p.getTestQuestionList1());
		lv.setAdapter(adapter);
		
	}
	
	public void favedListButtonPressed(View view){
		adapter = new customadapter(getApplicationContext(),p.getTestQuestionList2());
		lv.setAdapter(adapter);
	}
	
	// when editUsername button is pressed, a pop-up window will show up 
	//and enable user to type their username to log in;
	//Inspired from http://www.androidsnippets.com/prompt-user-input-with-an-alertdialog
	public void editUsernameButtomPressed(View view){
		//UserController uc = new UserController();
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("UserName");
		alert.setMessage("Please type your username: ");
		final EditText input = new EditText(this);
		alert.setView(input);
		alert.setPositiveButton("Log in", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String username = input.getText().toString();
				pc.login(username);
				TextView tv = (TextView) findViewById(R.id.UsernameTitleTextView);
				tv.setText(username);
				// unfinished, after logging in, listview need to load data from the cloud.
			}
		});
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		alert.show();
	}
}
