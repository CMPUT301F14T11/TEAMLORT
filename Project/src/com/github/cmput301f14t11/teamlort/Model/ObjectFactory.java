package com.github.cmput301f14t11.teamlort.Model;

import android.graphics.drawable.Drawable;

/**
 * Allows building of {@link Question}, {@link Answer} and {@link Reply} objects.
 * 
 * @author Elvis Lo
 *
 */
public class ObjectFactory {
	
	public ObjectFactory(){
		
	}
	
	/**
	 * Usage: eg. Question question = initQuestion("A Title", "A text body", "Author", {@link GpsLocation});
	 * 
	 * @param title
	 * @param body
	 * @param author
	 * @param GpsLocation
	 * @return
	 */
	public static Question initQuestion(String title, String body, String author, GpsLocation GpsLocation){
		Question question = new Question();
		
		question.setTitle(title);
		question.setBody(body);
		question.setAuthor(author);
		question.setLocation(GpsLocation);
		
		question.setID();
		return question;
	}
	
	/**
	 * Usage: eg. Question question = initQuestion("A Title", "A text body", "Author", {@link GpsLocation}, {@link Drawable});
	 * 
	 * @param title
	 * @param body
	 * @param author
	 * @param GpsLocation
	 * @return
	 */
	public static Question initQuestion(String title, String body, String author, GpsLocation GpsLocation, Drawable image){
		//An overload that takes in an image
		Question question = new Question();
		
		question.setTitle(title);
		question.setBody(body);
		question.setAuthor(author);
		question.addPicture(image);
		question.setLocation(GpsLocation);
		
		question.setID();
		return question;
	}
	
	/**
	 * Usage: eg. Question question = initQuestion("A Title", "A text body", "Author");
	 * 
	 * @param title
	 * @param body
	 * @param author
	 * @param GpsLocation
	 * @return
	 */
	public static Question initQuestion(String title, String body, String author){
		Question question = new Question();
		
		question.setTitle(title);
		question.setBody(body);
		question.setAuthor(author);
		
		question.setID();
		return question;
	}
	
	/**
	 * Usage: eg. Question question = initQuestion("A Title", "A text body", "Author", {@link GpsLocation});
	 * 
	 * @param title
	 * @param body
	 * @param author
	 * @param GpsLocation
	 * @return
	 */
	public static Question initQuestion(String title, String body, String author, Drawable image){
		//An overload that takes in an image
		Question question = new Question();
		
		question.setTitle(title);
		question.setBody(body);
		question.setAuthor(author);
		question.addPicture(image);
		
		question.setID();
		return question;
	}
	
	/**
	 * Usage: eg. Answer answer = initAnswer("A text body", "Author", {@link GpsLocation});
	 * 
	 * @param title
	 * @param body
	 * @param author
	 * @param GpsLocation
	 * @return
	 */
	public static Answer initAnswer(String body, String author, GpsLocation GpsLocation){
		Answer answer = new Answer();
		
		answer.setBody(body);
		answer.setAuthor(author);
		answer.setLocation(GpsLocation);

		answer.setID();
		return answer;
	}
	
	/**
	 * Usage: eg. Answer answer = initAnswer("A text body", "Author");
	 * 
	 * @param title
	 * @param body
	 * @param author
	 * @param GpsLocation
	 * @return
	 */
	public static Answer initAnswer(String body, String author){
		Answer answer = new Answer();
		
		answer.setBody(body);
		answer.setAuthor(author);
		
		answer.setID();
		return answer;
	}
	
	/**
	 * Usage: eg. Reply reply = initReply("A text body", "Author", {@link GpsLocation});
	 * 
	 * @param title
	 * @param body
	 * @param author
	 * @param GpsLocation
	 * @return
	 */
	public static Reply initReply(String body, String author, GpsLocation GpsLocation)
	{
		Reply reply = new Reply();
		
		reply.setBody(body);
		reply.setAuthor(author);
		reply.setLocation(GpsLocation);
		
		reply.setID();
		return reply;
	}
	
	/**
	 * Usage: eg. Reply reply = initReply("A text body", "Author");
	 * 
	 * @param title
	 * @param body
	 * @param author
	 * @param GpsLocation
	 * @return
	 */
	public static Reply initReply(String body, String author)
	{
		Reply reply = new Reply();
		
		reply.setBody(body);
		reply.setAuthor(author);
		
		reply.setID();		
		return reply;
	}
}
