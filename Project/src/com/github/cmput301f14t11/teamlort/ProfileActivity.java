package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;

import com.github.cmput301f14t11.teamlort.Controller.ProfileController;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.Profile;
import com.github.cmput301f14t11.teamlort.Model.Question;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


/**
 * Profile activity is an UI activity class implemented that users are enable to 
 * go to profile layout from the home and retrieve profile data from the local files.
 *
 */
public class ProfileActivity extends AppBaseActivity {
	static customadapter adapter; 
	ArrayList<Question> displayQuestionList = new ArrayList<Question>();
	ListView lv;
    AlertDialog alertDialog = null;

	/**
	 * Instantiate the controllers and models
	 */
	
	ObjectFactory dt = new ObjectFactory();
	//Profile p = new Profile();

	
	/**
	 * onCreate method will initial the main layout of profile activity and 
	 * first load the author's question list from the local manager and when 
	 * the user click on the question, it should be able to bring user to the
	 * QuestionViewActivity to see the detail of the chosen question
	 *
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		adapter = new customadapter(getApplicationContext(),pc.getP().getTestQuestionList(1));
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

	/**
	 *  Handle action bar item clicks here. The action bar will 
	 *  automatically handle clicks on the Home/Up button, so long
	 *  as you specify a parent activity in AndroidManifest.xml.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	/**
	 *  when save button is pressed, the method will return the saved list from 
	 *  the local manager
	 *  @issue not implemented
	 */
	public void savedListButtonPressed(View view){
		adapter = new customadapter(getApplicationContext(),pc.getP().getTestQuestionList(2));
		lv.setAdapter(adapter);
		
	}
	/**
	 *  when favorite button is pressed, the method will return the faved list from 
	 *  the local manager
	 *  @issue not implemented
	 */
	public void favedListButtonPressed(View view){
		adapter = new customadapter(getApplicationContext(),pc.getP().getFavedQuestionList());
		lv.setAdapter(adapter);
	}
	/**
	 * when editUsername button is pressed, a pop-up window will show up 
	 * and enable user to type their username to log in;
	 * Inspired from http://www.androidsnippets.com/prompt-user-input-with-an-alertdialog
	 * @issue not fully implement: after logging in, listview need to load data from the cloud
	 * 		  and local manager.
	 */
	public void editUsernameButtomPressed(View view){
		AlertDialog.Builder alert = buildlogin();
		alertDialog = alert.show();
		//alertDialog.show();
		
	}
	
	
	/**
	 * Used to get current profile 
	 * @return profile hold by profile activity
	 */
	public Profile getProfile(){
		return pc.getP();
	}
}
