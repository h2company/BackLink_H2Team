package com.backlink.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backlink.entities.User;
import com.backlink.service.UserService;


@RestController
@RequestMapping("/api/")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("users")
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("users")
	public User createUser(@RequestBody User user) {
		return userService.saveOne(user);
	}
}
