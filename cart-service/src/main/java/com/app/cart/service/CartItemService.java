package com.app.cart.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.cart.dto.CartItemDto;
import com.app.cart.dto.CartItemRequestDto;
import com.app.cart.entity.CartItem;
import com.app.cart.mapper.CartItemMapper;
import com.app.cart.repository.CartItemRepository;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartItemMapper cartItemMapper;

    public CartItemDto addItemToCart(CartItemRequestDto cartItemRequestDto) {
        // Check if item already exists in cart
        List<CartItem> existingItems = cartItemRepository.findByUserId(cartItemRequestDto.getUserId());
        Optional<CartItem> existingItem = existingItems.stream()
                .filter(item -> item.getProductId().equals(cartItemRequestDto.getProductId()))
                .findFirst();

        CartItem cartItem;
        if (existingItem.isPresent()) {
            // Update quantity
            cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + cartItemRequestDto.getQuantity());
        } else {
            // Create new cart item
            cartItem = new CartItem();
            cartItem.setUserId(cartItemRequestDto.getUserId());
            cartItem.setProductId(cartItemRequestDto.getProductId());
            cartItem.setQuantity(cartItemRequestDto.getQuantity());
            cartItem.setPrice(cartItemRequestDto.getPrice());
        }

        CartItem savedItem = cartItemRepository.save(cartItem);
        return cartItemMapper.toDto(savedItem);
    }

    public CartItemDto getCartItemById(Long id) {
        Optional<CartItem> cartItem = cartItemRepository.findById(id);
        if (cartItem.isPresent()) {
            return cartItemMapper.toDto(cartItem.get());
        }
        return null;
    }

    public List<CartItemDto> getCartByUserId(Long userId) {
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
        return cartItems.stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toList());
    }

    public CartItemDto updateCartItem(Long id, CartItemRequestDto cartItemRequestDto) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cartItem.setQuantity(cartItemRequestDto.getQuantity());
            cartItem.setPrice(cartItemRequestDto.getPrice());

            CartItem updatedItem = cartItemRepository.save(cartItem);
            return cartItemMapper.toDto(updatedItem);
        }
        return null;
    }

    public boolean removeItemFromCart(Long id) {
        if (cartItemRepository.existsById(id)) {
            cartItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean removeItemFromCart(Long userId, Long productId) {
        cartItemRepository.deleteByUserIdAndProductId(userId, productId);
        return true;
    }

    public boolean clearCart(Long userId) {
        List<CartItem> userCart = cartItemRepository.findByUserId(userId);
        if (!userCart.isEmpty()) {
            cartItemRepository.deleteAll(userCart);
            return true;
        }
        return false;
    }
}
