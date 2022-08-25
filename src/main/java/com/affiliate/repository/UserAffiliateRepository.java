package com.affiliate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.affiliate.bean.MyAffiliate;

public interface UserAffiliateRepository extends JpaRepository<MyAffiliate, Long>{

	public List<MyAffiliate> findByUserid(Long userid);
	
}
