package com.affiliate.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="myuser")
public class MyUser {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id",length=10)
	private Long userid;
	
	
	@Column(name="first_name",length=50)
	private String firstname;
	
	@Column(name="last_name",length=50)
	private String lastname;
	
	@Column(name="email",length=50,unique=true)
	private String email;
	
	@Column(name="password",length=280)
	private String password;
	
	@Column(name="gender",length=10)
	private String gender;
	
	@Column(name="city",length=50)
	private String city;
	
	@Column(name="country",length=50)
	private String country;
	
	@Column(name="zip",length=20)
	private Long zip;
	
	@Column(name="address",length=280)
	private String address;
	
	@Column(name="state",length=50)
	private String state;
	
	
	@Column(name="mobile",length=15)
	private String mobile;

	
	private String role;

	@Column(name="image", length=280)
	private String image;

	public Long getUserid() {
		return userid;
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


	public String getGender() {
		return gender;
	}


	public String getCity() {
		return city;
	}


	public String getCountry() {
		return country;
	}


	public Long getZip() {
		return zip;
	}


	public String getAddress() {
		return address;
	}


	public String getState() {
		return state;
	}


	public String getMobile() {
		return mobile;
	}


	public String getRole() {
		return role;
	}


	public void setUserid(Long userid) {
		this.userid = userid;
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


	public void setGender(String gender) {
		this.gender = gender;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public void setZip(Long zip) {
		this.zip = zip;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public void setState(String state) {
		this.state = state;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "MyUser [userid=" + userid + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + ", gender=" + gender + ", city=" + city + ", country=" + country + ", zip="
				+ zip + ", address=" + address + ", state=" + state + ", mobile=" + mobile + ", role=" + role + "]";
	}
	
	
	
	
}