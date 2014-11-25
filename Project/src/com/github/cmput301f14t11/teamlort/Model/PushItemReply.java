package com.github.cmput301f14t11.teamlort.Model;

public class PushItemReply {

	Reply reply;
	int questionID;
	int answerID;
	
	public PushItemReply(Reply reply, int questionID) {
		this.reply = reply;
		this.questionID = questionID;
	}

	public PushItemReply(Reply reply, int questionID, int answerID) {
		this.reply = reply;
		this.questionID = questionID;
		this.answerID = answerID;
	}

	public Reply getPushItem() {
		return reply;
	}

	public int getQuestionID(int questionID) {
		return questionID;
	}

	public int getAnswerID() {
		return answerID;
	}                  

}
