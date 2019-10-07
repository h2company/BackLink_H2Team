package com.backlink.entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "access_history")
public class AccessHistory extends BaseEntity {
	
	@Id
	private String id;
	
	private String ip;
	
	private String userAgent;
	
	private Date timeAccess;
	
	private List<Event> events;

	public AccessHistory() {
		super();
	}

	public AccessHistory(String ip, String userAgent, Date timeAccess, List<Event> events) {
		super();
		this.ip = ip;
		this.userAgent = userAgent;
		this.timeAccess = timeAccess;
		this.events = events;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public Date getTimeAccess() {
		return timeAccess;
	}

	public void setTimeAccess(Date timeAccess) {
		this.timeAccess = timeAccess;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	
}
