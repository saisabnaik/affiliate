package com.affiliate.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(3)
public class CustomerSecurityConfig  extends WebSecurityConfigurerAdapter{

	@Bean
	public UserDetailsService customerUserDetailsService() {
		return new CustomerUserDetailsService();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider2() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(customerUserDetailsService());
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());

		return authProvider;
	}

	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authenticationProvider(authenticationProvider2());
		
		http.antMatcher("/customer/**")
			.authorizeRequests().anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage("/customer/login")
					.usernameParameter("email")
					.loginProcessingUrl("/customer/dologin")
					.defaultSuccessUrl("/customer/home")
				.permitAll()
				.and()
				.logout().clearAuthentication(true)
					.logoutUrl("/customer/logout")
					.logoutSuccessUrl("/")
					.and().csrf().disable();
	}
	
}
