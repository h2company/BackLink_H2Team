package com.backlink.payload.request;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.backlink.Message.MessageException;

public class ActionRequest {
	
	private String urlAction;
	
	@NotNull(message = MessageException.ANO_NOT_BLANK)
	@Size(min = 1,message = MessageException.ANO_SIZE_MIN)
	private String[] keywords;

	@NotNull(message = MessageException.ANO_NOT_NULL)
	@Size(min = 1,message = MessageException.ANO_SIZE_MIN)
	private String[] searchEngine;
	
	@NotNull(message = MessageException.ANO_NOT_NULL)
	@Size(min = 1,message = MessageException.ANO_SIZE_MIN)
	private String[] userAgent;

	@NotNull(message = MessageException.ANO_NOT_NULL)
	private Long point;

	@NotNull(message = MessageException.ANO_NOT_NULL)
	private boolean blockPixel;

	@NotNull(message = MessageException.ANO_NOT_NULL)
	private boolean filterVA;
	
	@NotNull(message = MessageException.ANO_NOT_NULL)
	private boolean saveVA;
	
	@NotNull(message = MessageException.ANO_NOT_NULL)
	private Long beginTime = new Date().getTime();

	@NotNull(message = MessageException.ANO_NOT_NULL)
	private Long endTime;
	
	public String[] getKeywords() {
		return keywords;
	}

	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}

	public String[] getSearchEngine() {
		return searchEngine;
	}

	public void setSearchEngine(String[] searchEngine) {
		this.searchEngine = searchEngine;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
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

	public String[] getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String[] userAgent) {
		this.userAgent = userAgent;
	}

	public boolean isSaveVA() {
		return saveVA;
	}

	public void setSaveVA(boolean saveVA) {
		this.saveVA = saveVA;
	}

	public String getUrlAction() {
		return urlAction;
	}

	public void setUrlAction(String urlAction) {
		this.urlAction = urlAction;
	}	
	
	
}
