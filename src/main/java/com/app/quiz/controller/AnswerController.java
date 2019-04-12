package com.app.quiz.controller;

import java.util.HashSet;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.quiz.model.Answer;
import com.app.quiz.model.AnswerResponse;
import com.app.quiz.repository.AnswerRepository;
import com.app.quiz.repository.QuestionRepository;

@RestController
public class AnswerController {

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/questions/{question_id}/answers")
	public AnswerResponse getAllAnswersByQuestionId(@PathVariable(value = "question_id") Long questionId,
			Pageable pageable) {
		// return answerRepository.findByQuestionId(questionId, pageable);
		Page<Answer> answers = answerRepository.findByQuestionId(questionId, pageable);
		AnswerResponse answerResponse = new AnswerResponse();
		HashSet<String> options = new HashSet<String>();
		if (answers != null) {
			for (Answer answer : answers) {
				if (answerResponse.getQuestion() == null)
					answerResponse.setQuestion(answer.getQuestion().getQuestion());
				options.add(answer.getAnswer());
			}
			answerResponse.setOptions(options);
		}
		return answerResponse;
	}

	@PostMapping("/questions/{questionId}/answers")
	public Optional<Answer> createAnswer(@PathVariable(value = "questionId") Long questionId,
			@Valid @RequestBody Answer answer) {
		return questionRepository.findById(questionId).map(question -> {
			answer.setQuestion(question);
			return answerRepository.save(answer);
		});
	}

}
