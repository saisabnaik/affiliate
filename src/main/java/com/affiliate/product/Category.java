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
@Table(name="p_category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cId;
	@Column(length=200)
	private String cTitle;
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY,  mappedBy = "pId")
	private List<Product> products;

	
	public Category() {
		super();
	}

	public Category(Long cId, String cTitle, List<Product> products) {
		super();
		this.cId = cId;
		this.cTitle = cTitle;
		this.products = products;
	}

	public Long getcId() {
		return cId;
	}

	public String getcTitle() {
		return cTitle;
	}


	public List<Product> getProducts() {
		return products;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public void setcTitle(String cTitle) {
		this.cTitle = cTitle;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
}
