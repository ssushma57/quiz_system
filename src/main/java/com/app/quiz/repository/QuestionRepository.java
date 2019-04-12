package com.app.quiz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.quiz.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
	Page<Question> findById(Long id, Pageable pageable);
}
