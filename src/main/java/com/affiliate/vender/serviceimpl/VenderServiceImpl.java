package com.affiliate.vender.serviceimpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

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

	@Value("${uploadDir}")
	private String uploadFolder;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public Vender updateProfile(Vender vender, Principal principal, MultipartFile file,HttpServletRequest request) throws Exception {
		Vender currentVender = this.venderRepository.findByEmail(principal.getName());
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
				currentVender.setImage(imageData);
			}
		}
		
		currentVender.setFirstname(vender.getFirstname());
		currentVender.setLastname(vender.getLastname());
		currentVender.setMobile(vender.getMobile());
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
