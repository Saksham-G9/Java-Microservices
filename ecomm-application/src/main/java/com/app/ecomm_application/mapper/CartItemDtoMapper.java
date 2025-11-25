package com.app.ecomm_application.mapper;

import com.app.ecomm_application.dto.CartItemRequestDto;
import com.app.ecomm_application.dto.CartItemResponseDto;
import com.app.ecomm_application.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartItemDtoMapper {
    CartItemDtoMapper INSTANCE = Mappers.getMapper(CartItemDtoMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "product.id", target = "productId")
    CartItemResponseDto toResponseDto(CartItem cartItem);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CartItem toEntity(CartItemRequestDto dto);
}
