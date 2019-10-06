package com.backlink.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "acction")
public class Action extends BaseEntity {
	
	@Id
	private String id;
	
	private String urlTarget;
	
	private List<String> keywords;
	
	private String searchEngine;
	
	private String userAgent;
	
	private int point;
	
	private String blockPixel;
	
	private boolean filterVA;
	
	private List<String> accessHistory;
	
	private Long beginTime;
	
	private Long endTime;
	
	public Action() {
		super();
	}

	public Action(String urlTarget, List<String> keywords, String searchEngine, String userAgent, int point,
			String blockPixel, boolean filterVA, List<String> accessHistory, Long beginTime, Long endTime) {
		super();
		this.urlTarget = urlTarget;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrlTarget() {
		return urlTarget;
	}

	public void setUrlTarget(String urlTarget) {
		this.urlTarget = urlTarget;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
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

	public String getBlockPixel() {
		return blockPixel;
	}

	public void setBlockPixel(String blockPixel) {
		this.blockPixel = blockPixel;
	}

	public boolean isFilterVA() {
		return filterVA;
	}

	public void setFilterVA(boolean filterVA) {
		this.filterVA = filterVA;
	}

	public List<String> getAccessHistory() {
		return accessHistory;
	}

	public void setAccessHistory(List<String> accessHistory) {
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
