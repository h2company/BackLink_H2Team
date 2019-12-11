package com.backlink.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.backlink.Message.MessageException;

public class SignUpRequest {
	
	@NotBlank(message = MessageException.ANO_NOT_BLANK)
	@Size(min = 5, max = 30, message = MessageException.ANO_SIZE)
	private String username;

	@NotBlank(message = MessageException.ANO_NOT_BLANK)
	@Size(min = 8, max = 50, message = MessageException.ANO_SIZE)
	private String password;

	@NotBlank(message = MessageException.ANO_NOT_BLANK)
	@Size(min = 5, max = 30, message = MessageException.ANO_SIZE)
	private String fullname;

	@NotBlank(message = MessageException.ANO_NOT_BLANK)
	@Size(min = 5, max = 50, message = MessageException.ANO_SIZE)
	@Email
	private String email;

	@NotBlank(message = MessageException.ANO_NOT_BLANK)
	@Size(min = 10, max = 10, message = MessageException.ANO_SIZE)
	@Pattern(regexp = "^(0{1})([1-9]{1})([0-9]{8})")
	private String phone;

	//@NotBlank(message = MessageException.ANO_NOT_BLANK)
	private String address;

	private boolean gender;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}	
	

//	@NotBlank(message = MessageException.ANO_NOT_BLANK)
//	@Pattern(regexp = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$")
//	private String birthday;


}