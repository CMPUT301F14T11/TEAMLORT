package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class AnswerAdapter extends BaseExpandableListAdapter {

	private AnswerViewHolder answerViewHolder;
	private ReplyViewHolder replyViewHolder;
	
	private ArrayList<Answer> answerList;
	private Context context;
	
	static class AnswerViewHolder{
		//Stores views for an answer
		TextView answer1;
		ImageButton answer_action_reply;
		ImageButton answer_action_overflow;
		TextView answer_author;
		TextView answer_stats_1;
		TextView answer_comment_count;
	}
	
	static class ReplyViewHolder{
		//Stores views for an reply
		TextView reply1;
		TextView reply_author;
		TextView reply_time;
	}
	
	public AnswerAdapter(ArrayList<Answer> answerList, Context context) {
		this.answerList = answerList;
		this.context = context;
	}

	@Override
	public int getGroupCount() {
		return answerList.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		//Note: A get size in answer could make this cleaner.
		return answerList.get(groupPosition).getReplyList().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return answerList.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return answerList.get(groupPosition).getReply(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		//NOTE: Not 100% on if this should be true.
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if(convertView == null){
			//Inflate the view
			LayoutInflater layoutInflater = LayoutInflater.from(context);
			convertView = layoutInflater.inflate(R.layout.question_view_answer_item, parent, false);
			
			//Put views in viewHolder
			answerViewHolder = new AnswerViewHolder();
			answerViewHolder.answer1 = (TextView) convertView.findViewById(R.id.answer1);
			answerViewHolder.answer_action_reply = (ImageButton) convertView.findViewById(R.id.answer_action_reply);
			answerViewHolder.answer_action_overflow = (ImageButton) convertView.findViewById(R.id.answer_action_overflow);
			answerViewHolder.answer_author = (TextView) convertView.findViewById(R.id.answer_author);
			answerViewHolder.answer_stats_1 = (TextView) convertView.findViewById(R.id.answer_stats_1);
			answerViewHolder.answer_comment_count = (TextView) convertView.findViewById(R.id.answer_comment_count_textview);
			
			convertView.setTag(answerViewHolder);
			
		} else {
			//Recycle view information in viewHolder
			answerViewHolder = (AnswerViewHolder) convertView.getTag();
		}
		Answer answer = answerList.get(groupPosition);
		
		//Set the text for the view if answer isn't null
		if (answer != null){
			answerViewHolder.answer1.setText(answer.getBody());
			answerViewHolder.answer_author.setText(answer.getAuthor());
			answerViewHolder.answer_stats_1.setText(answer.getTime().toString());
			answerViewHolder.answer_comment_count.setText(String.valueOf(answer.getReplyList().size()) + " comments");
			}
		
		/*//These appear to be broken right now
		answerViewHolder.answer_action_reply.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		answerViewHolder.answer_action_overflow.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
		*/
		
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		if(convertView == null){
			//Inflate the view
			LayoutInflater layoutInflater = LayoutInflater.from(context);
			convertView = layoutInflater.inflate(R.layout.question_view_reply_item, parent, false);
			
			//Put views in viewHolder
			replyViewHolder = new ReplyViewHolder();
			replyViewHolder.reply1 = (TextView) convertView.findViewById(R.id.reply1);
			replyViewHolder.reply_author = (TextView) convertView.findViewById(R.id.reply_author);
			replyViewHolder.reply_time = (TextView) convertView.findViewById(R.id.reply_time);
			
			convertView.setTag(replyViewHolder);
			
		} else {
			//Recycle view information in viewHolder
			replyViewHolder = (ReplyViewHolder) convertView.getTag();
		}
		Answer answer = answerList.get(groupPosition);
		Reply reply = answer.getReply(childPosition);
		
		//Set the text for the view if answer isn't null
		if (reply != null){
			replyViewHolder.reply1.setText(reply.getBody());
			replyViewHolder.reply_author.setText(reply.getAuthor());
			replyViewHolder.reply_time.setText(reply.getTime().toString());
		}
		
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
