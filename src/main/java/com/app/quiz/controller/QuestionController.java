package com.app.quiz.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.quiz.model.Question;
import com.app.quiz.model.SubmitRequest;
import com.app.quiz.repository.QuestionRepository;

@RestController
public class QuestionController {

	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/questions")
	public Page<Question> getAllQuestions(Pageable pageable) {
		Page<Question> findAll = questionRepository.findAll(pageable);
		return findAll;
	}

	@PostMapping("/submit")
	public int submitQuiz(@Valid @RequestBody List<SubmitRequest> quizAnswers) {
		int score = 0;
		for (SubmitRequest request : quizAnswers) {
			Optional<Question> questionOptional = questionRepository.findById(request.getQuestionId());
			if (questionOptional.isPresent()) {
				Question question = questionOptional.get();
				score += request.getAnswerId() == question.getCorrectAnswerId() ? 1 : 0;
			}
		}
		return Math.floorDiv(score * 100, quizAnswers.size());
	}

	@PostMapping("/questions")
	public Question createQuestion(@Valid @RequestBody Question question) {
		return questionRepository.save(question);
	}

}
