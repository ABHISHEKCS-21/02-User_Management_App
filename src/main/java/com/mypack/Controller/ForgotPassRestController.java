
package com.mypack.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mypack.Constants.AppConstants;
import com.mypack.Properties.AppProperties;
import com.mypack.Service.UserServices;

@RestController
public class ForgotPassRestController {
	
	@Autowired
	private UserServices service;
	
	@Autowired
	private AppProperties props;
	
	@GetMapping("/forgotpass/{email}")
	public String forgotPassword(@PathVariable String email) {
		boolean forgotPassword = service.forgotPassword(email);
		if(forgotPassword) {
		return props.getMessages().get(AppConstants.PWS_RECOVER_SUCC);
		}else {
			return props.getMessages().get(AppConstants.PWS_RECOVER_FAIL);
		}
	}
	

}
