package com.github.cmput301f14t11.teamlort.Model;

public class PushItem {

	TextPrimitive pushItem;
	int questionID;
	int answerID;
	
	public PushItem(TextPrimitive pushItem) {
		this.pushItem = pushItem;
	}
	
	public PushItem(TextPrimitive pushItem, int questionID) {
		this.pushItem = pushItem;
		this.questionID = questionID;
	}

	public PushItem(TextPrimitive pushItem, int questionID, int answerID) {
		this.pushItem = pushItem;
		this.questionID = questionID;
		this.answerID = answerID;
	}

	public TextPrimitive getPushItem() {
		return pushItem;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public int getAnswerID() {
		return answerID;
	}

}
