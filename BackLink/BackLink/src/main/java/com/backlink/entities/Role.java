package com.backlink.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {
	
    @Id
    private Long id;

    private RoleName name;

    public Role() {

    }

    public Role(RoleName name) {
        if(name.equals(RoleName.ROLE_CUSTOMER)) {
        	this.id = 1L;
        }else if(name.equals(RoleName.ROLE_ADMIN)) {
        	this.id = 2L;
        }
    	this.name = name;
    }

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
    
    public enum RoleName{
		ROLE_CUSTOMER,
		ROLE_ADMIN,
		ROLE_MANAGER
	}
}
