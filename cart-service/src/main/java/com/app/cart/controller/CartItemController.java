package com.app.cart.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.cart.dto.CartItemDto;
import com.app.cart.dto.CartItemRequestDto;
import com.app.cart.service.CartItemService;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/add")
    public ResponseEntity<CartItemDto> addItemToCart(@RequestBody CartItemRequestDto cartItemRequestDto) {
        CartItemDto cartItemDto = cartItemService.addItemToCart(cartItemRequestDto);
        return new ResponseEntity<>(cartItemDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemDto> getCartItemById(@PathVariable Long id) {
        CartItemDto cartItemDto = cartItemService.getCartItemById(id);
        if (cartItemDto != null) {
            return new ResponseEntity<>(cartItemDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartItemDto>> getCartByUserId(@PathVariable Long userId) {
        List<CartItemDto> cartItems = cartItemService.getCartByUserId(userId);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemDto> updateCartItem(@PathVariable Long id, @RequestBody CartItemRequestDto cartItemRequestDto) {
        CartItemDto cartItemDto = cartItemService.updateCartItem(id, cartItemRequestDto);
        if (cartItemDto != null) {
            return new ResponseEntity<>(cartItemDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long id) {
        boolean deleted = cartItemService.removeItemFromCart(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<Void> removeSpecificItem(@RequestParam Long userId, @RequestParam Long productId) {
        cartItemService.removeItemFromCart(userId, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        boolean cleared = cartItemService.clearCart(userId);
        if (cleared) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
