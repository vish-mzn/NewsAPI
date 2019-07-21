package com.stackroute.favouriteservice.exception;

@SuppressWarnings("serial")
public class NewsNotFoundException extends Exception{
	
	public String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NewsNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "MovieNotFoundException [message=" + message + "]";
	}

}

