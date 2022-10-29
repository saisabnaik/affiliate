package com.affiliate.model;

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
	private Long id;
	
	private String affiliateid;
	private Long productId;
	
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

	@Column(name="price_of_product")
	private double priceOfProduct;
	
	@Column(name="total_sold")
	private long totalSold;
	
	@Column(name="Rate")
	private long rate;
	
	@Column(name="paid_referals")
	private long paidreferals;
	
	@Column(name="unpaid_referals")
	private long unpaidreferals;
	
	@Column(name="paid_earnings")
	private long paidearning;
	
	@Column(name="unpaid_earnings")
	private long unpaidearning;
	
	
	@Column(name="vender_id")
	private int venderid;


	public Long getId() {
		return id;
	}


	public String getAffiliateid() {
		return affiliateid;
	}


	public Long getProductId() {
		return productId;
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


	public double getPriceOfProduct() {
		return priceOfProduct;
	}


	public long getTotalSold() {
		return totalSold;
	}


	public long getRate() {
		return rate;
	}


	public long getPaidreferals() {
		return paidreferals;
	}


	public long getUnpaidreferals() {
		return unpaidreferals;
	}


	public long getPaidearning() {
		return paidearning;
	}


	public long getUnpaidearning() {
		return unpaidearning;
	}


	public int getVenderid() {
		return venderid;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setAffiliateid(String affiliateid) {
		this.affiliateid = affiliateid;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
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


	public void setPriceOfProduct(double priceOfProduct) {
		this.priceOfProduct = priceOfProduct;
	}


	public void setTotalSold(long totalSold) {
		this.totalSold = totalSold;
	}


	public void setRate(long rate) {
		this.rate = rate;
	}


	public void setPaidreferals(long paidreferals) {
		this.paidreferals = paidreferals;
	}


	public void setUnpaidreferals(long unpaidreferals) {
		this.unpaidreferals = unpaidreferals;
	}


	public void setPaidearning(long paidearning) {
		this.paidearning = paidearning;
	}


	public void setUnpaidearning(long unpaidearning) {
		this.unpaidearning = unpaidearning;
	}


	public void setVenderid(int venderid) {
		this.venderid = venderid;
	}
	
	
}
