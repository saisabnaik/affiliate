package com.affiliate.vender.serviceimpl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.affiliate.customer.Customer;
import com.affiliate.customer.CustomerRepository;
import com.affiliate.vender.Vender;
import com.affiliate.vender.VenderRepository;
import com.affiliate.vender.service.VenderService;


@Service
public class VenderServiceImpl implements VenderService{

	@Autowired
	private VenderRepository venderRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired private BCryptPasswordEncoder bp;

	@Override
	public Vender updateProfile(Vender vender, Principal principal, MultipartFile profileImage) throws Exception {
		//Byte[] byteObjects = convertToBytes(profileImage);

		Vender currentVender = this.venderRepository.findByEmail(principal.getName());
		currentVender.setFirstname(vender.getFirstname());
		currentVender.setLastname(vender.getLastname());
		currentVender.setMobile(vender.getMobile());

		//currentAdmin.setImage(byteObjects);
		this.venderRepository.save(currentVender);
	
		return currentVender;
	}

	@Override
	public Vender changeMyPassword(String oldPassword, String password, Principal principal) throws Exception {
		Vender currentVender = this.venderRepository.findByEmail(principal.getName());
		if(this.bp.matches(oldPassword, currentVender.getPassword())) {
			
			currentVender.setPassword(this.bp.encode(password));
			this.venderRepository.save(currentVender);
			System.out.println("password changed successfully");
		}
		
		return currentVender;
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
