package com.affiliate.user.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.affiliate.bean.MyUser;
import com.affiliate.repository.UserRepository;

@Service
public class UserUpdateAndChangePassword {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	//update user data from setting page...
	public MyUser updatePassword(MyUser myuser, Principal principal) throws Exception{
		
		MyUser currentUser = this.userRepository.findByEmail(principal.getName());
		currentUser.setFirstname(myuser.getFirstname());
		currentUser.setLastname(myuser.getLastname());
		currentUser.setMobile(myuser.getMobile());
		currentUser.setGender(myuser.getGender());
		currentUser.setAddress(myuser.getAddress());
		currentUser.setCountry(myuser.getCountry());
		currentUser.setState(myuser.getState());
		currentUser.setCity(myuser.getCity());
		currentUser.setZip(myuser.getZip());
		this.userRepository.save(currentUser);
	
		return currentUser;
	}


	// change user pasword from mysetting page 
	public MyUser changeMyPassword(String oldPassword, String password,	Principal principal) throws Exception {	
		MyUser currentUser = this.userRepository.findByEmail(principal.getName());
		if(this.bCryptPasswordEncoder.matches(oldPassword, currentUser.getPassword())) {
			
			currentUser.setPassword(this.bCryptPasswordEncoder.encode(password));
			this.userRepository.save(currentUser);
			System.out.println("password changed successfully");
		}
		
		return currentUser;
	}

	
	
	
}
