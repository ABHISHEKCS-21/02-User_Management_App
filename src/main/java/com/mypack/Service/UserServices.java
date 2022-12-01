 package com.mypack.Service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mypack.Entity.UnlockAccount;
import com.mypack.Entity.User;
@Service
public interface UserServices {
	
	public String loginCheck(String email, String pws);
	
	public Map<Integer,String> getCountries();
	
	public Map<Integer,String>getStates(Integer countryId);
	
	public Map<Integer,String>getCities(Integer stateId);
	
	public User getUserByEmail(String emailId);
	
	public boolean saveUser(User userAcc);
	
	/* public boolean isTempPwsValid(String email,String pws); */
	
	public String unlockAccount(UnlockAccount unlAcc);
	
	public boolean forgotPassword(String email);

}
