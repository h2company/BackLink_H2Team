package com.backlink.entities;

import java.util.Date;

public class UserDetail {

	private String email;
	private String phone;
	private String fullname;
	private String address;
	private Date birthday;
	private boolean gender;

	public UserDetail() {
		super();
	}

	public UserDetail(String email, String phone, String fullname, String address, Date birthday, boolean gender) {
		super();
		this.email = email;
		this.phone = phone;
		this.fullname = fullname;
		this.address = address;
		this.birthday = birthday;
		this.gender = gender;
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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
