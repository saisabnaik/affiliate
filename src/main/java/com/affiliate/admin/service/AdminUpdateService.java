package com.affiliate.admin.service;

import java.security.Principal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.affiliate.admin.MyAdmin;
import com.affiliate.customer.Customer;

public interface AdminUpdateService {
	
	public MyAdmin updateProfile(MyAdmin myadmin, Principal principal, MultipartFile profileImage) throws Exception;
	
	public MyAdmin changeMyPassword(String oldPassword, String password,Principal principal) throws Exception ;
	
	public List<Customer> getAllCustomer();

}
