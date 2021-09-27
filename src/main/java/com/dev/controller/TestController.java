package com.dev.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.ResponseDto;
import com.dev.dto.ResultDto;
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
	public ResponseEntity<?> readAll(@Valid @RequestBody TestRequestDto dto, Authentication a){
		Difficulties d=Difficulties.getDifficultie(dto.getDifficulty());
		User u = userService.readByUsername(a.getName());
		Category c = categoryService.readId(dto.getCategoryId());
		Test test=testService.generate(u, c, d);
		TestResponseDto testDto=TestResponseDto.toDto(test);
		return new ResponseEntity<>(new ResponseDto<TestResponseDto>(testDto), HttpStatus.OK);
	}
	
	@GetMapping("/{quizId}/report")
	public ResponseEntity<?> getResults(@PathVariable("quizId") int quizId, Authentication a){
		User u = userService.readByUsername(a.getName());
		if(!testService.wasDoneBy(quizId, u.getId())) {
			return new ResponseEntity<>(new ResponseDto<String>("User not allowed for performing this action"), HttpStatus.FORBIDDEN);
		}
		ResultDto result=testService.getResults(quizId);
		return new ResponseEntity<>(new ResponseDto<ResultDto>(result), HttpStatus.OK);
	}
}
