package com.app.ecomm_application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.app.ecomm_application.dto.OrderItemResponseDto;
import com.app.ecomm_application.dto.OrderResponseDto;
import com.app.ecomm_application.model.Order;
import com.app.ecomm_application.model.OrderItem;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "orderItems", target = "orderItems")
    OrderResponseDto toDto(Order order);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    OrderItemResponseDto toOrderItemDto(OrderItem orderItem);

    List<OrderItemResponseDto> toOrderItemDtoList(List<OrderItem> orderItems);
}
