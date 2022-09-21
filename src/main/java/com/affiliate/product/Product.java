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
	private Long pid;
	@Column(length = 200)
	private String pname;
	@Column(length = 3000)
	private String pdesc;
	private String ppic;
	private int pprice;
	private int pdiscount;
	private int pquantity;

    @ManyToOne
    @JoinColumn(name="cid")
	private Category category;

    //constructor
	public Product() {
		super();
	}
	

	public Product(Long pid, String pname, String pdesc, String ppic, int pprice, int pdiscount, int pquantity,
			Category category) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pdesc = pdesc;
		this.ppic = ppic;
		this.pprice = pprice;
		this.pdiscount = pdiscount;
		this.pquantity = pquantity;
		this.category = category;
	}



	public Long getPid() {
		return pid;
	}

	public String getPname() {
		return pname;
	}

	public String getPdesc() {
		return pdesc;
	}

	public String getPpic() {
		return ppic;
	}

	public int getPprice() {
		return pprice;
	}

	public int getPdiscount() {
		return pdiscount;
	}

	public int getPquantity() {
		return pquantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public void setPpic(String ppic) {
		this.ppic = ppic;
	}

	public void setPprice(int pprice) {
		this.pprice = pprice;
	}

	public void setPdiscount(int pdiscount) {
		this.pdiscount = pdiscount;
	}

	public void setPquantity(int pquantity) {
		this.pquantity = pquantity;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

    
   
}
