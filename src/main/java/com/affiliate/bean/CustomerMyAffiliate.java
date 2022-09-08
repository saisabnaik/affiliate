package com.affiliate.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "myaffiliate")
public class CustomerMyAffiliate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long affiliateId;

	@Column(name="product_name",length=150)
	private String productName;
	
	@Column(name="product_serial_no")
	private String productSerialNo;
	
	@Column(name="product_links",length=250)
	private String productLinks;
	
	@Column(name="generated_links", length=500)
	private String generatedLinks;
	
	@Column(name="created_date",length=30)
	private String createdDate;

	@Column(name="product_image",length=500)
	private String productImage;

	@Column(name="price_of_product")
	private long priceOfProduct;
	
	
	@Column(name="total_sold")
	private long totalSold;
	
	@Column(name="Rate")
	private String rate;
	
	@Column(name="total_earnings")
	private long totalEarnings;

	public Long getAffiliateId() {
		return affiliateId;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductSerialNo() {
		return productSerialNo;
	}

	public String getProductLinks() {
		return productLinks;
	}

	public String getGeneratedLinks() {
		return generatedLinks;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public String getProductImage() {
		return productImage;
	}

	public long getPriceOfProduct() {
		return priceOfProduct;
	}

	public long getTotalSold() {
		return totalSold;
	}

	public String getRate() {
		return rate;
	}

	public long getTotalEarnings() {
		return totalEarnings;
	}

	public void setAffiliateId(Long affiliateId) {
		this.affiliateId = affiliateId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductSerialNo(String productSerialNo) {
		this.productSerialNo = productSerialNo;
	}

	public void setProductLinks(String productLinks) {
		this.productLinks = productLinks;
	}

	public void setGeneratedLinks(String generatedLinks) {
		this.generatedLinks = generatedLinks;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public void setPriceOfProduct(long priceOfProduct) {
		this.priceOfProduct = priceOfProduct;
	}

	public void setTotalSold(long totalSold) {
		this.totalSold = totalSold;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public void setTotalEarnings(long totalEarnings) {
		this.totalEarnings = totalEarnings;
	}

	
	
}
