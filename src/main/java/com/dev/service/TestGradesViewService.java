package com.dev.service;

import com.dev.model.Category;
import com.dev.model.SubCategory;
import com.dev.model.TestGradesView;
import com.dev.model.User;
import com.dev.repository.TestGradesViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestGradesViewService {

    @Autowired
    private TestGradesViewRepository testGradesViewRepository;

    public List<TestGradesView> readByUserId(int userId){
        return testGradesViewRepository.findByUserId(userId);
    }

}
