package com.affiliate.superadmin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminRepository extends JpaRepository<Superadmin, Integer> {
	public Superadmin findByAdminemail(String email);
}
