package com.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.Category;
import com.dev.model.SubCategory;
import com.dev.repository.SubCategoryRepository;

@Service
public class SubCategoryService {
	@Autowired
	SubCategoryRepository subCategoryRepository;
	public List<SubCategory> readByCategory(Category category){
		return subCategoryRepository.findByCategory(category);
	}
}
