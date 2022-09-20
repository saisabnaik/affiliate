/*
package com.affiliate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.affiliate.vender.Vender;
import com.affiliate.vender.VenderRepository;

@Configuration
public class DatabaseLoader {

	@Autowired
	private BCryptPasswordEncoder bp;
	
	private VenderRepository venderRepo;

	
	public DatabaseLoader(VenderRepository venderRepo) {
		this.venderRepo = venderRepo;
		
	}



	@Bean
	public CommandLineRunner initializeDatabase() {
		return args -> {
			
			Vender user1 = new Vender("david","Beckham","david@gmail.com", bp.encode("david123"),"0987654321");
			Vender user2 = new Vender("john","Wick","john@gmail.com", bp.encode("john2020"),"1234567890");
venderRepo.save(user1);
venderRepo.save(user2);
			
			//venderRepo.saveAll(List.of(user1, user2));
			
			System.out.println("Database initialized");
		};
	}
}
*/