package com.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.ResponseDto;
import com.dev.dto.TestRequestDto;
import com.dev.dto.TestResponseDto;
import com.dev.model.Category;
import com.dev.model.Difficulties;
import com.dev.model.Test;
import com.dev.model.User;
import com.dev.service.CategoryService;
import com.dev.service.TestService;
import com.dev.service.UserService;

@RestController
@RequestMapping("/quizes")
public class TestController {
	@Autowired
	private TestService testService;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	@PostMapping
	public ResponseEntity<?> readAll(@RequestBody TestRequestDto dto, Authentication a){
		Difficulties d=null;
		switch(dto.getDifficulty()) {
		case 1:
			d=Difficulties.EASY;
			break;
		case 2:
			d=Difficulties.MEDIUM;
			break;
		case 3:
			d=Difficulties.HARD;
			break;
			default:
			throw new IllegalArgumentException("The difficulty level is not admited");
		}
		User u = userService.readByUsername(a.getName());
		Category c = categoryService.readId(dto.getCategoryId());
		Test test=testService.generate(u, c, d);
		TestResponseDto testDto=TestResponseDto.toDto(test);
		return new ResponseEntity<>(new ResponseDto<TestResponseDto>(testDto), HttpStatus.OK);
	}
}
