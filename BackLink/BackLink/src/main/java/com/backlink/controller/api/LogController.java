package com.backlink.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backlink.entities.LogSystem.Type;
import com.backlink.service.LogSystemService;

@RestController()
@RequestMapping("/api/")
public class LogController {

	@Autowired
	private LogSystemService logSystemService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("logs")
	public ResponseEntity<?> getAllLogs(@RequestParam(name = "type", defaultValue = "") Type type){
		return new ResponseEntity<Object>(logSystemService.findAll(type), HttpStatus.OK);
	}
}
