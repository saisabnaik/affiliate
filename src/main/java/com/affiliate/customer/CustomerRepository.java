package com.affiliate.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public Customer findByEmail(String email);
	public boolean findByAffiliateId(String afid);
	public void deleteByUserid(Long affiliateid);
	public Customer findByUserid(Long id);
	
}
