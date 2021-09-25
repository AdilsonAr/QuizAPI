package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer>{

}
