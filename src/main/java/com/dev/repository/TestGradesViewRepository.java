package com.dev.repository;

import com.dev.model.TestGradesView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestGradesViewRepository extends JpaRepository<TestGradesView, Long> {
    List<TestGradesView> findByUserId(int userId);
}