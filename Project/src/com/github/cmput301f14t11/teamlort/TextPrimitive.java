package com.github.cmput301f14t11.teamlort;

import java.util.Date;

public class TextPrimitive {
	private String body;
	private String author;
	private Date time;

	public TextPrimitive() {
		time = new Date();
	}
	
	public String getBody() {
		return body;
	}
	public String getAuthor() {
		return author;
	}
	public Date getTime() {
		return time;
	}
	public void setBody(String newBody) {
		body = newBody;
	}
	public void setAuthor(String newAuthor) {
		author = newAuthor;
	}
}
