package com.affiliate.vender;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vender")
public class Vender {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="vender_id")
	private int venderId;
	
	@Column(name="first_name",length=50)
	private String firstname;
	
	@Column(name="last_name",length=50)
	private String lastname;
	
	@Column(name="email",length=150, nullable=false,unique=true)
	private String email;
	
	@Column(name="password",length=280,nullable=false)
	private String password;
	
	@Column(name="mobile",length=16)
	private String mobile;
	
	
	public Vender() {
		super();
	}

	
	public Vender(String firstname, String lastname, String email, String password, String mobile) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
	}


	public int getAdminid() {
		return venderId;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public void setAdminid(int venderId) {
		this.venderId = venderId;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}	

	
	
}
