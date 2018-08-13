package com.restful.exception;

import java.util.Date;

public class ExceptionResponse {
	private int statusCode;
	private String message;
	private String details;
	private Date timestamp;
	
	public ExceptionResponse(int statusCode, String message, String details, Date timestamp) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.details = details;
		this.timestamp = timestamp;
	}

	public int getStatusCode() {
		return statusCode;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getdetails() {
		return details;
	}	
	
	public Date getTimestamp() {
		return timestamp;
	}
	
}
