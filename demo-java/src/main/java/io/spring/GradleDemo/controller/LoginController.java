package io.spring.GradleDemo.controller;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import io.spring.GradleDemo.model.UserAccount;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class); 
	
	@GetMapping(value = "/user")
	@ResponseBody
	public UserAccount user(@RequestHeader("authorization") String authorization) {
		UserAccount account = new UserAccount();
		authorization = authorization.substring(authorization.indexOf(" "));
		String decoded = new String(Base64.getMimeDecoder().decode(authorization));
		logger.info(decoded);
		String[] decodedArray = decoded.split(":");
		account.setUsername(decodedArray[0]);
		account.setPassword(decodedArray[1]);
		return account;
	}
	
	@GetMapping(value = "/{path:[^\\.]*}")
	public String redirect() {
		return "forward:/";
	}
	
}
