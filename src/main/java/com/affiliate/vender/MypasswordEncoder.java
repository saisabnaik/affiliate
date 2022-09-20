package com.affiliate.vender;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class MypasswordEncoder {

	
	@Bean
	public BCryptPasswordEncoder passwordEncoder1() {
		return new BCryptPasswordEncoder();
	}
	
}
