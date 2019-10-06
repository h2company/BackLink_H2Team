package com.backlink.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SyntaxException extends IllegalStateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7890262702476479104L;

	public SyntaxException(String message) {
		super(message);
	}
	
	public SyntaxException(String message, Throwable cause) {
		super(message, cause);
	}

}
