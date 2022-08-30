package com.affiliate.bean;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="myuser")
public class MyUser {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userid",length=10)
	private Long userid;
	
	
	@Column(name="firstname",length=50)
	private String firstname;
	
	@Column(name="lastname",length=50)
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


	@Lob
	@Column(name="image", length=500)
	private Byte[] image;
	
	private String role;
	
	private String otp;
	
	private String DateTime;

	
	@OneToMany(targetEntity = MyAffiliate.class ,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="myuser_fk", referencedColumnName = "userid")
	private List<MyAffiliate> affiliateList;


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


	public Byte[] getImage() {
		return image;
	}


	public String getRole() {
		return role;
	}


	public String getOtp() {
		return otp;
	}


	public String getDateTime() {
		return DateTime;
	}


	public List<MyAffiliate> getAffiliateList() {
		return affiliateList;
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


	public void setImage(Byte[] image) {
		this.image = image;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public void setOtp(String otp) {
		this.otp = otp;
	}


	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}


	public void setAffiliateList(List<MyAffiliate> affiliateList) {
		this.affiliateList = affiliateList;
	}
	
	

	
}
