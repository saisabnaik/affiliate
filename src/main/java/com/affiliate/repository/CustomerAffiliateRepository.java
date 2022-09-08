package com.affiliate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.affiliate.bean.CustomerMyAffiliate;

public interface CustomerAffiliateRepository extends JpaRepository<CustomerMyAffiliate, Long>{
	
}
