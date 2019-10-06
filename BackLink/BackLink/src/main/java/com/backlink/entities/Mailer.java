package com.backlink.entities;

public class Mailer {
	private String[] sendTo;
	private String subject;
	private String content;

	public Mailer() {
		super();
	}

	public Mailer(String[] sendTo, String subject, String content) {
		super();
		this.sendTo = sendTo;
		this.subject = subject;
		this.content = content;
	}

	public String[] getSendTo() {
		return sendTo;
	}

	public void setSendTo(String[] sendTo) {
		this.sendTo = sendTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
