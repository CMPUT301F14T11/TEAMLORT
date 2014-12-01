package com.github.cmput301f14t11.teamlort.Model;

import android.content.Context;
import android.widget.Toast;

public class Vote {
	
	public static void sendVote(Context context, Question question) {
		if(NetworkListener.checkConnection(context)) {
			PushQueue.getInstance().pushQuestion(question, context);
		}
		else {
			Toast.makeText(context, "No connection vote won't be saved", Toast.LENGTH_SHORT).show();
		}
	}
}
