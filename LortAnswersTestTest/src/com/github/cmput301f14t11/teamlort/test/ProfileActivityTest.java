package com.github.cmput301f14t11.teamlort.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.github.cmput301f14t11.teamlort.ProfileActivity;

public class ProfileActivityTest extends ActivityInstrumentationTestCase2<ProfileActivity>{
	public ProfileActivityTest() {
		// TODO Auto-generated constructor stub
		super(ProfileActivity.class);
	}
	
	public void testDispalyAlertDialog(){
		ProfileActivity activity = (ProfileActivity) getActivity();
		ImageButton setUsernameButton = (ImageButton) activity.findViewById(com.github.cmput301f14t11.teamlort
					.R.id.imageButton1);
		setUsernameButton.performClick();
	}
	
	public void testEnterUserName(){
		ProfileActivity activity = (ProfileActivity) getActivity();
		ImageButton setUsernameButton = (ImageButton) activity.findViewById(com.github.cmput301f14t11.teamlort
					.R.id.imageButton1);
		setUsernameButton.performClick();
		TextView tv = (TextView) activity.findViewById(com.github.cmput301f14t11.teamlort.R.id.UsernameTitleTextView);
		assertTrue("empty string should be set", tv.getText().toString() == "");
	}
	
	public void testFavedButtonPressed(){
		ProfileActivity activity = (ProfileActivity) getActivity();
		ImageButton favedButton = (ImageButton) activity.findViewById(com.github.cmput301f14t11.teamlort
				.R.id.favorite_button);
		ListView lv = (ListView) activity.findViewById(com.github.cmput301f14t11.teamlort
				.R.id.ProfileQuestionListView);
		favedButton.performClick();
		int size = lv.getAdapter().getCount();
		for (int i = 0; i < size; i++){
			assertTrue("F:index"+i+"is not matching",lv.getAdapter().getItem(i) 
					== activity.getProfile().getFavedQuestionList().get(i));
		}
	}
	
	public void testSavedButtonPressed(){
		ProfileActivity activity = (ProfileActivity) getActivity();
		ImageButton favedButton = (ImageButton) activity.findViewById(com.github.cmput301f14t11.teamlort
				.R.id.save_button);
		ListView lv = (ListView) activity.findViewById(com.github.cmput301f14t11.teamlort
				.R.id.ProfileQuestionListView);
		favedButton.performClick();
		int size = lv.getAdapter().getCount();
		for (int i = 0; i < size; i++){
			assertTrue("S:index"+i+"is not matching",lv.getAdapter().getItem(i) 
					== activity.getProfile().getSavedQuestionList().get(i));
		}
	}
	
	public void testMyButtonPressed(){
		ProfileActivity activity = (ProfileActivity) getActivity();
		ListView lv = (ListView) activity.findViewById(com.github.cmput301f14t11.teamlort
				.R.id.ProfileQuestionListView);
		int size = lv.getAdapter().getCount();
		for (int i = 0; i < size; i++){
			assertTrue("M:index"+i+"is not matching",lv.getAdapter().getItem(i) 
					== activity.getProfile().getMyQuestionList().get(i));
		}
	}
	

}
