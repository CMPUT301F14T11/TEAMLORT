package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.cmput301f14t11.teamlort.Model.Question;

class Single_Profile_Question
{
	public TextView title;
	public TextView content;
	public TextView count;
	public Question thisquestion;
	Single_Profile_Question(View feed)
	{
		title = (TextView) feed.findViewById(R.id.p_qtitle);
		content = (TextView) feed.findViewById(R.id.p_qcontent);
		count = (TextView) feed.findViewById(R.id.p_qstats);
	}
}

public class ProfileAdatper extends BaseAdapter{
	
	ArrayList<Question> list;//the archived/current task currently loaded in this gridview, i could of just referenced those lists since they are global
	// but i think this makes my adapter more portable
	Context context;

	ProfileAdatper(Context context,ArrayList<Question> provided)
	{
		this.context = context; //initializing methode providing the context and the list of todoitems we will work with
		list = provided;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = convertView;
	    Single_Home_Question holder = null;
		if(row == null)
		{
			
			row = LayoutInflater.from(parent.getContext()).inflate(R.layout.profileactivity_question, parent,false);
			
			holder = new Single_Home_Question(row);
			
			row.setTag(holder);
		}
		else
		{
			holder = (Single_Home_Question) row.getTag();
		}
		//final View finalView = row;
		holder.thisquestion = list.get(position);
		holder.title.setText(list.get(position).getTitle());
		holder.content.setText(list.get(position).getBody());
		holder.count.setText("posted on "+holder.thisquestion.getTime().toString()+", "+holder.thisquestion.getAnswerList().size()+" answers");	
		
		return row;
	}
	
	public void updatelist(ArrayList<Question> provided)//refreshes the gridview
	{
		this.list = provided;
		notifyDataSetChanged(); //updates view
	}
	

}
