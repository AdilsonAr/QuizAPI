package com.dev.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.AnswerRequestDto;
import com.dev.dto.ResponseDto;
import com.dev.model.User;
import com.dev.service.QuestionService;
import com.dev.service.TestItemService;
import com.dev.service.TestService;
import com.dev.service.UserService;

@RestController
@RequestMapping("/answers")
public class TestItemController {
	
	@Autowired
	private TestService testService;
	@Autowired
	private UserService userService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private TestItemService testItemService;
	
	@PostMapping
	public ResponseEntity<?> readAll(@Valid @RequestBody AnswerRequestDto dto, Authentication a){
		User u = userService.readByUsername(a.getName());
		
		if(!testService.wasDoneBy(dto.getTestId(), u.getId())) {
			return new ResponseEntity<>(new ResponseDto<String>("User not allowed for performing this action"), HttpStatus.FORBIDDEN);
		}
		
		testItemService.create(testService.readId(dto.getTestId()), questionService.readId(dto.getQuestionId()), dto.getAnswerId());
		return new ResponseEntity<>(new ResponseDto<String>("The answer has been saved"), HttpStatus.OK);
	}
	
}
