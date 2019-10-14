package com.backlink.entities;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Document
public class BaseEntity {
	
	//Thời gian tạo
	protected Date createAt = new Date();
	
	//Thời gian update
	protected Date updateAt;
	
	// Trạng thái
	@JsonProperty(access=Access.WRITE_ONLY)
	protected boolean enabled = true;

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}	
	
}
