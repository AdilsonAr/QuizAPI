package com.dev.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.Category;
import com.dev.model.Difficulties;
import com.dev.model.Question;
import com.dev.model.QuestionId;
import com.dev.model.SubCategory;
import com.dev.repository.QuestionRepository;

@Service
public class QuestionService {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private SubCategoryService subCategoryService;
	
	public List<Question> getRandom(Difficulties d, Category c){
		List<SubCategory> subs=subCategoryService.readByCategory(c);
		List<QuestionId> ids=new ArrayList<>();
		
		subs.forEach(x->{
			List<QuestionId> idTemp = questionRepository.findByDifficultyLevelAndSubCategory(d.getId(), x);
			ids.addAll(idTemp);
		});
		
		Set<Integer>selectedIds=new HashSet<>();
		
		if(ids.size()>10) {
			while(selectedIds.size()<10) {
				int id=(int)Math.floor(Math.random()*(ids.size()));
				selectedIds.add(id);
			}
		}else {
			ids.forEach(x->{
				selectedIds.add(x.getId());
			});
		}
		return questionRepository.findAllById(selectedIds);
	}
}
