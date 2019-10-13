package com.backlink.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignUpRequest {
	@NotBlank
	@Size(min = 5, max = 30)
	private String username;

	@NotBlank
	@Size(min = 8, max = 50)
	private String password;

	@NotBlank
	@Size(min = 5, max = 30)
	private String fullname;

	@NotBlank
	@Size(min = 5, max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(min = 10, max = 10)
	@Pattern(regexp = "^(0{1})([1-9]{1})([0-9]{8})")
	private String phone;

	@NotBlank
	private String address;

	private boolean gender;

	@NotBlank
	@Pattern(regexp = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$")
	private String birthday;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}