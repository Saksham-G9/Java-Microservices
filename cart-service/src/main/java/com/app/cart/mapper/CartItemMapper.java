package com.app.cart.mapper;

import org.springframework.stereotype.Component;
import com.app.cart.dto.CartItemDto;
import com.app.cart.entity.CartItem;

@Component
public class CartItemMapper {

    public CartItemDto toDto(CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cartItem.getId());
        cartItemDto.setUserId(cartItem.getUserId());
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setCreatedAt(cartItem.getCreatedAt());
        cartItemDto.setUpdatedAt(cartItem.getUpdatedAt());
        return cartItemDto;
    }

    public CartItem toEntity(CartItemDto cartItemDto) {
        if (cartItemDto == null) {
            return null;
        }
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemDto.getId());
        cartItem.setUserId(cartItemDto.getUserId());
        cartItem.setProductId(cartItemDto.getProductId());
        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.setPrice(cartItemDto.getPrice());
        return cartItem;
    }
}
