package com.affiliate.admin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<MyAdmin, Integer> {
	public MyAdmin findByEmail(String email);	
}
