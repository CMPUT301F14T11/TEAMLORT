package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;


import com.github.cmput301f14t11.teamlort.Controller.ProfileController;
import com.github.cmput301f14t11.teamlort.Model.Question;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


class Single_Home_Question
{
	public TextView title;
	public TextView content;
	public TextView count;
	public ImageButton save;
	public ImageButton favorite;
	public Question thisquestion;
	Single_Home_Question(View feed)
	{
		title = (TextView) feed.findViewById(R.id.listitem_question_title);
		content = (TextView) feed.findViewById(R.id.listitem_question_desc);
		count = (TextView) feed.findViewById(R.id.listitem_question_stats);
		save = (ImageButton) feed.findViewById(R.id.listitem_question_save_button);
		favorite = (ImageButton) feed.findViewById(R.id.listitem_question_favorite_button);
		
	}
}
//this adapter was inspired by the extended baseadapter example on android developer website
//http://developer.android.com/guide/topics/ui/layout/gridview.html
class customadapter extends BaseAdapter// the adapter used for displaying items in the two gridviews from current task and archived task
{
	ArrayList<Question> da_list;//the archived/current task currently loaded in this gridview, i could of just referenced those lists since they are global
								// but i think this makes my adapter more portable
	Context context;
	
	customadapter(Context context,ArrayList<Question> provided)
	{
		this.context = context; //initializing methode providing the context and the list of todoitems we will work with
		da_list = provided;
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return da_list.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return da_list.get(position); //returns the item in position,usually used to reference a single item toggled in a gridview cell
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = convertView;
	    Single_Home_Question holder = null;
		if(row == null)
		{
			
			row = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_question, parent,false);
			
			holder = new Single_Home_Question(row);
			
			row.setTag(holder);
		}
		else
		{
			holder = (Single_Home_Question) row.getTag();
		}
		holder.thisquestion = da_list.get(position);
		holder.title.setText(da_list.get(position).getTitle());
		holder.content.setText(da_list.get(position).getBody());
		holder.count.setText("posted on "+holder.thisquestion.getTime().toString()+", "+holder.thisquestion.getAnswerList().size()+" answers");	
		holder.save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ProfileController pc = new ProfileController();
				pc.addSavedQuestion(da_list.get(position));
			}
			
		});
		
		holder.favorite.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ProfileController pc = new ProfileController();
				pc.addFavedQuestion(da_list.get(position));
			}
			
		});
		
		return row;
	}
	public void updatelist(ArrayList<Question> provided)//refreshes the gridview
	{
		this.da_list = provided;
		notifyDataSetChanged(); //updates view
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void add(Question question) {
		// TODO Auto-generated method stub
		da_list.add(question);
		
	}
}
