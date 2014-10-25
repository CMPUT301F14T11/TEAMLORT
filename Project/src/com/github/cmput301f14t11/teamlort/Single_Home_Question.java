package com.github.cmput301f14t11.teamlort;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class Single_Home_Question
{
	TextView title;
	TextView content;
	Button save;
	Button favorite;
	Single_Home_Question(View feed)
	{
		title = (TextView) feed.findViewById(R.id.listitem_question_title);
		content = (TextView) feed.findViewById(R.id.listitem_question_desc);
		save = (Button) feed.findViewById(R.id.listitem_question_save_button);
		favorite = (Button) feed.findViewById(R.id.listitem_question_favorite_button);
	}
}