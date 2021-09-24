package com.dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.LabelDto;
import com.dev.dto.ResponseDto;
import com.dev.model.SubCategory;
import com.dev.service.CategoryService;
import com.dev.service.SubCategoryService;

@RestController
@RequestMapping("/subcategories")
public class SubCategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SubCategoryService subCategoryService;
	@GetMapping
	public ResponseEntity<?> read(@RequestParam int categoryId){
		List<SubCategory> subs=subCategoryService.readByCategory(categoryService.readId(categoryId));
		
		List<LabelDto> labels = subs.stream().map(x->{
			return new LabelDto(x.getId(), x.getSubCategory());
		}).collect(Collectors.toList());
		
		ResponseDto<List<LabelDto>> response=new ResponseDto<>(labels);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
