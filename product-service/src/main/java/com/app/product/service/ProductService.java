package com.app.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.product.dto.ProductDto;
import com.app.product.dto.ProductRequestDto;
import com.app.product.entity.Product;
import com.app.product.mapper.ProductMapper;
import com.app.product.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public ProductDto createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setProductName(productRequestDto.getProductName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());
        product.setCategory(productRequestDto.getCategory());
        product.setImageUrl(productRequestDto.getImageUrl());
        product.setIsActive(productRequestDto.getIsActive());
        
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    public ProductDto getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(productMapper::toDto).orElse(null);
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getProductsByCategory(String category) {
        return productRepository.findByCategory(category).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getActiveProducts() {
        return productRepository.findByIsActive(true).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> searchProducts(String productName) {
        return productRepository.findByProductNameContainingIgnoreCase(productName).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setProductName(productRequestDto.getProductName());
            product.setDescription(productRequestDto.getDescription());
            product.setPrice(productRequestDto.getPrice());
            product.setQuantity(productRequestDto.getQuantity());
            product.setCategory(productRequestDto.getCategory());
            product.setImageUrl(productRequestDto.getImageUrl());
            product.setIsActive(productRequestDto.getIsActive());
            
            Product updatedProduct = productRepository.save(product);
            return productMapper.toDto(updatedProduct);
        }
        return null;
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void updateProductQuantity(Long productId, Integer quantityChange) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Product p = product.get();
            p.setQuantity(p.getQuantity() + quantityChange);
            productRepository.save(p);
        }
    }
}
