package com.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.Category;
import com.dev.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	public List<Category> readAll(){
		return categoryRepository.findAll();		
	}
	
	public Category readId(int id) {
		return categoryRepository.findById(id).orElseThrow(
				()-> new IllegalArgumentException("The category referenced does not exist"));
	}
}
