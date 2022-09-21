package com.affiliate.product.service;

import org.springframework.web.multipart.MultipartFile;

import com.affiliate.product.Category;
import com.affiliate.product.Product;

public interface ProductService {
	public boolean addProduct(Product product,Category category,MultipartFile multipartFile,int venderid);
}
