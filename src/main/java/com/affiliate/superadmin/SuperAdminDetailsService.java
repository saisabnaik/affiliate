package com.affiliate.superadmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SuperAdminDetailsService implements UserDetailsService {
	@Autowired
	private SuperAdminRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Superadmin super_admin = repository.findByAdminemail(email);
		if (super_admin == null) {
			throw new UsernameNotFoundException("No user found with the given email");
		}

		return new SuperAdminDetails(super_admin);
	}

}
