package com.affiliate.vender;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenderRepository extends JpaRepository<Vender, Integer> {
	public Vender findByEmail(String email);	
}
