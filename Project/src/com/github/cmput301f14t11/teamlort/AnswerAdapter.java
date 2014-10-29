package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class AnswerAdapter extends BaseAdapter {

	static class AnswerViewHolder{
		//Stores views for an answer
		// TODO set up view holder
	}
	
	private ArrayList<Answer> answerList;
	
	public AnswerAdapter() {
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		return null;
	}

}
