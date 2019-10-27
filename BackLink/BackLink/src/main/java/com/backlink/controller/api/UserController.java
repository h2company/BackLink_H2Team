package com.backlink.controller.api;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backlink.payload.request.AddUserRequest;
import com.backlink.payload.request.UpdateUserRequest;
import com.backlink.service.UserService;


@RestController
@RequestMapping("/api/")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER')")
	@GetMapping("users/{id}")
	public ResponseEntity<?> findOne(@PathVariable String id){
		return new ResponseEntity<Object>(userService.getById(id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("users")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<Object>(userService.findAll(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("users")
	public ResponseEntity<?> createUser(@Valid @RequestBody AddUserRequest addUserRequest) throws ParseException {
		return userService.addUser(addUserRequest);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER')")
	@PutMapping("users")
	public ResponseEntity<?> editUser(@Valid @RequestBody UpdateUserRequest updateUserRequest) throws ParseException {
		return userService.updateUser(updateUserRequest); 
	}
}
