package com.backlink.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "backlinks")
public class Backlink extends BaseEntity {
	@Id
	private String id;
	
	private String username;

	private String urlBacklink;

	private int point;

	private int limit;
	
	private int limit_active;

	private boolean filterVA;
	
	private boolean saveVA;

	private String[] accessHistory;

	private Long beginTime = new Date().getTime();

	private Long endTime;

	public Backlink() {
		super();
	}	
	
	
	public Backlink(String username, String urlBacklink, int point, boolean filterVA, boolean saveVA, Long beginTime,
			Long endTime) {
		super();
		this.username = username;
		this.urlBacklink = urlBacklink;
		this.point = point;
		this.filterVA = filterVA;
		this.saveVA = saveVA;
		this.beginTime = beginTime;
		this.endTime = endTime;
	}

	public Backlink(String username, String urlBacklink, int point, int limit, boolean filterVA, boolean saveVA,
			Long beginTime, Long endTime) {
		super();
		this.username = username;
		this.urlBacklink = urlBacklink;
		this.point = point;
		this.limit = limit;
		this.filterVA = filterVA;
		this.saveVA = saveVA;
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

	public String getUrlBacklink() {
		return urlBacklink;
	}

	public void setUrlBacklink(String urlBacklink) {
		this.urlBacklink = urlBacklink;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	public int getLimit() {
		return limit;
	}


	public void setLimit(int limit) {
		this.limit = limit;
	}


	public int getLimit_active() {
		return limit_active;
	}


	public void setLimit_active(int limit_active) {
		this.limit_active = limit_active;
	}


	public boolean isFilterVA() {
		return filterVA;
	}

	public void setFilterVA(boolean filterVA) {
		this.filterVA = filterVA;
	}

	public boolean isSaveVA() {
		return saveVA;
	}

	public void setSaveVA(boolean saveVA) {
		this.saveVA = saveVA;
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

