package com.backlink.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RecoverRequest {
	
	@NotBlank(message="Incorrect Syntax")
	@Email(message="Incorrect Syntax")
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
