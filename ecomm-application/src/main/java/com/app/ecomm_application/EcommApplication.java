package com.app.ecomm_application;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.app.ecomm_application.dto.ProductRequestDto;
import com.app.ecomm_application.service.ProductService;

@SpringBootApplication
@EnableJpaRepositories
public class EcommApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommApplication.class, args);
	}

	// @Bean
	// CommandLineRunner initDatabase(ProductService productService) {
	// 	return args -> {
	// 		// Sample products
	// 		productService.createProduct(new ProductRequestDto("Laptop", "High-performance laptop",
	// 				new BigDecimal("1200.00"), 10, "Electronics", "https://example.com/laptop.jpg", true));
	// 		productService.createProduct(new ProductRequestDto("Smartphone", "Latest smartphone model",
	// 				new BigDecimal("800.00"), 25, "Electronics", "https://example.com/smartphone.jpg", true));
	// 		productService.createProduct(new ProductRequestDto("Book", "Bestselling novel", new BigDecimal("15.99"), 50,
	// 				"Books", "https://example.com/book.jpg", true));
	// 		productService.createProduct(new ProductRequestDto("Headphones", "Noise-cancelling headphones",
	// 				new BigDecimal("150.00"), 15, "Electronics", "https://example.com/headphones.jpg", true));
	// 		productService.createProduct(new ProductRequestDto("T-shirt", "Cotton t-shirt", new BigDecimal("20.00"),
	// 				100, "Clothing", "https://example.com/tshirt.jpg", true));
	// 		productService.createProduct(new ProductRequestDto("Coffee Maker", "Automatic coffee maker",
	// 				new BigDecimal("80.00"), 8, "Appliances", "https://example.com/coffeemaker.jpg", true));
	// 		productService.createProduct(new ProductRequestDto("Running Shoes", "Comfortable running shoes",
	// 				new BigDecimal("120.00"), 20, "Sports", "https://example.com/shoes.jpg", true));
	// 		productService.createProduct(new ProductRequestDto("Tablet", "10-inch tablet", new BigDecimal("300.00"), 12,
	// 				"Electronics", "https://example.com/tablet.jpg", true));
	// 		productService.createProduct(new ProductRequestDto("Backpack", "Durable backpack", new BigDecimal("45.00"),
	// 				30, "Accessories", "https://example.com/backpack.jpg", true));
	// 		productService.createProduct(new ProductRequestDto("Watch", "Smart watch", new BigDecimal("250.00"), 18,
	// 				"Electronics", "https://example.com/watch.jpg", true));
	// 	};
	// }

}
