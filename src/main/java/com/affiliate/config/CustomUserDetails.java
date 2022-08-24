package com.affiliate.config;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.affiliate.bean.MyUser;

public class CustomUserDetails implements UserDetails  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private MyUser u;
	
	

	public CustomUserDetails(MyUser u) {
		super();
		this.u = u;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		HashSet<GrantedAuthority> set = new HashSet<GrantedAuthority>();
		set.add(new SimpleGrantedAuthority(u.getRole()));
		return set;
	}

	public String getPassword() {
		
		return u.getPassword();
	}

	public String getUsername() {
		
		return u.getEmail();
	}

	public boolean isAccountNonExpired() {
		
		return true;
	}

	public boolean isAccountNonLocked() {
		
		return true;
	}

	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	public boolean isEnabled() {
		
		return true;
	}

}
