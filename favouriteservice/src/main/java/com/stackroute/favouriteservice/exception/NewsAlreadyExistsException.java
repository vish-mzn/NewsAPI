package com.stackroute.favouriteservice.exception;

@SuppressWarnings("serial")
public class NewsAlreadyExistsException extends Exception{

	String message;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public NewsAlreadyExistsException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "MovieAlreadyExistsException [message=" + message + "]";
	}
	
}
