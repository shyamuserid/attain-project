package io.spring.GradleDemo.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@RequestMapping(value = "/user")
	public Principal user(Principal user) {
		return user;
	}
}
