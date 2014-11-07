package com.github.cmput301f14t11.teamlort;

import android.R.string;

/**
 * Generates Json query commands for {@link ElasticManager};
 * 
 * @Issues: Not implemented.
 * 
 * @author Elvis Lo
 *
 */
public class CommandGenerator {

	/**
	 * @param question The {@link Question} to push.
	 * @return {@link String} query formatted for pushing {@link Question} to Elastic Search.
	 */
	public String getPushQuestionCommand(Question question){
		//TODO stub
		return null;
	}

	/**
	 * @param answer The {@link Answer} to push.
	 * @param questionID The id of the {@link Question} the {@link Answer} to be pushed is under.
	 * @return {@link String} query formatted for pushing an {@link Answer} to Elastic Search.
	 */
	public String getPushAnswerCommand(Answer answer, int questionID){
		//TODO stub
		return null;
	}

	/**
	 * @param reply The {@link Reply} to push.
	 * @param questionID The id of the {@link Question} the {@link Reply} to be pushed is under.
	 * @return {@link String} query formatted for pushing a {@link Reply} under a 
	 * {@link Question} to Elastic Search.
	 */
	public String getPushQuestionReplyCommand(Reply reply, int questionID){
		//TODO stub
		return null;
	}

	/**
	 * @param reply The {@link Reply} to push.
	 * @param questionID The id of the {@link Question} the {@link Reply} to be pushed is under.
	 * @param answerID The id of the {@link Answer} the {@link Reply} to be pushed is under.
	 * @return {@link String} query formatted for pushing a {@link Reply} under a 
	 * {@link Answer} which itself is under a {@link Question} to Elastic Search.
	 */
	public String getPushAnswerReplyCommand(Reply reply, int questionID, int answerID){
		//TODO stub
		return null;
	}


	/**
	 * @return {@link String} query formatted for requesting a {@link Question} list from 
	 * Elastic Search by date.
	 */
	public String getRequestQuestionsByDateCommand(){
		//TODO stub
		return null;
	}

	/**
	 * @return {@link String} query formatted for requesting a {@link Question} list from 
	 * Elastic Search by if question has image.
	 */
	public String getRequestQuestionsByImgCommand(){
		//TODO stub
		return null;
	}
	
	/**
	 * @return {@link String} query formatted for requesting a {@link Question} list from 
	 * Elastic Search by upvote.
	 */
	public String getRequestQuestionsByUpvoteCommand(){
		//TODO stub
		return null;
	}
	
	/**
	 * @param id The {@link Question} id.
	 * @return {@link String} query formatted for requesting a {@link Question} from 
	 * Elastic Search by ID.
	 */
	public String getRequestQuestionCommand(int id){
		//TODO stub
		return null;
	}

}
