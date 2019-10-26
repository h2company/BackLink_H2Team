package com.backlink.entities;

public class SDKMessage {
	private MessageType type;
	private String action;
	private String data;
	private String token;
	private String siteId;
	
	public enum MessageType {
        TRACKING,
        CONNECT,
        LEAVE
    }

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	
}
