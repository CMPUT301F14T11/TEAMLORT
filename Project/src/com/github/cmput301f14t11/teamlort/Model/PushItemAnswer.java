package com.github.cmput301f14t11.teamlort.Model;

public class PushItemAnswer {

	Answer answer;
	int questionID;
	
	public PushItemAnswer(Answer answer, int questionID) {
		this.answer = answer;
		this.questionID = questionID;
	}

	public Answer getPushItem() {
		return answer;
	}
	
	public int getQuestionID() {
		return questionID;
	}

}
