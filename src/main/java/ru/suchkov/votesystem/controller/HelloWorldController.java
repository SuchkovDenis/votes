package ru.suchkov.votesystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// todo пониать что за пользователь сейчас
@RestController
@CrossOrigin()
public class HelloWorldController {

	@RequestMapping({ "/hello" })
	public String hello() {
		return "Hello World";
	}

	@RequestMapping({ "/helloAdmin" })
	public String helloAdmin() {
		return "Hello Admin";
	}

	@RequestMapping({ "/helloUser" })
	public String helloUser() {
		return "Hello User";
	}
}
