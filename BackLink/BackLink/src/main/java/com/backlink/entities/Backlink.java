package com.backlink.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "backlink")
public class Backlink extends BaseEntity {
	@Id
	private String id;

	private String urlBacklink;

	private String urlIgnore;

	private int point;

	private String blockPixel;

	private boolean filterVA;

	private String[] accessHistory;

	private Long beginTime;

	private Long endTime;

	public Backlink() {
		super();
	}

	public Backlink(String urlBacklink, String urlIgnore, int point, String blockPixel, boolean filterVA,
			String[] accessHistory, Long beginTime, Long endTime) {
		super();
		this.urlBacklink = urlBacklink;
		this.urlIgnore = urlIgnore;
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

	public String getUrlBacklink() {
		return urlBacklink;
	}

	public void setUrlBacklink(String urlBacklink) {
		this.urlBacklink = urlBacklink;
	}

	public String getUrlIgnore() {
		return urlIgnore;
	}

	public void setUrlIgnore(String urlIgnore) {
		this.urlIgnore = urlIgnore;
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

