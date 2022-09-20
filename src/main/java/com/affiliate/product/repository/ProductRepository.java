package com.affiliate.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.affiliate.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
