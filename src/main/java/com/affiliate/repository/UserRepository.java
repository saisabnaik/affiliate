package com.affiliate.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.affiliate.bean.MyUser;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {

	public MyUser findByEmail(String email) throws Exception;

	
	
	
	
	@Transactional
	@Modifying
	@Query("UPDATE MyUser feedUser SET feedUser.firstname=:firstname, feedUser.lastname=:lastname, feedUser.mobile=:mobile, feedUser.gender=:gender,feedUser.address=:address,feedUser.country=:country,feedUser.state=:state,feedUser.city=:city,feedUser.zip=:zip WHERE feedUser.email=:email")
	public void updateData(@Param("firstname") String firstname, @Param("lastname") String lastname,
			@Param("mobile") String mobile, @Param("gender") String gender, @Param("address") String address,
			@Param("country") String country, @Param("state") String state, @Param("city") String city,
			@Param("zip") Long zip, @Param("email") String email) throws Exception;


	@Transactional
	@Modifying
	@Query("UPDATE MyUser feedUser SET feedUser.password=:password WHERE feedUser.email=:email")
	public int updatePassword(@Param("password") String password, @Param("email") String email) throws Exception;

	
	
	
	
	
}
