package com.backlink.controller.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.backlink.Message.MessageException;
import com.backlink.exception.ApiError;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST, MessageException.INCORRECT_SYNTAX);
		return new ResponseEntity<Object>(error, error.getHttpStatus());
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST, MessageException.INCORRECT_SYNTAX);
		return new ResponseEntity<Object>(error, error.getHttpStatus());
	}	
}