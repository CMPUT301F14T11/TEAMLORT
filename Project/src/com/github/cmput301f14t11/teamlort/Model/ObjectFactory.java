package com.github.cmput301f14t11.teamlort.Model;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

import com.github.cmput301f14t11.teamlort.Model.Question;

/**
 * Allows building of {@link Question}, {@link Answer} and {@link Reply} objects.
 * 
 * @author Elvis Lo
 *
 */
public class ObjectFactory {
	
	public ObjectFactory(){
		
	}
	
	public static Question initQuestion(String title, String body, String author, GpsLocation GpsLocation){
		Question question = new Question();
		
		question.setTitle(title);
		question.setBody(body);
		question.setAuthor(author);
		question.setID();
		question.setLocation(GpsLocation);
		return question;
	}
	
	public static Question initQuestion(String title, String body, String author, GpsLocation GpsLocation, Drawable image){
		//An overload that takes in an image
		Question question = new Question();
		
		question.setTitle(title);
		question.setBody(body);
		question.setAuthor(author);
		question.addPicture(image);
		question.setID();
		question.setLocation(GpsLocation);
		
		return question;
	}
	
	public static Question initQuestion(String title, String body, String author){
		Question question = new Question();
		
		question.setTitle(title);
		question.setBody(body);
		question.setAuthor(author);
		question.setID();
		return question;
	}
	
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
	
	public static Answer initAnswer(String body, String author, GpsLocation GpsLocation){
		Answer answer = new Answer();
		
		answer.setBody(body);
		answer.setAuthor(author);
		answer.setID();
		answer.setLocation(GpsLocation);
		
		return answer;
	}
	
	public static Answer initAnswer(String body, String author){
		Answer answer = new Answer();
		
		answer.setBody(body);
		answer.setAuthor(author);
		answer.setID();
		
		return answer;
	}
	
	public static Reply initReply(String body, String author, GpsLocation GpsLocation)
	{
		Reply reply = new Reply();
		
		reply.setBody(body);
		reply.setAuthor(author);
		reply.setID();
		reply.setLocation(GpsLocation);
		
		return reply;
	}
	
	public static Reply initReply(String body, String author)
	{
		Reply reply = new Reply();
		
		reply.setBody(body);
		reply.setAuthor(author);
		reply.setID();
		
		return reply;
	}
}
