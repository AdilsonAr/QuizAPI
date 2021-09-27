package com.dev.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.UserRequestDto;
import com.dev.model.User;
import com.dev.service.UserService;

@RestController
@RequestMapping("/signUp")
public class SingUpController {
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody UserRequestDto u){
		if(userService.exist(u.getEmail())) {
			throw new IllegalArgumentException("This e-mail already exist");
		}
		userService.create(UserRequestDto.toModel(u));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
