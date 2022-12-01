package com.mypack.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mypack.Entity.UnlockAccount;
import com.mypack.Entity.User;
import com.mypack.Service.UserServices;

@RestController
public class UnlockAccRestController {
	
	@Autowired
	private UserServices service;
	
	@PostMapping("/unlock")
	public String unlockAccount(@RequestBody UnlockAccount unlockaccount) {
		String unlockAccount2 = service.unlockAccount(unlockaccount);
		return unlockAccount2;
		
	}

}
