package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.location.LocationManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.cmput301f14t11.teamlort.Controller.LocationController;
import com.github.cmput301f14t11.teamlort.Controller.QuestionController;
import com.github.cmput301f14t11.teamlort.Model.Answer;
import com.github.cmput301f14t11.teamlort.Model.AppCache;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.PushQueue;
import com.github.cmput301f14t11.teamlort.Model.Question;
import com.github.cmput301f14t11.teamlort.Model.Reply;
import com.github.cmput301f14t11.teamlort.Model.Vote;

/**
 * Custom {@link Adapter} for an {@link ExpandableListView} containing {@link Answer} groups each with 
 * an expandable list of {@link Reply} objects. Extends {@link BaseExpandableListAdapter}.
 * <br><br>
 * After initializing, this adapter should be set to an {@link ExpandableListView}. <br>
 * <br>
 * eg. <br>
 * AnswerAdapter answerAdapter = new AnswerAdapter(answerList, context, question) <br>
 * ExpandableListView listview.setAdapter(answerAdapter)
 * 
 * @author Elvis Lo
 */
public class AnswerAdapter extends BaseExpandableListAdapter {

	private AnswerViewHolder answerViewHolder;
	private ReplyViewHolder replyViewHolder;
	
	private Question question;
	private ArrayList<Answer> answerList;
	private Context context;
	private QuestionController questionController;
	private AppCache ac = new AppCache();
	
	/**
	 * ViewHolder for {@link Answer} view elements, one way of utilizing is by using 
	 * {@link android.view.View#setTag(Object tag) setTag(AnswerViewHolder)} on
	 * a convertView then retrieving the tag by {@link android.view.View#getTag(Object tag) getTag(AnswerViewHolder)}. 
	 * <br><br>
	 * Used to prevent unnecessary calls of {@link android.view.View#findViewById(int id)} and allow for smooth scrolling.
	 * 
	 * @author Elvis Lo
	 * 
	 * @Credit ViewHolder pattern from:
	 *  http://developer.android.com/training/improving-layouts/smooth-scrolling.html
	 */
	static class AnswerViewHolder{
		//Stores views for an answer
		TextView answer1;
		ImageButton answer_action_reply;
		ImageButton answer_action_overflow;
		TextView answer_author;
		TextView answer_stats_1;
		TextView answer_comment_count;
		Button upvoteButton;
		TextView geolocation;
		Button viewAnswerItemImageButton;
		
	}
	
	/**
	 * ViewHolder for {@link Reply} view elements, one way of utilizing is by using 
	 * {@link android.view.View#setTag(Object tag) setTag(ReplyViewHolder)} on
	 * a convertView then retrieving the tag by {@link android.view.View#getTag(Object tag) getTag(ReplyViewHolder)}. 
	 * <br><br>
	 * Used to prevent unnecessary calls of {@link android.view.View#findViewById(int id)} and allow for smooth scrolling.
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
	 * {@link AnswerAdapter} constructor.
	 * 
	 * @param answerList The {@link ArrayList}<{@link Answer}> the adapter will adapt views for.
	 * @param context The {@link Activity} {@link Context}.
	 */
	public AnswerAdapter(ArrayList<Answer> answerList, Context context, Question question) {
		this.answerList = answerList;
		this.context = context;
		this.question = question;
		questionController = new QuestionController(context);
		questionController.setQuestion(question);
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
			answerViewHolder.upvoteButton = (Button) convertView.findViewById(R.id.upvoteButton);
			answerViewHolder.upvoteButton.setBackgroundColor(Color.GRAY);
			answerViewHolder.geolocation = (TextView) convertView.findViewById(R.id.location_text_view_answer);
			answerViewHolder.viewAnswerItemImageButton = (Button) convertView.findViewById(R.id.viewAnswerItemImageButton);
			
			convertView.setTag(answerViewHolder);
			
		} else {
			//Recycle view information in viewHolder
			answerViewHolder = (AnswerViewHolder) convertView.getTag();
		}
		final Answer answer = answerList.get(groupPosition);
		final String username = AppCache.getInstance().getProfile().getUsername();
		final int finalGroupPosition = groupPosition;
		
