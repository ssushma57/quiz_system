package com.app.quiz.model;

import java.util.Set;

public class AnswerResponse {
  private String question; 
  private Set<String> options; 
  
  public String getQuestion() {
	  return this.question;
  }
  
  public Set<String> getOptions(){
	  return options; 
  }
  
  public void setQuestion(String question) {
	  this.question = question; 
  }
  
  public void setOptions(Set<String> answers) {
	  this.options = answers;
  }
}
