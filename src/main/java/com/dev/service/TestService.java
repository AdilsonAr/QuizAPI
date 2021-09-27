package com.dev.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.Category;
import com.dev.model.Difficulties;
import com.dev.model.Question;
import com.dev.model.Test;
import com.dev.model.TestItem;
import com.dev.model.User;
import com.dev.repository.TestRepository;

@Service
public class TestService {
	@Autowired
	private TestRepository testRepository;
	@Autowired
	private QuestionService questionService;
	
	public Test generate(User u ,Category c, Difficulties d) {
		
		Test test =new Test(); 
		Test testCreated = testRepository.save(test);
		
		List<Question> questions=questionService.getRandom(d,c);
		List<TestItem> items= new ArrayList<>();
		for(Question i:questions) {
			items.add(new TestItem(testCreated, i, false, false));
		}
		testCreated.setGrade(-1);
		testCreated.setDate(LocalDateTime.now());
		testCreated.setUser(u);
		testCreated.setItems(items);
		testCreated.setItemsCount(items.size());
		
		testRepository.save(testCreated);
		return testCreated;
	}
}
