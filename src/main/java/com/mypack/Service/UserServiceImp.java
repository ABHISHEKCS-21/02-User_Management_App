  package com.mypack.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypack.Constants.AppConstants;
import com.mypack.Entity.City;
import com.mypack.Entity.Country;
import com.mypack.Entity.State;
import com.mypack.Entity.UnlockAccount;
import com.mypack.Entity.User;
import com.mypack.Properties.AppProperties;
import com.mypack.Repositories.CityRepository;
import com.mypack.Repositories.CountryRepository;
import com.mypack.Repositories.StateRepository;
import com.mypack.Repositories.UserRepository;
import com.mypack.Utils.EmailsUtils;

@Service
public class UserServiceImp implements UserServices {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CountryRepository countryRepo;
	
	@Autowired
	private StateRepository stateRepo;
	
	@Autowired
	private CityRepository cityRepo;
	
	@Autowired
	private EmailsUtils emailutils;
	
	@Autowired
	private AppProperties props;

	@Override
	public String loginCheck(String email, String pws) {
		User entity = userRepo.findByEmailAndPassword(email, pws);

		if (entity != null) {
			String status = entity.getStatus();

		//	if (status.equals(AppConstants.UNLOCKED)) {
			if(AppConstants.UNLOCKED.equals(status)) {
				return AppConstants.SUCCESS;
			} else {
				return props.getMessages().get(AppConstants.LOCKED_ACOUNT);
			}
		} else {
			return props.getMessages().get(AppConstants.INVALID_CERDENTIALS);
		}
	}

	@Override
	public Map<Integer, String> getCountries() {
		List<Country> findAll = countryRepo.findAll();

		Map<Integer, String> countries = new HashMap<>();

		 findAll.forEach(entity ->{
			 countries.put(entity.getCountryId(), entity.getCountryName());
			 });
		return countries;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {

		List<State> entities = stateRepo.findByCountryId(countryId);

		Map<Integer, String> states = new HashMap();

		entities.forEach(entity -> {
			states.put(entity.getStateId(), entity.getStateName());
		});
		return states;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {

		List<City> cty = cityRepo.findByStateId(stateId);

		Map<Integer, String> cities = new HashMap<>();

		cty.forEach(entity -> {
			cities.put(entity.getCityId(), entity.getCityName());
		});
		return cities;

	}

	@Override
	public User getUserByEmail(String emailId) {
		User entity = userRepo.findByEmail(emailId);
		return entity;
	}

	@Override
	public boolean saveUser(User user) {
		String tempPws = generateRandomPws(5);
		user.setPassword(tempPws);
		user.setStatus(AppConstants.LOCKED);
		User save = userRepo.save(user);
		if (save.getUserId() != null) {
			
			boolean isSent=sendAccRegEmail(user);
			return isSent;
			
		} else {
			return false;
		}

	}

	@Override
	public String unlockAccount(UnlockAccount unlAcc) {
		User user = userRepo.findByEmail(unlAcc.getEmail());
		if(user!=null && user.getPassword().equals(unlAcc.getTemPassword())) {
			user.setPassword(unlAcc.getNewPassword());
			user.setStatus(AppConstants.UNLOCKED );
			userRepo.save(user);
			return props.getMessages().get(AppConstants.ACC_UNLOCK_SUCC);

		}
		return props.getMessages().get(AppConstants.INV_TEMP_PWD);
	}

	@Override
	public boolean forgotPassword(String email) {
		User user = userRepo.findByEmail(email);
		if(user!=null&& AppConstants.UNLOCKED.equals(user.getStatus())) {
			return sendPwdToUserEmail(user);
			
		}
				return false;
	}
	

	private String generateRandomPws(Integer length) {
		 byte[] array = new byte[7]; // length is bounded by 7
		    new Random().nextBytes(array);
		    String generatedString = new String(array, Charset.forName("UTF-8"));

		    System.out.println(generatedString);
		return generatedString;
	}
 
	private boolean sendPwdToUserEmail(User user) {
		String body = getEmailBodyText(user, "RECOVERY-PASSWORD-EMAIL-BODY-TEMPLATE.txt");
		emailutils.sendRegEmail(props.getMessages().get(AppConstants.MAIL_SUB_MSG),
									body,
									user.getEmail());
		return true;
	}
	
	private boolean sendAccRegEmail(User user ) { 
		String body = getEmailBodyText(user,"UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt");
		emailutils.sendRegEmail(props.getMessages().get(AppConstants.REG_SUCC_MSG),body, user.getEmail());
		return true;
	}
	
	
	private String  getEmailBodyText( User user,String fileName) {
		
		String mailBody=null;
		StringBuilder sb= new StringBuilder();
		try {
			FileReader fr=new FileReader(fileName);
			BufferedReader br=new BufferedReader(fr);
			String line = br.readLine();
			while(line !=null) {
				sb.append(line);
				line=br.readLine();
			}
			br.close();
			
			mailBody = sb.toString();
			mailBody = mailBody.replace("{FNAME}", user.getFirstName());
			mailBody = mailBody.replace("{LNAME}", user.getLastName());
			mailBody = mailBody.replace("{TEMP-PWD}", user.getPassword());
			mailBody = mailBody.replace("{EMAIL}", user.getEmail());
			mailBody = mailBody.replace("{PWD}", user.getPassword());
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mailBody;
	}

}
