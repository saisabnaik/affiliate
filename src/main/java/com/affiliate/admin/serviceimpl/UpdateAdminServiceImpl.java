package com.affiliate.admin.serviceimpl;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.affiliate.admin.AdminRepository;
import com.affiliate.admin.MyAdmin;
import com.affiliate.admin.service.AdminUpdateService;
import com.affiliate.customer.Customer;
import com.affiliate.customer.CustomerRepository;

@Service
public class UpdateAdminServiceImpl implements AdminUpdateService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired private BCryptPasswordEncoder bp;

	@Override
	public MyAdmin updateProfile(MyAdmin myadmin, Principal principal, MultipartFile profileImage) throws Exception {
		//Byte[] byteObjects = convertToBytes(profileImage);

		MyAdmin currentAdmin = this.adminRepository.findByEmail(principal.getName());
		currentAdmin.setFirstname(myadmin.getFirstname());
		currentAdmin.setLastname(myadmin.getLastname());
		currentAdmin.setMobile(myadmin.getMobile());

		//currentAdmin.setImage(byteObjects);
		this.adminRepository.save(currentAdmin);
	
		return currentAdmin;
	}

	@Override
	public MyAdmin changeMyPassword(String oldPassword, String password, Principal principal) throws Exception {
		MyAdmin currentAdmin = this.adminRepository.findByEmail(principal.getName());
		if(this.bp.matches(oldPassword, currentAdmin.getPassword())) {
			
			currentAdmin.setPassword(this.bp.encode(password));
			this.adminRepository.save(currentAdmin);
			System.out.println("password changed successfully");
		}
		
		return currentAdmin;
	}

	@Override
	public List<Customer> getAllCustomer() {
		return (List<Customer>) this.customerRepository.findAll();
	}
	
	/*
	 private Byte[] convertToBytes(MultipartFile file) throws IOException {
	        Byte[] byteObjects = new Byte[file.getBytes().length];
	        int i = 0;
	        for (byte b : file.getBytes()) {
	            byteObjects[i++] = b;
	        }
	        return byteObjects;
	    }
	
	*/
	
	
	
	
	
}
