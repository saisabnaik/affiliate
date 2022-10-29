package com.affiliate.product;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="product")
public class Product {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id", nullable = false, unique = true)
	private Long productId;
	
	@Column(name = "product_name", nullable = false,length=200)
	private String productname;
	
	@Column(name="description", nullable = false,length = 3000)
	private String description;
	
	@Column(name="price",length=15,nullable = false, precision = 10, scale = 2)
	private double price;

	@Column(name="quantity",length=15,nullable = false)
	private int quantity;
	
	@Column(name="link",length=500,nullable =true)
	private String link;

	@Column(name="category",length=200)
	private String category;

    @Column(name="vender_id",length=10)
    private int vid;
    
	@Lob
    @Column(name = "Image", length = Integer.MAX_VALUE)
    private byte[] image;
      
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    public Product() {
		super();
	}
    
    
	public Long getProductId() {
		return productId;
	}

	public String getProductname() {
		return productname;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getLink() {
		return link;
	}

	public String getCategory() {
		return category;
	}

	public int getVid() {
		return vid;
	}

	public byte[] getImage() {
		return image;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productname=" + productname + ", description=" + description
				+ ", price=" + price + ", quantity=" + quantity + ", link=" + link + ", category=" + category + ", vid="
				+ vid + ", image=" + Arrays.toString(image) + ", createDate=" + createDate + "]";
	}

	
    
    
}
