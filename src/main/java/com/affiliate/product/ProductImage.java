package com.affiliate.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product_image")
public class ProductImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_image_id")
	private Long productImageId;
	
	@Column(name="product_image_name",length=200)
	private String productImageName;

	@Column(name="product_fk_id",length=15)
	private Long productId;
	
	
	//default constructor
	public ProductImage() {
		super();
		
	}

	public ProductImage(Long productImageId, String productImageName,Long productId) {
		super();
		this.productImageId = productImageId;
		this.productImageName = productImageName;
		this.productId = productId;
	}

	public Long getProductImageId() {
		return productImageId;
	}

	public String getProductImageName() {
		return productImageName;
	}

	public void setProductImageId(Long productImageId) {
		this.productImageId = productImageId;
	}

	public void setProductImageName(String productImageName) {
		this.productImageName = productImageName;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	

	
}
