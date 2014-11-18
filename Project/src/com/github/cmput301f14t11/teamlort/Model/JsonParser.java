package com.github.cmput301f14t11.teamlort.Model;

import java.util.ArrayList;

import android.R.string;

/**
 * Class for parsing results from queries.
 * 
 * @author Elvis Lo
 */
public class JsonParser {

	/**
	 * @param queryResult The {@link string} result from a getQuestions query.
	 * @return An {@link ArrayList}<{@link Question}> parsed from queryResult.
	 */
	public ArrayList<Question> parseQuestionsQueryResult(String queryResult){
		//TODO stub
		return null;
	}
	
	/**
	 * @param queryResult The {@link string} result from a getSingleQuestion query.
	 * @return An {@link ArrayList}<{@link Question}> parsed from queryResult.
	 */
	public Question parseSingleQuestionQueryResult(String queryResult){
		//TODO stub
		return null;
	}

}
