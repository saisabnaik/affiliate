package com.affiliate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.affiliate.model.MyAffiliate;

public interface AffiliateRepository extends JpaRepository<MyAffiliate, Long>{
	public MyAffiliate findByGeneratedLinks(String generatedLinks);
	public List<MyAffiliate> findAllByAffiliateid(String affiliateid);
	public List<MyAffiliate> findAllByVenderid(int vid);
}
