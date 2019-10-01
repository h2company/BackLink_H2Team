package com.backlink.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.backlink.util.Enum.Roles;

@Document(collection = "Users")
public class User extends BaseEntity {

	@Id
	private String id;

	@Indexed(unique = true)
	private String username;

	private String password;

	private Roles role = Roles.CUSTOMER;

	private UserDetail userDetail;

	public User() {
		super();
	}

	public User(String username, String password, Roles role, UserDetail userDetail) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.userDetail = userDetail;
	}

	public User(String id, String username, String password, Roles role, UserDetail userDetail) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.userDetail = userDetail;
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
		this.password = password;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

}
