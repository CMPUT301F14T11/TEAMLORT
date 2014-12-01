package com.github.cmput301f14t11.teamlort.Model;

import android.content.Context;
import android.widget.Toast;
/**
 * 
 * @author emmett
 * Vote class that pushes vote changes of an answer to
 * cloud. Ex. When a user upvotes a question, that information
 * is sent to elasticSearch. 
 */
public class Vote {
	/**
	 * 
	 * @param context The context of the questionViewActivtiy the answer belongs to
	 * @param question the question the answer belongs to
	 * This method checks to make sure the user has connection and then 
	 * pushes the new question (with answer upvoted) to elasticManager
	 */
	public static void sendVote(Context context, Question question) {
		if(NetworkListener.checkConnection(context)) {
			PushQueue.getInstance().pushQuestion(question, context);
		}
		else {
			Toast.makeText(context, "No connection vote won't be saved", Toast.LENGTH_SHORT).show();
		}
	}
}
