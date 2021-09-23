package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

}
