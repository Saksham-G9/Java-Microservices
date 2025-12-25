package com.app.cart.clients.restclient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.app.cart.dto.ProductDto;

@HttpExchange
public interface ProductServiceClient {

    @GetExchange("/api/products/{id}")
    ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id);
}
