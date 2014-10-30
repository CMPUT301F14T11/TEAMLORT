package com.github.cmput301f14t11.teamlort;

import java.util.Date;

/**
 * @author  eunderhi
 */
public class TextPrimitive {
	/**
	 * @uml.property  name="body"
	 */
	private String body;
	/**
	 * @uml.property  name="author"
	 */
	private String author;
	/**
	 * @uml.property  name="time"
	 */
	private Date time = new Date();
	private int ID;
	
	/**
	 * @return
	 * @uml.property  name="body"
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @return
	 * @uml.property  name="author"
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @return
	 * @uml.property  name="time"
	 */
	public Date getTime() {
		return time;
	}
	/**
	 * @param newBody
	 * @uml.property  name="body"
	 */
	public void setBody(String newBody) {
		body = newBody;
	}
	/**
	 * @param newAuthor
	 * @uml.property  name="author"
	 */
	public void setAuthor(String newAuthor) {
		author = newAuthor;
	}
	public int getID() {
		return ID;
	}
	public void setID() {
		ID = this.hashCode();
	}
}
