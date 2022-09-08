package com.affiliate.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
	@Autowired private AdminRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		MyAdmin myAdmin = repo.findByEmail(email);
		if (myAdmin == null) {
			throw new UsernameNotFoundException("No user found with the given email");
		}
		
		return new CustomUserDetails(myAdmin);
	}

}
