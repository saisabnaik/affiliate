package com.affiliate.superadmin;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SuperAdminDetails implements UserDetails {
	private Superadmin admin;

	public SuperAdminDetails(Superadmin admin) {
		this.admin = admin;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return null;
	}

	@Override
	public String getPassword() {

		return admin.getSuper_admin_password();
	}

	@Override
	public String getUsername() {

		return admin.getAdminemail();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


}
