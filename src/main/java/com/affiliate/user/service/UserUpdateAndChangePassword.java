package com.affiliate.user.service;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
	public MyUser updateProfile(MyUser myuser, Principal principal, MultipartFile profileImage) throws Exception{
		Byte[] byteObjects = convertToBytes(profileImage);
		
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
		currentUser.setImage(byteObjects);
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

	 private Byte[] convertToBytes(MultipartFile file) throws IOException {
	        Byte[] byteObjects = new Byte[file.getBytes().length];
	        int i = 0;
	        for (byte b : file.getBytes()) {
	            byteObjects[i++] = b;
	        }
	        return byteObjects;
	    }
	
	
}
