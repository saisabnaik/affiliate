package com.affiliate.product.service;

import org.springframework.web.multipart.MultipartFile;

import com.affiliate.product.Product;

public interface ProductService {

	public boolean addProduct(Product product,MultipartFile multipartFile);
}
