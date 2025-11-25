package com.app.ecomm_application.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.app.ecomm_application.dto.CartItemRequestDto;
import com.app.ecomm_application.dto.CartItemResponseDto;
import com.app.ecomm_application.mapper.CartItemDtoMapper;
import com.app.ecomm_application.model.CartItem;
import com.app.ecomm_application.model.Product;
import com.app.ecomm_application.model.User;
import com.app.ecomm_application.repo.CartItemRepo;
import com.app.ecomm_application.repo.ProductRepo;
import com.app.ecomm_application.repo.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final ProductRepo productRepo;
    private final CartItemRepo cartItemRepo;
    private final UserRepository userRepo;
    private final CartItemDtoMapper cartItemMapper;

    public void addToCart(Long userId, CartItemRequestDto request) {
        Product product = productRepo.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStockQuantity() < request.getQuantity() || !product.isActive()) {
            throw new RuntimeException("Insufficient stock for product ID: " + product.getId());
        }

        product.setStockQuantity(product.getStockQuantity() - request.getQuantity());
        productRepo.save(product);

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CartItem item = cartItemRepo.findByUserAndProduct(user, product)
                .map(existing -> {
                    existing.setQuantity(existing.getQuantity() + request.getQuantity());
                    existing.setPrice(calculatePrice(existing.getQuantity(), product.getPrice()));
                    return existing;
                })
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setUser(user);
                    newItem.setProduct(product);
                    newItem.setQuantity(request.getQuantity());
                    newItem.setPrice(calculatePrice(newItem.getQuantity(), product.getPrice()));
                    return newItem;
                });

        cartItemRepo.save(item);
    }

    public CartItemResponseDto getCart(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartItemRepo.findByUser(user)
                .map(cartItemMapper::toResponseDto)
                .orElse(null);
    }

    private static BigDecimal calculatePrice(int quantity, BigDecimal unitPrice) {
        return BigDecimal.valueOf(quantity).multiply(unitPrice);
    }

}
