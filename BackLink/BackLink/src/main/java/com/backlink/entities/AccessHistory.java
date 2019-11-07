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
	
	private String urlAgent;
	
	private Date timeAccess;
	
	private Date timeConnect;
	
	private List<Event> events;

	public AccessHistory() {
		super();
	}

	public AccessHistory(String ip, String userAgent, String urlAgent, Date timeAccess, Date timeConnect,
			List<Event> events) {
		super();
		this.ip = ip;
		this.userAgent = userAgent;
		this.urlAgent = urlAgent;
		this.timeAccess = timeAccess;
		this.timeConnect = timeConnect;
		this.events = events;
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

	public String getUrlAgent() {
		return urlAgent;
	}

	public void setUrlAgent(String urlAgent) {
		this.urlAgent = urlAgent;
	}

	public Date getTimeAccess() {
		return timeAccess;
	}

	public void setTimeAccess(Date timeAccess) {
		this.timeAccess = timeAccess;
	}

	public Date getTimeConnect() {
		return timeConnect;
	}

	public void setTimeConnect(Date timeConnect) {
		this.timeConnect = timeConnect;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
