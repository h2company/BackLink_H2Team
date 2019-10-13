package com.backlink.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.backlink.Message.MessageException;

public class RecoverRequest {
	
	@NotBlank(message = MessageException.ANO_NOT_BLANK)
	@Email(message = MessageException.ANO_EMAIL_INCORRECT)
	private String email;

	public RecoverRequest() {
		super();
	}

	public RecoverRequest(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
