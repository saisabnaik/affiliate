
package com.affiliate;

import org.springframework.beans.factory.annotation.Autowired;
/*
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.affiliate.superadmin.SuperAdminRepository;
import com.affiliate.superadmin.Superadmin;

@Configuration
public class DatabaseLoader {

	@Autowired
	private BCryptPasswordEncoder bp;
	
	private SuperAdminRepository adminRepo;

	
	public DatabaseLoader(SuperAdminRepository adminRepo) {
		this.adminRepo = adminRepo;
		
	}



	@Bean
	public CommandLineRunner initializeDatabase() {
		return args -> {
			
			//Superadmin superamdin = new Superadmin("david","Beckham","david@gmail.com", bp.encode("david123"),"0987654321");
			Superadmin superadmin=new Superadmin();
			superadmin.setAdmin_firstName("Sourav");
			superadmin.setAdmin_lastName("sir");
			superadmin.setAdminemail("sourav@gmail.com");
			superadmin.setAdmin_phone("123456789");
			superadmin.setSuper_admin_password(bp.encode("sourav@123"));
			
			adminRepo.save(superadmin);

			
			//venderRepo.saveAll(List.of(user1, user2));
			
			System.out.println("Database initialized");
		};
	}
}
*/