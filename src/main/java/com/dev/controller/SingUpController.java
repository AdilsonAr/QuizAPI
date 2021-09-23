package com.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.UserRequestDto;
import com.dev.service.UserService;

@RestController
@RequestMapping("/signUp")
public class SingUpController {
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody UserRequestDto u){
		userService.create(UserRequestDto.toModel(u));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> readAll(){
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
