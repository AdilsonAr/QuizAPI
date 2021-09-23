package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer>{

}
