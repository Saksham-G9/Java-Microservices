package com.app.ecomm_application.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemResponseDto {
    private Long id;
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal priceAtOrder;
}
