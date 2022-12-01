package com.mypack.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mypack.Constants.AppConstants;
import com.mypack.Entity.User;
import com.mypack.Properties.AppProperties;
import com.mypack.Service.UserServices;

@RestController
public class UserRegRestController {

		@Autowired
		private UserServices service;
		
		@Autowired
		private AppProperties props;
		
	@GetMapping("/countries")
	public Map<Integer,String> getCountries(){
		Map<Integer, String> countries = service.getCountries();
		return countries;
	}
	
	@GetMapping("/states/{countryId}")
	public Map<Integer,String> getStetes(@PathVariable Integer countryId){
		Map<Integer, String> states = service.getStates(countryId);
		return states;
		
	}
	
	@GetMapping("cities/{stateId}")
	public Map<Integer,String>getCityes(@PathVariable Integer stateId){
		 Map<Integer, String> cities = service.getCities(stateId);
		 return cities;
		
	}
	
	@GetMapping("/email-check/{email}")
	public String uniqueEmailCheck(@PathVariable String email) {
		User acc = service.getUserByEmail(email);
		return acc !=null ? AppConstants.DUPLICATE:AppConstants.UNIQUE;
	}  
	
	@PostMapping("register")
	public String saveUser(@RequestBody User user ) {
		User acc = service.getUserByEmail(user.getEmail());
		if(acc==null) {
			boolean saveUser = service.saveUser(user);
			if(saveUser =true) {
				return props.getMessages().get(AppConstants.ACC_REG_SUCC);
			}
			return props.getMessages().get(AppConstants.ACC_REG_FAIL);
		}else {
			return props.getMessages().get(AppConstants.USER_ALREADY_REG);
		}
		
	}
}

