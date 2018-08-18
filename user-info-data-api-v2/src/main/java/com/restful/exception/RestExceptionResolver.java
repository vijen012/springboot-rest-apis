package com.restful.exception;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class RestExceptionResolver extends ResponseEntityExceptionHandler {

	@Autowired
	private Logger exceptionLogger;

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		exceptionLogger.error("Exception: " + ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				ex.getMessage(), request.getDescription(false), new Date());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		exceptionLogger.error("Exception: " + ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				request.getDescription(false), new Date());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException ex, WebRequest request) {
		exceptionLogger.error("Exception: " + ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				request.getDescription(false), new Date());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ ResourceNotFoundException.class, UserNotFoundException.class, AddressNotFoundException.class })
	public final ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
		exceptionLogger.error("Exception: " + ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				request.getDescription(false), new Date());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		exceptionLogger.error("Exception: " + ex);
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		String message = processFieldErrors(fieldErrors);
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), message, message,
				new Date());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		exceptionLogger.error("Exception: " + ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				request.getDescription(false), new Date());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		exceptionLogger.error("Exception: " + ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				request.getDescription(false), new Date());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		exceptionLogger.error("Exception: " + ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.METHOD_NOT_ALLOWED.value(),
				ex.getMessage(), request.getDescription(false), new Date());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
	}

	private String processFieldErrors(List<FieldError> fieldErrors) {
		String message = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
		return message;
	}
}