package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
