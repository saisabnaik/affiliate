package com.affiliate.customer.serviceimpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.affiliate.customer.Customer;
import com.affiliate.customer.CustomerRepository;
import com.affiliate.customer.service.UserUpdate;

@Service
public class UserUpdateAndChangePassword implements UserUpdate{

	@Autowired
	private CustomerRepository userRepository;

	@Autowired private BCryptPasswordEncoder bp;
	
	@Value("${uploadDir}")
	private String uploadFolder;

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Customer updateProfile(Customer myuser, Principal principal, MultipartFile file,HttpServletRequest request) throws Exception{
		Byte[] byteObjects = convertToBytes(file);
		Customer currentUser = this.userRepository.findByEmail(principal.getName());
		
		if(file.getSize()>0) {
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			if (fileName != null || fileName.contains("..")==false) {
				try {
					File dir = new File(uploadDirectory);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
					stream.write(file.getBytes());
					stream.close();
				} catch (Exception e) {
					log.info("in catch");
					e.printStackTrace();
				}
				byte[] imageData = file.getBytes();
				currentUser.setImage(imageData);
			}
		}
		currentUser.setFirstname(myuser.getFirstname());
		currentUser.setLastname(myuser.getLastname());
		currentUser.setMobile(myuser.getMobile());
		currentUser.setGender(myuser.getGender());
		currentUser.setAddress(myuser.getAddress());
		currentUser.setCountry(myuser.getCountry());
		currentUser.setState(myuser.getState());
		currentUser.setCity(myuser.getCity());
		currentUser.setZip(myuser.getZip());
		
		this.userRepository.save(currentUser);
	
		return currentUser;
	}


	// change user pasword from mysetting page 
	@Override
	public Customer changeMyPassword(String oldPassword, String password,	Principal principal) throws Exception {	
		Customer currentUser = this.userRepository.findByEmail(principal.getName());
		if(this.bp.matches(oldPassword, currentUser.getPassword())) {
			
			currentUser.setPassword(this.bp.encode(password));
			this.userRepository.save(currentUser);
			System.out.println("password changed successfully");
		}
		
		return currentUser;
	}

	 private Byte[] convertToBytes(MultipartFile file) throws IOException {
	        Byte[] byteObjects = new Byte[file.getBytes().length];
	        int i = 0;
	        for (byte b : file.getBytes()) {
	            byteObjects[i++] = b;
	        }
	        return byteObjects;
	    }

}
