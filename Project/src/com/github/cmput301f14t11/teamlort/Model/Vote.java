package com.github.cmput301f14t11.teamlort.Model;

import android.content.Context;

public class Vote {
	static ElasticManager em = ElasticManager.getInstance();
	
	static void sendVote(Answer answer, Question question, Context context) {
		if(NetworkListener.checkConnection(context)) {
			em.addItem(question);
		}
	}
}
