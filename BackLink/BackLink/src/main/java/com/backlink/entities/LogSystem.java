package com.backlink.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "log_system")
public class LogSystem extends BaseEntity {

	@Id
	private String id;

	private String implementer;

	private Type type;

	private LogAction event;

	private String content;

	public LogSystem() {
		super();
	}

	public LogSystem(String implementer, Type type, LogAction event, String content) {
		super();
		this.implementer = implementer;
		this.type = type;
		this.event = event;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImplementer() {
		return implementer;
	}

	public void setImplementer(String implementer) {
		this.implementer = implementer;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public LogAction getEvent() {
		return event;
	}

	public void setEvent(LogAction event) {
		this.event = event;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public enum LogAction {
		CREATE, UPDATE, REMOVE, SEARCH, RECOVER, LOGIN, REGISTER
	}

	public enum Type {
		CUSTOMER, MANAGER, ADMIN
	}

}
