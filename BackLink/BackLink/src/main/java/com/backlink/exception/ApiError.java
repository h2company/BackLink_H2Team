package com.backlink.exception;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ApiError {

	private Date timestamp = new Date();
	private int status;
	@JsonIgnore
	private HttpStatus httpStatus;
	private String message;
	private List<String> errors;

	public ApiError(HttpStatus httpStatus, String message, List<String> errors) {
		super();
		this.httpStatus = httpStatus;
		this.status = httpStatus.value();
		this.message = message;
		this.errors = errors;
	}

	public ApiError(HttpStatus httpStatus, String message) {
		super();
		this.httpStatus = httpStatus;
		this.status = httpStatus.value();
		this.message = message;
	}
	
	public ApiError(HttpStatus httpStatus, String message, String error) {
        super();
        this.httpStatus = httpStatus;
        this.status = httpStatus.value();
        this.message = message;
        errors = Arrays.asList(error);
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

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}