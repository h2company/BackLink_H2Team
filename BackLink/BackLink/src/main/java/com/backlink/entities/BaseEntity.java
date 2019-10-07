package com.backlink.entities;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BaseEntity {
	
	//Thời gian tạo
	protected Date createAt = new Date();
	
	//Thời gian update
	protected Date updateAt;
	
	// Trạng thái
	protected boolean enabled = true;

	protected Date getCreateAt() {
		return createAt;
	}

	protected void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	protected Date getUpdateAt() {
		return updateAt;
	}

	protected void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	protected boolean isEnabled() {
		return enabled;
	}

	protected void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}	
	
}
