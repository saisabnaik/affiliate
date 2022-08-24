package com.affiliate.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "myaffiliate")
public class MyAffiliate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long affiliateId;
	

	@Column(name="first_name",length=50)
	private String firstname;
	
	@Column(name="last_name",length=50)
	private String lastname;
	
	@Column(name="email",length=50)
	private String email;
	
	@Column(name="mobile",length=15)
	private String mobile;

	
	@Column(name="new_date",length=15)
	private String newdate;
	
	@Column(name="poduct_image",length=15)
	private String productImage;

	public Long getAffiliateId() {
		return affiliateId;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	public String getMobile() {
		return mobile;
	}

	public String getNewdate() {
		return newdate;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setAffiliateId(Long affiliateId) {
		this.affiliateId = affiliateId;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setNewdate(String newdate) {
		this.newdate = newdate;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	
	
	
}
