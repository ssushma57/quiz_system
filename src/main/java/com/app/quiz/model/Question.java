package com.app.quiz.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(nullable = false)
	@Type(type = "text")
	private String question;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "question")
	private Set<Answer> answers = new HashSet<Answer>();

	public Long getId() {
		return this.id;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	@JsonIgnore
	public long getCorrectAnswerId() {
		for (Answer answer : answers) {
			if (answer.isValid()) {
				return answer.getId();
			}
		}
		return -1;
	}

	@JsonIgnore
	public String getCorrectAnswer() {
		for (Answer answer : answers) {
			if (answer.isValid()) {
				return answer.getAnswer();
			}
		}
		return "";
	}

	@Override
	public String toString() {
		return question + "|" + answers;
	}

}