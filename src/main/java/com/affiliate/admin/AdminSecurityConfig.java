package com.affiliate.admin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(1)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return
	 * NoOpPasswordEncoder.getInstance(); }
	 */
	/*
	 * @Bean
	 * 
	 * @Primary public BCryptPasswordEncoder password1() { return new
	 * BCryptPasswordEncoder(); }
	 */
	

	@Bean
	public DaoAuthenticationProvider authenticationProvider1() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());

		return authProvider;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authenticationProvider(authenticationProvider1());

		http.authorizeRequests().antMatchers("/").permitAll();

		http.antMatcher("/admin/**")
			.authorizeRequests().anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/admin/login")
				.usernameParameter("email")
				.loginProcessingUrl("/admin/dologin")
				.defaultSuccessUrl("/admin/home")
				.permitAll()
				.and()
					.logout()
						.logoutUrl("/admin/logout")
						.logoutSuccessUrl("/logout");

	}

}