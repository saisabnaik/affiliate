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

	@Column(name="product_name",length=150)
	private String productName;
	
	@Column(name="product_links",length=250)
	private String productLinks;
	

	@Column(name="created_date",length=30)
	private String createdDate;

	@Column(name="product_image",length=500)
	private String productImage;

	@Column(name="total_shared_links",length=10)
	private Long totalSharedLinks;

	

	
	public Long getAffiliateId() {
		return affiliateId;
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


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public void setProductLinks(String productLinks) {
		this.productLinks = productLinks;
	}


	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}


	
	public String getProductImage() {
		return productImage;
	}



	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}



	public void setTotalSharedLinks(Long totalSharedLinks) {
		this.totalSharedLinks = totalSharedLinks;
	}


	@Override
	public String toString() {
		return "MyAffiliate [affiliateId=" + affiliateId + ", productName=" + productName
				+ ", productLinks=" + productLinks + ", createdDate=" + createdDate + ", totalSharedLinks="
				+ totalSharedLinks + "]";
	}
	

	
	
}