		//Set the text for the view if answer isn't null
		if (answer != null){
			answerViewHolder.answer1.setText(answer.getBody());
			answerViewHolder.answer_author.setText(answer.getAuthor());
			answerViewHolder.answer_stats_1.setText(answer.getTime().toString());
			answerViewHolder.answer_comment_count.setText(String.valueOf(answer.getReplyList().size()) + " comments");
			answerViewHolder.upvoteButton.setText(String.valueOf(answer.getScore()));
			if (answer.getLocation() != null) {
				answerViewHolder.geolocation.setText(answer.printCoordinates());
			}
			
			if(answer.getVoterSet().contains(username)){
				answerViewHolder.upvoteButton.setBackgroundColor(Color.GREEN);
			}
			else {
				answerViewHolder.upvoteButton.setBackgroundColor(Color.GRAY);
			}
			
			if (!answer.hasPicture()){
				answerViewHolder.viewAnswerItemImageButton.setVisibility(View.GONE);
			} else {
				answerViewHolder.viewAnswerItemImageButton.setVisibility(View.VISIBLE);
			}
			
			answer.setListener(new Listener(){

				@Override
				public void update() {
					notifyDataSetChanged();
				}
				
			});
			
			final View finalConvertView = convertView;
			final QuestionViewActivity parentActivity = (QuestionViewActivity) context;
			/**
			 * Listens to upvote button and upvotes and unvotes answers according to the
			 * user's username. 
			 * 
			 * Checks if the user has voted the question already, and unvotes it if so
			 * else, upvotes it.
			 * 
			 * For example: if someone has upvoted an answer already, and clicks upvote again
			 * the upvote is removed. If they have not upvoted it, then the upvote is added
			 * and they are saved as a voter of the answer
			 */
			answerViewHolder.upvoteButton.setOnClickListener( new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					if(answer.getVoterSet().contains(username)){
						questionController.unVoteAnswer(username, finalGroupPosition);
					}
					else {
						questionController.upVoteAnswer(username, finalGroupPosition);
					}
					
				}
			});
				
		}
		
			answerViewHolder.answer_action_reply.setOnClickListener(new View.OnClickListener(){
	
				@Override
				public void onClick(View v) {
					//Build a dialogue for entering a new reply.
					AlertDialog.Builder alertDialogueBuilder = new AlertDialog.Builder(context);
					alertDialogueBuilder.setCancelable(true);
					alertDialogueBuilder.setTitle("Enter a reply:");
					
					//Text view for user to enter reply into
					final EditText body = new EditText(context);
					alertDialogueBuilder.setView(body);
					
					alertDialogueBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							AppCache appCache = AppCache.getInstance();
							Reply reply = ObjectFactory.initReply(body.getText().toString(), appCache.getProfile().getUsername());
							questionController.addAnswerReply(reply, finalGroupPosition);
						}
					});
					alertDialogueBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface arg0, int arg1) {

						}	
						
					});
					
					alertDialogueBuilder.show();
					
				}
				
			});	
			
			answerViewHolder.viewAnswerItemImageButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (answer.hasPicture()){
						ImagePopup.showPopup(answer.getPicture(), context).show();
					} 
				}
			});
		
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
			replyViewHolder.geolocation = (TextView) convertView.findViewById(R.id.location_text_view_reply);
			
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
			if (reply.getLocation() != null) {
				replyViewHolder.geolocation.setText(reply.printCoordinates());
			}
		}
		
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	private boolean attachLocation() {
		return ac.getProfile().getLocationService();
	}
	
	private void findLocation() {
		LocationManager lm = (LocationManager) context.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		AppCache.getInstance().getProfile().getLocation(lm);
	}

}
