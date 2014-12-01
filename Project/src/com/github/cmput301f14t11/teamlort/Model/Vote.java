package com.github.cmput301f14t11.teamlort.Model;

import android.content.Context;
import android.widget.Toast;

public class Vote {
	
	public static void sendVote(Question question, Context context) {
		if(NetworkListener.checkConnection(context)) {
			ElasticManager.getInstance().addItem(question);
		}
		Toast.makeText(context, "No connection vote won't be savede", Toast.LENGTH_SHORT).show();
	}
}
