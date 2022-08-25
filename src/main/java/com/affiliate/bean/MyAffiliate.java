package com.affiliate.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "myaffiliate")
public class MyAffiliate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long affiliateId;
	
	@Column(name="user_id",length=10)
	private Long userid;

	@Column(name="product_name",length=100)
	private String productName;
	
	@Column(name="product_links",length=50)
	private String productLinks;
	

	@Column(name="created_date",length=15)
	private String createdDate;


	@Column(name="total_shared_links",length=10)
	private Long totalSharedLinks;

	@ManyToOne(fetch = FetchType.LAZY)
	private MyUser myAffiliateUser;
	
	

	public Long getAffiliateId() {
		return affiliateId;
	}


	public Long getUserid() {
		return userid;
	}


	public String getProductName() {
		return productName;
	}


	public String getProductLinks() {
		return productLinks;
	}


	public String getCreatedDate() {
		return createdDate;
	}


	public Long getTotalSharedLinks() {
		return totalSharedLinks;
	}


	public void setAffiliateId(Long affiliateId) {
		this.affiliateId = affiliateId;
	}


	public void setUserid(Long userid) {
		this.userid = userid;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public void setProductLinks(String productLinks) {
		this.productLinks = productLinks;
	}


	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}


	public void setTotalSharedLinks(Long totalSharedLinks) {
		this.totalSharedLinks = totalSharedLinks;
	}


	@Override
	public String toString() {
		return "MyAffiliate [affiliateId=" + affiliateId + ", userid=" + userid + ", productName=" + productName
				+ ", productLinks=" + productLinks + ", createdDate=" + createdDate + ", totalSharedLinks="
				+ totalSharedLinks + "]";
	}
	

	
	
}
