package com.github.cmput301f14t11.teamlort.Model;

import java.util.Date;

/**
 * @author  eunderhi
 */

/**
 * The base text object class that all others
 * (Answer, question, reply) all inherit from
 * contains the base functionality
 */
public class TextPrimitive 
implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String body;
	
	private GpsLocation GpsLocation = null;

	
	/**
	 * @uml.property  name="author"
	 */
	private String author;
	
	private Date time = new Date();
	private int ID;
	
	/**
	 * @return the string of text of the reply/question/answer
	 * 
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @return the author of the object
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @return the date it was made
	 */
	public Date getTime() {
		return time;
	}
	/**
	 * @param newBody the text body of the object
	 */
	public void setBody(String newBody) {
		body = newBody;
	}
	/**
	 * @param newAuthor the author of the question
	 */
	
	public void setAuthor(String newAuthor) {
		author = newAuthor;
	}
	
	
	public int getID() {
		return ID;
	}
	/**
	 * ID's are set to the hash of the object when called
	 * */
	public void setID() {
		ID = this.hashCode();
	}
	
	public void setLocation(GpsLocation GpsLocation) {
		this.GpsLocation = GpsLocation;
	}
	
	public GpsLocation getLocation() {
		return GpsLocation;
	}
}
