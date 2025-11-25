package com.app.ecomm_application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.ecomm_application.dto.CartItemRequestDto;
import com.app.ecomm_application.dto.CartItemResponseDto;
import com.app.ecomm_application.service.CartItemService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<Void> addToCart(
            @RequestHeader("X-User-ID") Long userId,
            @RequestBody CartItemRequestDto request) {
        try {
            cartItemService.addToCart(userId, request);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException ex) {
            String msg = ex.getMessage() == null ? "" : ex.getMessage().toLowerCase();
            if (msg.contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<CartItemResponseDto> getCart(
            @RequestHeader("X-User-ID") Long userId) {
        return ResponseEntity.ok(cartItemService.getCart(userId));
    }

    @DeleteMapping("/remove-product/{productId}/quantity/{quantity}")
    public ResponseEntity<Void> removeProductFromCart(
            @RequestHeader("X-User-ID") Long userId,
            @PathVariable Long productId,
            @PathVariable int quantity) {
        cartItemService.removeItemFromCart(userId, productId, quantity);
        return ResponseEntity.ok().build();
    }

}
