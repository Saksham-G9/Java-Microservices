package com.app.ecomm_application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ecomm_application.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

}
