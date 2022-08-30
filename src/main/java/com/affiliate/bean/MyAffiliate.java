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
public class MyAffiliate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long affiliateId;

	@Column(name="product_name",length=150)
	private String productName;
	
	@Column(name="product_serial_no")
	private String productSerialNo;
	
	@Column(name="product_links",length=250)
	private String productLinks;
	

	@Column(name="created_date",length=30)
	private String createdDate;

	@Column(name="product_image",length=500)
	private String productImage;

	@Column(name="price_of_product")
	private long priceOfProduct;
	
	@Column(name="total_shared_links",length=10)
	private Long totalSharedLinks;

	
	@Column(name="total_sold")
	private long totalSold;
	
	@Column(name="commission")
	private String commission;
	
	@Column(name="subtotal")
	private long subTotal;

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

	public String getCreatedDate() {
		return createdDate;
	}

	@Lob
	public String getProductImage() {
		return productImage;
	}

	public long getPriceOfProduct() {
		return priceOfProduct;
	}

	public Long getTotalSharedLinks() {
		return totalSharedLinks;
	}

	public long getTotalSold() {
		return totalSold;
	}

	public String getCommission() {
		return commission;
	}

	public long getSubTotal() {
		return subTotal;
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

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public void setPriceOfProduct(long priceOfProduct) {
		this.priceOfProduct = priceOfProduct;
	}

	public void setTotalSharedLinks(Long totalSharedLinks) {
		this.totalSharedLinks = totalSharedLinks;
	}

	public void setTotalSold(long totalSold) {
		this.totalSold = totalSold;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public void setSubTotal(long subTotal) {
		this.subTotal = subTotal;
	}
	

	
}
