package com.affiliate.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.affiliate.bean.User;
import com.affiliate.config.CustomUserDetails;
import com.affiliate.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{
    
	@Autowired
	private UserRepository repo;
	
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		try {
			
			User u = repo.findByEmail(email);
			
			if(u==null) {
				throw new UsernameNotFoundException("User not found.");
			}else {
				
				return new CustomUserDetails(u);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
