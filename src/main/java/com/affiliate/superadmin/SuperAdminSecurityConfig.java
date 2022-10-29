package com.affiliate.superadmin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Configuration
@Order(1)
public class SuperAdminSecurityConfig  extends WebSecurityConfigurerAdapter{

	@Bean
	public UserDetailsService superAdminDetailsService() {
		return new SuperAdminDetailsService();
	}


	@Bean
	public DaoAuthenticationProvider authenticationProvider3() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(superAdminDetailsService());
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());

		return authProvider;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authenticationProvider(authenticationProvider3());

		http.authorizeRequests().antMatchers("/").permitAll();

		http.antMatcher("/super-admin/**").authorizeRequests().anyRequest().authenticated().and().formLogin()
				.loginPage("/super-admin/login").usernameParameter("email").loginProcessingUrl("/super-admin/dologin")
				.defaultSuccessUrl("/super-admin/dashboard").permitAll().and().logout().logoutUrl("/super-admin/logout")
				.logoutSuccessUrl("/").and().csrf().disable();

	}

}
