package com.backlink.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.backlink.Message.MessageException;
import com.backlink.entities.Role;
import com.backlink.entities.Role.RoleName;

public class RoleRequest{

	@NotNull(message = MessageException.ANO_NOT_NULL)
	private Long id;

	@NotBlank(message = MessageException.ANO_NOT_BLANK)
    private RoleName name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleName getName() {
		return name;
	}

	public void setName(RoleName name) {
		this.name = name;
	}
	
	
}
