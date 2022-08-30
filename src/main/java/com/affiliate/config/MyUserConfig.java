package com.affiliate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.affiliate.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class MyUserConfig  extends WebSecurityConfigurerAdapter{

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}

	@Bean
	public UserDetailsService getUserDetailService() {
		return new CustomUserDetailsService();
		
	}
	
	@Bean
	public BCryptPasswordEncoder password() {
		return new BCryptPasswordEncoder();
	}
	
	public DaoAuthenticationProvider daoProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(getUserDetailService());
		dao.setPasswordEncoder(password());
		return dao;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoProvider() );
		//super.configure(auth);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/user/**").hasRole("USER")
		.antMatchers("/registerUser").permitAll().antMatchers("/user/profile-details/**")
		.hasAnyAuthority("ADMIN", "USER").antMatchers("/delete/**").hasAuthority("ADMIN")
		.antMatchers("/**").permitAll()
		.and().formLogin().loginPage("/")
		.loginProcessingUrl("/dologin").defaultSuccessUrl("/user/home")
		.and().exceptionHandling().accessDeniedPage("/error")
		.and().csrf().disable();
		
		
		
		
		//super.configure(http);
	}

}
