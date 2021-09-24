package com.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	@GetMapping
	public ResponseEntity<?> readAll(){
		categoryService.readAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
