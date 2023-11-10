package com.e.commerce.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e.commerce.domain.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
