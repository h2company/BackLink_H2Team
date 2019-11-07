package com.backlink.payload.request;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.backlink.Message.MessageException;

public class UpdateUserRequest {
	
	@NotBlank(message = MessageException.ANO_NOT_BLANK)
	private String id;
	
	@NotBlank(message = MessageException.ANO_NOT_BLANK)
	@Size(min = 5, max = 30, message = MessageException.ANO_SIZE)
	private String username;	
	
	@NotNull(message = MessageException.ANO_NOT_NULL)
	@Size(min = 1,message = MessageException.ANO_SIZE_MIN)
	private RoleRequest[] roles;
	
	@NotBlank(message = MessageException.ANO_NOT_BLANK)
	@Size(min = 5, max = 50, message = MessageException.ANO_SIZE)
	@Email
	private String email;
	
	@NotBlank(message = MessageException.ANO_NOT_BLANK)
	@Size(min = 10, max = 10, message = MessageException.ANO_SIZE)
	@Pattern(regexp = "^(0{1})([1-9]{1})([0-9]{8})")
	private String phone;
	
	@NotBlank(message = MessageException.ANO_NOT_BLANK)
	private String address;
	
	@NotBlank(message = MessageException.ANO_NOT_BLANK)
	@Size(min = 5, max = 30, message = MessageException.ANO_SIZE)
	private String fullname;
	
	private Date birthday;
	
	private boolean gender;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public RoleRequest[] getRoles() {
		return roles;
	}

	public void setRoles(RoleRequest[] roles) {
		this.roles = roles;
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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}	
	
}
