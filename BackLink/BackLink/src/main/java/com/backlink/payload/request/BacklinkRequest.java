package com.backlink.payload.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import com.backlink.Message.MessageException;

public class BacklinkRequest {
	@NotBlank(message = MessageException.ANO_NOT_BLANK)
	@Size(min = 3, max = 400, message = MessageException.ANO_SIZE)
	@URL
	private String urlBacklink;

	private int point;
	
	private int limit;

	private boolean filterVA;
	
	private boolean saveVA;
	
	@NotNull(message = MessageException.ANO_NOT_NULL)
	private Long beginTime;
	
	@NotNull(message = MessageException.ANO_NOT_NULL)
	private Long endTime;

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
