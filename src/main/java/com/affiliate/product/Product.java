package com.affiliate.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pId;
	@Column(length = 200)
	private String pName;
	@Column(length = 3000)
	private String pDesc;
	private String pPic;
	private int pPrice;
	private int pDiscount;
	private int pQuantity;

    @ManyToOne
    @JoinColumn(name="cId")
	private Category category;

    
    
    
	public Product() {
		super();
	}

	public Product(Long pId, String pName, String pDesc, String pPic, int pPrice, int pDiscount, int pQuantity,
			Category category) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pDesc = pDesc;
		this.pPic = pPic;
		this.pPrice = pPrice;
		this.pDiscount = pDiscount;
		this.pQuantity = pQuantity;
		this.category = category;
	}

	public Long getpId() {
		return pId;
	}

	public String getpName() {
		return pName;
	}

	public String getpDesc() {
		return pDesc;
	}

	public String getpPic() {
		return pPic;
	}

	public int getpPrice() {
		return pPrice;
	}

	public int getpDiscount() {
		return pDiscount;
	}

	public int getpQuantity() {
		return pQuantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public void setpDesc(String pDesc) {
		this.pDesc = pDesc;
	}

	public void setpPic(String pPic) {
		this.pPic = pPic;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public void setpDiscount(int pDiscount) {
		this.pDiscount = pDiscount;
	}

	public void setpQuantity(int pQuantity) {
		this.pQuantity = pQuantity;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
    
    
    
	
}
