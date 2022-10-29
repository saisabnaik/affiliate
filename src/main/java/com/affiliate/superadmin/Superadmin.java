package com.affiliate.superadmin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="super_admin")
@Entity
public class Superadmin {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int admin_id;
	@Column(length=150,unique=true)
	
	private String admin_firstName;
	@Column(length=150)
	private String admin_lastName;
	@Column(length=150)
	
	private String adminemail;
	@Column(length=300,unique=true)
	private String super_admin_password;
	@Column(length=14,unique=true)
	private String admin_phone;
	
	public Superadmin() {
		super();
	
	}
	

	public int getAdmin_id() {
		return admin_id;
	}

	public String getAdmin_firstName() {
		return admin_firstName;
	}

	public String getAdmin_lastName() {
		return admin_lastName;
	}

	public String getAdminemail() {
		return adminemail;
	}

	public String getSuper_admin_password() {
		return super_admin_password;
	}

	public String getAdmin_phone() {
		return admin_phone;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public void setAdmin_firstName(String admin_firstName) {
		this.admin_firstName = admin_firstName;
	}

	public void setAdmin_lastName(String admin_lastName) {
		this.admin_lastName = admin_lastName;
	}

	public void setAdminemail(String adminemail) {
		this.adminemail = adminemail;
	}

	public void setSuper_admin_password(String super_admin_password) {
		this.super_admin_password = super_admin_password;
	}

	public void setAdmin_phone(String admin_phone) {
		this.admin_phone = admin_phone;
	}
	
	
	
}
