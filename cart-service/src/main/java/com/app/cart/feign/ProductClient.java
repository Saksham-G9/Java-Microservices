// package com.app.cart.feign;

// import org.springframework.cloud.openfeign.FeignClient;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import com.app.cart.dto.ProductDto;

// @FeignClient(name = "product-service", url = "http://localhost:8082")
// public interface ProductClient {
    
//     @GetMapping("/api/products/{id}")
//     ProductDto getProductById(@PathVariable("id") Long id);
// }
