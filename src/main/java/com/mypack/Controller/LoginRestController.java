package com.mypack.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mypack.Service.UserServices;
import com.mypack.domain.LoginRequest;

@RestController
public class LoginRestController {
   
	@Autowired
	private UserServices service;
	
	@PostMapping("/login")
	public String Login( @RequestBody LoginRequest request) {
	
		return service.loginCheck(request.getEmail(), request.getPwd());
	}
}
