package com.dev.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.dto.ResultDto;
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
		testCreated.setClosed(false);
		
		testRepository.save(testCreated);
		return testCreated;
	}
	
	public boolean wasDoneBy(int testId, int userId) {
		Test test=readId(testId);
		if(test.getUser().getId()!=userId) {
			return false;
		}else {
			return true;
		}
	}
	
	public Test readId(int testId) {
		return testRepository.findById(testId).orElseThrow(()->new IllegalArgumentException("The requested test does not exist"));
	}
	
	public ResultDto getResults(int testId){
		ResultDto result=new ResultDto();
		Test test=readId(testId);
		boolean allSolved=true;
		for(TestItem c: test.getItems()) {
			if(!c.isSolved()) {
				allSolved=false;
				break;
			}
		}
		
		if(!allSolved) {
			throw new IllegalArgumentException("The requested test is not finished");
		}
		
		if(test.getItems().size()==0) {
			throw new IllegalArgumentException("The requested test does not have any item");
		}
		
		int grade=0;
		for(TestItem c: test.getItems()) {
			if(c.isSolvedCorrectly()) {
				grade++;
			}
		}
		test.setGrade(grade);
		test.setClosed(true);
		testRepository.save(test);
		
		Question first=test.getItems().get(0).getQuestion();
		
		result.setCategory(first.getSubCategory().getCategory().getCategory());
		result.setSubCategory(first.getSubCategory().getSubCategory());
		result.setGrade(grade);
		result.setDifficultyLevel(Difficulties.getDifficultie(first.getDifficultyLevel()).getLabel());
		result.setTestId(test.getId());
		result.setUserId(test.getUser().getId());
		
		return result;
	}
}
