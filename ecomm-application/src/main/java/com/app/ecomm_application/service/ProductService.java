package com.app.ecomm_application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.ecomm_application.dto.ProductRequestDto;
import com.app.ecomm_application.dto.ProductResponseDto;
import com.app.ecomm_application.mapper.ProductMapper;
import com.app.ecomm_application.model.Product;
import com.app.ecomm_application.repo.ProductRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepo productRepository;

    public Optional<ProductResponseDto> createProduct(ProductRequestDto productRequest) {

        Product product = productMapper.toEntity(productRequest);
        Product savedProduct = productRepository.save(product);
        return Optional.of(productMapper.toDto(savedProduct));

    }

    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ProductResponseDto> getProduct(Long id) {
        return productRepository.findById(id).map(productMapper::toDto);
    }

    public Optional<ProductResponseDto> updateProduct(Long id, ProductRequestDto productRequestDto) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            Product existingProduct = product.get();
            productMapper.updateProductFromDto(productRequestDto, existingProduct);
            Product savedProduct = productRepository.save(existingProduct);
            return Optional.of(productMapper.toDto(savedProduct));
        }

        return Optional.empty();
    }

    public void deleteProduct(Long id) {
        if (id != null) {
            productRepository.deleteById(id);
        }
    }


}
