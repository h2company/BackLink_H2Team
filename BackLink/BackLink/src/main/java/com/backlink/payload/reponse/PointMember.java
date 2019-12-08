package com.backlink.payload.reponse;

public class PointMember {

	private String fullname;
	private String username;
	private int point;
	
	
	public PointMember(String fullname, String username, int point) {
		super();
		this.fullname = fullname;
		this.username = username;
		this.point = point;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
}
