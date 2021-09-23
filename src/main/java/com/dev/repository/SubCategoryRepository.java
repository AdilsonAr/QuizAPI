package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.model.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer>{

}
