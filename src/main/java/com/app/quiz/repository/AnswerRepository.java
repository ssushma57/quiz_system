package com.app.quiz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.quiz.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
	Page<Answer> findByQuestionId(Long questionId, Pageable pageable);
}
