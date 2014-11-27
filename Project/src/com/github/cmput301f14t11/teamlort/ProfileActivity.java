package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.github.cmput301f14t11.teamlort.Controller.ProfileController;
import com.github.cmput301f14t11.teamlort.Model.AppCache;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.Profile;
import com.github.cmput301f14t11.teamlort.Model.Question;


/**
 * Profile activity is an UI activity class implemented that users are enable to 
 * go to profile layout from the home and retrieve profile data from the local files.
 *
 */
public class ProfileActivity extends AppBaseActivity implements Observer {
	static HomeAdapter adapter; 
	ArrayList<Question> displayQuestionList = new ArrayList<Question>();
	ListView lv;
    AlertDialog alertDialog = null;

    static final int FAVORITE_QUESTION_VIEW = 1;
    static final int SAVE_QUESTION_VIEW = 2;
    static final int MY_QUESTION_VIEW = 3;
    static final int TEMP_QUESTION_VIEW = 4;
    
    int currentView = FAVORITE_QUESTION_VIEW;
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
		//pc.addObserver(this);
		
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		adapter = new HomeAdapter(getApplicationContext(),ProfileController.getP().getFavedQuestionList());
		lv = (ListView) findViewById(R.id.ProfileQuestionListView);
		lv.setOnItemClickListener(new OnItemClickListener()//did the user press any questions?
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//JUMP TO QUESTION VIEW,FILLING DATA ACCORDING TO QUESTION ID - NEED MORE DISCUSSION
				Intent intent = new Intent(getApplicationContext(),QuestionViewActivity.class);
				AppCache appCache = AppCache.getInstance();
				appCache.setQuestion((Question) adapter.getItem(position));
				//Single_Home_Question holder = (Single_Home_Question) view.getTag();
				//Question temp = (Question) holder.title.getTag();
				//intent.putExtra("position", position);
				//intent.putExtra("id", 0);// corrected the id confusion as requested - Sam
				startActivity(intent);
				
			}
			
		});
		lv.setAdapter(adapter);
		
		TextView tv = (TextView) findViewById(R.id.UsernameTitleTextView);
		if(AppCache.getInstance().getProfile().getUsername()==null){
			tv.setText("Guest");
		}
		else{
		tv.setText(AppCache.getInstance().getProfile().getUsername());
		}
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.profile, menu);
		super.onCreateOptionsMenu(menu);
		return true;
	}

	@Override
	protected Builder buildlogin() {
		// TODO Auto-generated method stub
	
		return super.buildlogin();
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
		adapter = new HomeAdapter(getApplicationContext(),ProfileController.getP().getSavedQuestionList());
		lv.setAdapter(adapter);
		currentView = SAVE_QUESTION_VIEW;
		
	}
	/**
	 *  when favorite button is pressed, the method will return the faved list from 
	 *  the local manager
	 *  @issue not implemented
	 */
	public void favedListButtonPressed(View view){
		adapter = new HomeAdapter(getApplicationContext(),ProfileController.getP().getFavedQuestionList());
		lv.setAdapter(adapter);
		currentView = FAVORITE_QUESTION_VIEW;
	}
	
	public void myListButtonPressed(View view){
		adapter = new HomeAdapter(getApplicationContext(),ProfileController.getP().getMyQuestionList());
		lv.setAdapter(adapter);
		currentView = MY_QUESTION_VIEW;
	}
	
	public void tempListButtonPressed(View view){
		adapter = new HomeAdapter(getApplicationContext(),ProfileController.getP().getTempQuestionList());
		lv.setAdapter(adapter);
		currentView = TEMP_QUESTION_VIEW;
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
		
	}
	
	/**
	 * Used to get current profile 
	 * @return profile hold by profile activity
	 */
	public Profile getProfile(){
		return ProfileController.getP();
	}


	@Override
	public void update(Observable observable, Object data) {
		setNewAdapter();
	}

	private void setNewAdapter() {
		if(currentView == SAVE_QUESTION_VIEW){
			adapter = new HomeAdapter(getApplicationContext(),ProfileController.getP().getSavedQuestionList());
			lv.setAdapter(adapter);
		}
		else if (currentView == FAVORITE_QUESTION_VIEW){
			adapter = new HomeAdapter(getApplicationContext(),ProfileController.getP().getFavedQuestionList());
			lv.setAdapter(adapter);
		}
		else{
			
		}
		
	}
}
