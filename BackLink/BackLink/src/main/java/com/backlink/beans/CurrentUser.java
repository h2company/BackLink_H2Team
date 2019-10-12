package com.backlink.beans;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.backlink.entities.Role.RoleName;
import com.backlink.entities.UserPrincipal;

@Component("currentUser")
public class CurrentUser {
	
	public UserPrincipal get() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (UserPrincipal) authentication.getPrincipal();
	}
	
	public boolean userHasAuthority(RoleName authority)
	{
	    List<GrantedAuthority> authorities = extracted();
	    for (GrantedAuthority grantedAuthority : authorities) {
	    	if (authority.name().equals(grantedAuthority.getAuthority())) {
	            return true;
	        }
	    }

	    return false;
	}

	private List<GrantedAuthority> extracted() {
		return (List<GrantedAuthority>) this.get().getAuthorities();
	}
}
