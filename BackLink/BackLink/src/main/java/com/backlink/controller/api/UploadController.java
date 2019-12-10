package com.backlink.controller.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backlink.service.UserService;

@RestController
@RequestMapping("/api/upload/")
public class UploadController {
	
	@Autowired
	private UserService userService;

	@PostMapping("avatar")
	public ResponseEntity<?> updateAvatar(@RequestParam MultipartFile file) throws IOException{		
		return userService.saveAvatar(file);
	}
}
