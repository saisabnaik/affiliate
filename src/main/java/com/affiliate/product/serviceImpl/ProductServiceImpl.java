package com.affiliate.product.serviceImpl;

import javax.mail.Multipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.affiliate.product.Product;
import com.affiliate.product.repository.ProductRepository;
import com.affiliate.product.service.FileUploadUtil;
import com.affiliate.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public boolean addProduct(Product product,MultipartFile multipartFile) {
		
		try {
			
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			product.setpName(product.getpName());
			product.setpDesc(product.getpDesc());
			product.setpPrice(product.getpPrice());
			product.setpDiscount(product.getpDiscount());
			product.setCategory(product.getCategory());
			
			product.setpPic(fileName);
		         System.out.println(product.getpPic());
		         System.out.println(product.getpDiscount());
		         System.out.println(product.getpName());
		         System.out.println(product.getCategory());
		         System.out.println(product.getpQuantity());
		       // Product savedProduct = productRepository.save(product);
		 
		        String uploadDir = "user-photos/" ;
		 
		        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		         
		//Product presult = productRepository.save(product);
		return true;
		}catch(Exception e) {
				e.printStackTrace();
				return false;
		}
		
	}

}
