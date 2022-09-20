package com.affiliate.vender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
	@Autowired private VenderRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Vender vender = repo.findByEmail(email);
		if (vender == null) {
			throw new UsernameNotFoundException("No user found with the given email");
		}
		
		return new CustomUserDetails(vender);
	}

}
