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
import org.springframework.web.bind.annotation.RestController;

import com.backlink.entities.Backlink;
import com.backlink.exception.ApiException;
import com.backlink.payload.reponse.APIResponse;
import com.backlink.payload.request.BacklinkRequest;
import com.backlink.payload.request.VerifyRequest;
import com.backlink.service.BacklinkService;

@RestController
@RequestMapping("/api/")
public class BacklinkController {

	@Autowired
	private BacklinkService backlinkService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER')")
	@GetMapping("backlink/getBackink/{id}")
	public ResponseEntity<?> getBacklink(@PathVariable String id) {
		return new ResponseEntity<Object>(backlinkService.getById(id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER')")
	@GetMapping("backlink/getBackinks")
	public ResponseEntity<?> getBacklinks() {
		return new ResponseEntity<Object>(backlinkService.findAll(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER')")
	@GetMapping("backlink/getBackinks/customer")
	public ResponseEntity<?> getBacklinksByUser() {
		return new ResponseEntity<Object>(backlinkService.findAllByUser(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER')")
	@PostMapping("backlink/createBackink")
	public ResponseEntity<?> createBacklink(@Valid @RequestBody BacklinkRequest backlinkRequest) {
		return backlinkService.saveBacklink(backlinkRequest);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER')")
	@PostMapping("backlink/checkverifyACPBacklink")
	public ResponseEntity<?> checkverifyACPBacklink(@Valid @RequestBody VerifyRequest verifyRequest) {
		return backlinkService.checkverifyACPBacklink(verifyRequest);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER')")
	@PostMapping("backlink/verifyACPBacklink")
	public ResponseEntity<?> verifyACPBacklink(@Valid @RequestBody VerifyRequest verifyRequest) {
		return backlinkService.verifyACPBacklink(verifyRequest);
	}
}
