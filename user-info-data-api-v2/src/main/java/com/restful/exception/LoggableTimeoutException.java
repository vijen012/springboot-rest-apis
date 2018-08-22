package com.restful.exception;

public class LoggableTimeoutException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LoggableTimeoutException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoggableTimeoutException(Throwable cause) {
		super(cause);
	}
}
