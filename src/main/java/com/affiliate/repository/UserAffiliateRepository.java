package com.affiliate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.affiliate.bean.MyAffiliate;

public interface UserAffiliateRepository extends JpaRepository<MyAffiliate, Long>{
	
}
