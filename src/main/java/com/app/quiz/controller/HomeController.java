package com.app.quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "playQuiz";
	}
	
	@RequestMapping(value = "/play", method = RequestMethod.GET)
	public String play() {
		return "playQuiz";
	}	
}