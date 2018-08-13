package io.spring.GradleDemo.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	@GetMapping(value = "/user")
	@ResponseBody
	public Principal user(Principal user) {
		return user;
	}
	
	@GetMapping(value = "/{path:[^\\.]*}")
	public String redirect() {
		return "forward:/";
	}
}
