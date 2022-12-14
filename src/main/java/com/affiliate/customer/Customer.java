package com.affiliate.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userid",length=10)
	private Long userid;
	
	@Column(name="firstname",length=50)
	private String firstname;
	
	@Column(name="lastname",length=50)
	private String lastname;
	
	@Column(name="email",length=100,unique=true,nullable = false)
	private String email;
	
	@Column(name="password",length=280,nullable = false)
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
	
	@Column(name="mobile",length=16)
	private String mobile;

	@Lob
    @Column(name = "image", length = Integer.MAX_VALUE)
    private byte[] image;
		
	private String otp;
	
	private String DateTime;
	
	private String paidTotalEarnings;
	
	private String unpaidTotalEarnings;
	
	private String unpaidTotalReferals;
	private String paidTotalReferals;
	
	private String totalSharedReferals;
	private String status;
	
	@Column(name="affiliate_id",length=10)
	private String affiliateId;

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

	

	public String getOtp() {
		return otp;
	}

	public String getDateTime() {
		return DateTime;
	}

	public String getPaidTotalEarnings() {
		return paidTotalEarnings;
	}

	public String getUnpaidTotalEarnings() {
		return unpaidTotalEarnings;
	}

	public String getUnpaidTotalReferals() {
		return unpaidTotalReferals;
	}

	public String getPaidTotalReferals() {
		return paidTotalReferals;
	}

	public String getTotalSharedReferals() {
		return totalSharedReferals;
	}

	public String getStatus() {
		return status;
	}

	public String getAffiliateId() {
		return affiliateId;
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



	public void setOtp(String otp) {
		this.otp = otp;
	}

	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}

	public void setPaidTotalEarnings(String paidTotalEarnings) {
		this.paidTotalEarnings = paidTotalEarnings;
	}

	public void setUnpaidTotalEarnings(String unpaidTotalEarnings) {
		this.unpaidTotalEarnings = unpaidTotalEarnings;
	}

	public void setUnpaidTotalReferals(String unpaidTotalReferals) {
		this.unpaidTotalReferals = unpaidTotalReferals;
	}

	public void setPaidTotalReferals(String paidTotalReferals) {
		this.paidTotalReferals = paidTotalReferals;
	}

	public void setTotalSharedReferals(String totalSharedReferals) {
		this.totalSharedReferals = totalSharedReferals;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAffiliateId(String affiliateId) {
		this.affiliateId = affiliateId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	/*
	 * @OneToMany(targetEntity = CustomerMyAffiliate.class ,fetch =
	 * FetchType.LAZY,cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name="myuser_fk", referencedColumnName = "userid") private
	 * List<CustomerMyAffiliate> affiliateList;
	 */

	
	
}
