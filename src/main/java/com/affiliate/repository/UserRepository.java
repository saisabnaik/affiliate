package com.affiliate.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.affiliate.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmail(String email);
	
	//firstname, lastname, contactnumber, gender,address,country,state, city, postalzip
	//@Modifying(clearAutomatically = true)
	//@Query("update RssFeedEntry feedEntry set feedEntry.read =:isRead where feedEntry.id =:entryId")
	//void markEntryAsRead(@Param("entryId") Long rssFeedEntryId, @Param("isRead") boolean isRead);
	
	@Transactional
	@Modifying
	@Query("UPDATE User feedUser SET feedUser.firstname=:firstname, feedUser.lastname=:lastname, feedUser.mobile=:mobile, feedUser.gender=:gender,feedUser.address=:address,feedUser.country=:country,feedUser.state=:state,feedUser.city=:city,feedUser.zip=:zip WHERE feedUser.email=:email")
	public void update(@Param("firstname") String firstname, @Param("lastname") String lastname, @Param("mobile") String mobile, @Param("gender") String gender, @Param("address") String address , @Param("country") String country, @Param("state") String state , @Param("city") String city, @Param("zip") Long zip, @Param("email") String email);
	
}
