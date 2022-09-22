package com.affiliate.customer.serviceimpl;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.affiliate.customer.Customer;
import com.affiliate.customer.CustomerRepository;
import com.affiliate.customer.service.UserUpdate;

@Service
public class UserUpdateAndChangePassword implements UserUpdate{

	@Autowired
	private CustomerRepository userRepository;

	@Autowired private BCryptPasswordEncoder bp;
	
	//update user data from setting page...
	
	@Override
	public Customer updateProfile(Customer myuser, Principal principal, MultipartFile profileImage) throws Exception{
		Byte[] byteObjects = convertToBytes(profileImage);
		
		Customer currentUser = this.userRepository.findByEmail(principal.getName());
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
	@Override
	public Customer changeMyPassword(String oldPassword, String password,	Principal principal) throws Exception {	
		Customer currentUser = this.userRepository.findByEmail(principal.getName());
		if(this.bp.matches(oldPassword, currentUser.getPassword())) {
			
			currentUser.setPassword(this.bp.encode(password));
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
