package com.backlink.payload.request;

import javax.validation.constraints.NotBlank;

import com.backlink.Message.MessageException;

public class LoginRequest {
	
	@NotBlank(message = MessageException.ANO_NOT_BLANK)
    private String usernameOrEmail;

	@NotBlank(message = MessageException.ANO_NOT_BLANK)
    private String password;

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}