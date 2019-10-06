package com.backlink.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "users")
public class User extends BaseEntity {

	@Id
	private String id;

	@Indexed(unique = true)
	private String username;

	private String password;

	private Set<Role> roles = new HashSet<>();

	@Indexed(unique = true)
	private String email;

	@Indexed(unique = true)
	private String phone;

	private String fullname;

	private String address;

	private Date birthday;

	private boolean gender;

	public User() {
		super();
	}

	public User(String username, String password, Set<Role> roles, String email, String phone, String fullname,
			String address, Date birthday, boolean gender) {
		super();
		this.username = username;
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
		this.roles = roles;
		this.email = email;
		this.phone = phone;
		this.fullname = fullname;
		this.address = address;
		this.birthday = birthday;
		this.gender = gender;
	}
	
	

	public User(String username, String password, String email, String fullname) {
		super();
		this.username = username;
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
		this.email = email;
		this.fullname = fullname;
	}

	public User(String id, String username, String password, Set<Role> roles, String email, String phone,
			String fullname, String address, Date birthday, boolean gender) {
		super();
		this.id = id;
		this.username = username;
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
		this.roles = roles;
		this.email = email;
		this.phone = phone;
		this.fullname = fullname;
		this.address = address;
		this.birthday = birthday;
		this.gender = gender;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
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
