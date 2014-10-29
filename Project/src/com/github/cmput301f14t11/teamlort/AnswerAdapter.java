package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class AnswerAdapter extends BaseAdapter {

	AnswerViewHolder viewHolder;
	
	private ArrayList<Answer> answerList;
	private Context context;
	
	static class AnswerViewHolder{
		//Stores views for an answer
		TextView answer1;
		ImageButton answer_action_reply;
		ImageButton answer_action_overflow;
		TextView answer_author;
		TextView answer_stats_1;
	}
	
	public AnswerAdapter(ArrayList<Answer> answerList, Context context) {
		this.answerList = answerList;
		this.context = context;
	}

	@Override
	public int getCount() {
		return answerList.size();
	}

	@Override
	public Object getItem(int position) {
		return answerList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			//Inflate the view
			LayoutInflater layoutInflater = LayoutInflater.from(context);
			convertView = layoutInflater.inflate(R.layout.question_view_answer_item, parent, false);
			
			//Put views in viewHolder
			viewHolder = new AnswerViewHolder();
			viewHolder.answer1 = (TextView) convertView.findViewById(R.id.answer1);
			viewHolder.answer_action_reply = (ImageButton) convertView.findViewById(R.id.answer_action_reply);
			viewHolder.answer_action_overflow = (ImageButton) convertView.findViewById(R.id.answer_action_overflow);
			viewHolder.answer_author = (TextView) convertView.findViewById(R.id.answer_author);
			viewHolder.answer_stats_1 = (TextView) convertView.findViewById(R.id.answer_stats_1);
			
			convertView.setTag(viewHolder);
			
		} else {
			//Recycle view information in viewHolder
			viewHolder = (AnswerViewHolder) convertView.getTag();
		}
		Answer answer = answerList.get(position);
		
		//Set the text for the view if answer isn't null
		if (answer != null){
			viewHolder.answer1.setText(answer.getBody());
			viewHolder.answer_author.setText(answer.getAuthor());
			viewHolder.answer_stats_1.setText(answer.getTime().toString());
		}
		
		viewHolder.answer_action_reply.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		viewHolder.answer_action_overflow.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		return convertView;
	}

}
