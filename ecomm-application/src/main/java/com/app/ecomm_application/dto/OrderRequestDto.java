package com.app.ecomm_application.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequestDto {
    private List<OrderItemRequestDto> orderItems;
    private String shippingAddress;
}
