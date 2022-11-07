package com.affiliate.product;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product_category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id",length=200)
	
	private Long categoryId;
	@Column(name="category_title",length=200)
	private String categoryTitle;
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY,  mappedBy = "productId")
	private List<Product> products;

	
	public Category() {
		super();
	}


	public Long getCategoryId() {
		return categoryId;
	}


	public String getCategoryTitle() {
		return categoryTitle;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}


	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
	
}
