package com.backlink.controller.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backlink.entities.AccessHistory;
import com.backlink.payload.request.ActionRequest;
import com.backlink.service.ActionService;

@RestController
@RequestMapping("/api/")
public class ActionController {
	
	@Autowired
	private ActionService actionService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER')")
	@GetMapping("actions")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<Object>(actionService.findByUsername(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER')")
	@GetMapping("actions/dashboard")
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") int p){
		return new ResponseEntity<Object>(actionService.findAll(p), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER')")
	@PostMapping("actions")
	public ResponseEntity<?> createAction(HttpServletRequest request ,@Valid @RequestBody ActionRequest actionRequest) {
		return actionService.saveAction(actionRequest, request);
	}	
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER')")
	@GetMapping("actions/{id}")
	public ResponseEntity<?> findAction(@PathVariable String id) {
		return actionService.findById(id);
	}	
	
}
