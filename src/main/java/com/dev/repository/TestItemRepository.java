package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.model.TestItem;

@Repository
public interface TestItemRepository extends JpaRepository<TestItem, Integer>{

}
