package com.backlink.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "actions")
public class Action extends BaseEntity {

	@Id
	private String id;

	private String username;

	private String[] keywords;

	private String searchEngine;

	private String userAgent;

	private int point;

	private boolean blockPixel;

	private boolean filterVA;

	private String[] accessHistory;

	private Long beginTime = new Date().getTime();

	private Long endTime;

	public Action() {
		super();
	}

	public Action(String username, String[] keywords, String searchEngine, String userAgent, int point,
			boolean blockPixel, boolean filterVA, String[] accessHistory, Long beginTime, Long endTime) {
		super();
		this.username = username;
		this.keywords = keywords;
		this.searchEngine = searchEngine;
		this.userAgent = userAgent;
		this.point = point;
		this.blockPixel = blockPixel;
		this.filterVA = filterVA;
		this.accessHistory = accessHistory;
		this.beginTime = beginTime;
		this.endTime = endTime;
	}	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getKeywords() {
		return keywords;
	}

	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}

	public String getSearchEngine() {
		return searchEngine;
	}

	public void setSearchEngine(String searchEngine) {
		this.searchEngine = searchEngine;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public boolean isBlockPixel() {
		return blockPixel;
	}

	public void setBlockPixel(boolean blockPixel) {
		this.blockPixel = blockPixel;
	}

	public boolean isFilterVA() {
		return filterVA;
	}

	public void setFilterVA(boolean filterVA) {
		this.filterVA = filterVA;
	}

	public String[] getAccessHistory() {
		return accessHistory;
	}

	public void setAccessHistory(String[] accessHistory) {
		this.accessHistory = accessHistory;
	}

	public Long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

}
