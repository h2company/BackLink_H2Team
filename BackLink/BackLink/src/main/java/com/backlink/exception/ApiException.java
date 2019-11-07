package com.backlink.exception;

import java.util.Date;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ApiException {

	private Date timestamp = new Date();
	private int status;
	@JsonIgnore
	private HttpStatus httpStatus;
	private String message;
	private Map<String, String> errors;

	public ApiException(HttpStatus httpStatus, String message, Map<String, String> errors) {
		super();
		this.httpStatus = httpStatus;
		this.status = httpStatus.value();
		this.message = message;
		this.errors = errors;
	}

	public ApiException(HttpStatus httpStatus, String message) {
		super();
		this.httpStatus = httpStatus;
		this.status = httpStatus.value();
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.status = httpStatus.value();
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

}