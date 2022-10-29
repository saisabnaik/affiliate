package com.affiliate.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.affiliate.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	public List<Product> findAllByVid(int venderid);
	public Product findByProductId(Long productId);

	public void deleteByProductId(Long productId);

}
