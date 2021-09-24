package com.dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.LabelDto;
import com.dev.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	@GetMapping
	public ResponseEntity<?> readAll(){
		List<LabelDto> labels = (categoryService.readAll()).stream().map(x->{
			return new LabelDto(x.getId(), x.getCategory());
		}).collect(Collectors.toList());
		
		return new ResponseEntity<>(labels, HttpStatus.OK);
	}
}
