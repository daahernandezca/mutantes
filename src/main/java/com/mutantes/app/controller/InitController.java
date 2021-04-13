package com.mutantes.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {

	@RequestMapping("/")
	public String init() {
		return "This a api for mercado libre Test";
	}
}
