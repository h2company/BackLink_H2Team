package com.backlink.entities;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Event {

	@Id
	private String id;
	
	private List<EventAction> eventAction;
	
	private String siteId;
	
	private String uuid;
	
	public Event() {
		super();
	}

	public Event(List<EventAction> eventAction, String siteId, String uuid) {
		super();
		this.eventAction = eventAction;
		this.siteId = siteId;
		this.uuid = uuid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<EventAction> getEventAction() {
		return eventAction;
	}

	public void setEventAction(List<EventAction> eventAction) {
		this.eventAction = eventAction;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
