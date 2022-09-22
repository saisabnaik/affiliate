package com.affiliate.customer.service;

import java.security.Principal;

import org.springframework.web.multipart.MultipartFile;

import com.affiliate.customer.Customer;

public interface UserUpdate {

	public Customer updateProfile(Customer myuser, Principal principal, MultipartFile profileImage) throws Exception;
	
	public Customer changeMyPassword(String oldPassword, String password,	Principal principal) throws Exception ;
}
