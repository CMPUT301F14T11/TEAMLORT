package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;

import com.github.cmput301f14t11.teamlort.AnswerAdapter.ReplyViewHolder;
import com.github.cmput301f14t11.teamlort.Model.Reply;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Custom {@link Adapter} for an {@link ListView} containing {@link Reply} objects.
 * 
 * @author Elvis Lo
 */
public class ReplyAdapter extends BaseAdapter {
	
	private ReplyViewHolder replyViewHolder;
	
	private ArrayList<Reply> replyList;
	private Context context;
	
	/**
	 * ViewHolder for {@link Reply} view elements.
	 * 
	 * @author Elvis Lo
	 * 
	 * @Credit ViewHolder pattern from:
	 *  http://developer.android.com/training/improving-layouts/smooth-scrolling.html
	 */
	static class ReplyViewHolder{
		//Stores views for an reply
		TextView reply1;
		TextView reply_author;
		TextView reply_time;
		TextView geolocation;
	}
	
	/**
	 * {@link ReplyAdapter} constructor.
	 * 
	 * @param replyList The {@link ArrayList}<{@link Reply} > the adapter will adapt views for.
	 * @param context The {@link Activity} {@link Context}.
	 */
	public ReplyAdapter(ArrayList<Reply> replyList, Context context) {
		this.replyList = replyList;
		this.context = context;
	}

	@Override
	public int getCount() {
		return replyList.size();
	}

	@Override
	public Object getItem(int position) {
		return replyList.get(position);
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
			convertView = layoutInflater.inflate(R.layout.question_view_reply_item, parent, false);
			
			//Put views in viewHolder
			replyViewHolder = new ReplyViewHolder();
			replyViewHolder.reply1 = (TextView) convertView.findViewById(R.id.reply1);
			replyViewHolder.reply_author = (TextView) convertView.findViewById(R.id.reply_author);
			replyViewHolder.reply_time = (TextView) convertView.findViewById(R.id.reply_time);
			replyViewHolder.geolocation = (TextView) convertView.findViewById(R.id.location_text_view_reply);
			
			convertView.setTag(replyViewHolder);
			
		} else {
			//Recycle view information in viewHolder
			replyViewHolder = (ReplyViewHolder) convertView.getTag();
		}
		Reply reply = replyList.get(position);
		
		//Set the text for the view if answer isn't null
		if (reply != null){
			replyViewHolder.reply1.setText(reply.getBody());
			replyViewHolder.reply_author.setText(reply.getAuthor());
			//replyViewHolder.geolocation.setText(reply.getLocation().getLatitude() + "¡, "+ reply.getLocation().getLongitude() + "¡");

		}
		
		return convertView;
	}

}
