package com.affiliate.adminBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int adminId;
	
	@Column(name="firstname",length=50)
	private String firstname;
	
	@Column(name="lastname",length=50)
	private String lastname;
	
	private String email;
	
	@Column(name="password",length=280)
	private String password;
	
	@Lob
	@Column(name="image", length=500)
	private Byte[] image;
	
	private String role;

	public int getAdminId() {
		return adminId;
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

	public Byte[] getImage() {
		return image;
	}

	public String getRole() {
		return role;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
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

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	
}
