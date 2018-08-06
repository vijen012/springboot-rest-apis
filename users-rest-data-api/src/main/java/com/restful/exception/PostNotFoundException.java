package com.restful.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Requested post is not found", value = HttpStatus.NOT_FOUND)
public class PostNotFoundException extends ResourceNotFoundException {
	private static final long serialVersionUID = 1L;

	public PostNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
