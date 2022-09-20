package com.affiliate.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.affiliate.product.Category;
import com.affiliate.product.Product;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
