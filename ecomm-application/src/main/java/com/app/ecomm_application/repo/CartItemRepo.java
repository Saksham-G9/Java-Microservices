package com.app.ecomm_application.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ecomm_application.model.CartItem;
import com.app.ecomm_application.model.Product;
import com.app.ecomm_application.model.User;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long>{
	Optional<CartItem> findByUserAndProduct(User user, Product product);
	Optional<CartItem> findByUser(User user);

}
