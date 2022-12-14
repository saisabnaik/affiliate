package com.affiliate.vender.service;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.affiliate.customer.Customer;
import com.affiliate.vender.Vender;

public interface VenderService {
	
	public Vender updateProfile(Vender vender, Principal principal, MultipartFile profileImage, HttpServletRequest request) throws Exception;
	
	public Vender changeMyPassword(String oldPassword, String password,Principal principal) throws Exception ;
	
	public List<Customer> getAllCustomer();
	

}
