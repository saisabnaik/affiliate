package com.affiliate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.affiliate.bean.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmail(String email);
	
}
