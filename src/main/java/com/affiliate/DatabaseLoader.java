package com.affiliate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class DatabaseLoader {
/*
	@Autowired
	private BCryptPasswordEncoder bp;
	
	private AdminRepository adminRepo;

	public DatabaseLoader(AdminRepository adminRepo) {
		this.adminRepo = adminRepo;
		
	}
*/
/*
	@Bean
	public CommandLineRunner initializeDatabase() {
		return args -> {
			
			MyAdmin user1 = new MyAdmin("jack","dosn","jack@gmail.com", bp.encode("Jack@123"),"1234567890");
			MyAdmin user2 = new MyAdmin("leo","dec","leo@gmail.com", bp.encode("Leo@123"),"7894561230");
			adminRepo.saveAll(List.of(user1, user2));		
			
			System.out.println("Database initialized");
		};
	}
	*/
	
}
