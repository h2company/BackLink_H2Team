package com.backlink.controller.api;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backlink.entities.AccessHistory;
import com.backlink.payload.request.LoginRequest;
import com.backlink.payload.request.RecoverRequest;
import com.backlink.payload.request.SignUpRequest;
import com.backlink.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.authenticate(loginRequest);
    }

    @SuppressWarnings("unchecked")
	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws ParseException {
    	return userService.register(signUpRequest);
    }
    
    
    @PutMapping("/recover")
    public ResponseEntity<?> recoverUser(@Valid @RequestBody RecoverRequest recoverRequest) {
    	return userService.recover(recoverRequest);
    }
    
	@PostMapping("/test")
	public ResponseEntity<?> aa(@RequestBody AccessHistory accessHistory){
		return new ResponseEntity<Object>(accessHistory, HttpStatus.OK);
	}
    
	
}
