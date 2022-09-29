package com.affiliate.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.affiliate.product.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
	public List<ProductImage> findAllByProductId(Long productId);
}
