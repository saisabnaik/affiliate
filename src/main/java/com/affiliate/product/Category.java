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
	private Long cid;
	@Column(length=200)
	private String ctitle;
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY,  mappedBy = "pid")
	private List<Product> products;

	
	public Category() {
		super();
	}

	public Category(Long cid, String ctitle, List<Product> products) {
		super();
		this.cid = cid;
		this.ctitle = ctitle;
		this.products = products;
	}

	public Long getCid() {
		return cid;
	}

	public String getCtitle() {
		return ctitle;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
	
}
