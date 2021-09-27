package com.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.Answer;
import com.dev.model.Question;
import com.dev.model.Test;
import com.dev.model.TestItem;
import com.dev.repository.TestItemRepository;

@Service
public class TestItemService {
	@Autowired
	private TestItemRepository testItemRepository;
	
	public void create(Test t, Question q, int a) {
		boolean possibleAnswer=false;
		for(Answer c: q.getAnswers()) {
			if(c.getId()==a) {
				possibleAnswer=true;
			}
		}
		
		boolean present=false;
		List<TestItem> items=t.getItems();
		TestItem beingSolved=null;
		for(TestItem c: items) {
			if(q.getId()==c.getQuestion().getId()) {
				present=true;
				beingSolved=c;
			}
		}
		
		if(!present) {
			throw new IllegalArgumentException("The question and test do not match");
		}
		
		if(beingSolved.isSolved()) {
			throw new IllegalArgumentException("The question has already been answered");
		}
		
		if(!possibleAnswer) {
			throw new IllegalArgumentException("The answer is not a valid option for this particular question");
		}
		
		boolean correct=false;
		for(Answer c: q.getAnswers()) {
			if(c.isCorrect()) {
				if(c.getId()==a) {
					correct=true;
				}
			}
		}
		
		beingSolved.setSolved(true);
		beingSolved.setSolvedCorrectly(correct);
		testItemRepository.save(beingSolved);
	}
	
}
