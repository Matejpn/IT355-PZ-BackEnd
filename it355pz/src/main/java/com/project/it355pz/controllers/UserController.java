package com.project.it355pz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.it355pz.dto.request.RegisterDto;
import com.project.it355pz.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<RegisterDto> register(@RequestBody RegisterDto registerDto){
		RegisterDto user = userService.register(registerDto);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
}
