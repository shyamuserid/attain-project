package io.spring.GradleDemo.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	@GetMapping(value = "/user")
	@ResponseBody
	public ResponseEntity<Principal> user(Principal user) {
		return new ResponseEntity<Principal>(user, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{path:[^\\.]*}")
	public String redirect() {
		return "forward:/";
	}
	
}
