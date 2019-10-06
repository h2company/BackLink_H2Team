package com.backlink.entities;

import org.springframework.data.annotation.Id;

public class EventAction {
	
	private Long timeStamp;
	
	private String selector;
	
	private String event;
	
	public EventAction() {
		super();
	}

	public EventAction(Long timeStamp, String selector, String event) {
		super();
		this.timeStamp = timeStamp;
		this.selector = selector;
		this.event = event;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getSelector() {
		return selector;
	}

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	
	
}
