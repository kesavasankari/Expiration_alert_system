package com.expiration_alert.service;

import com.expiration_alert.dto.ProductRequestDto;
import com.expiration_alert.dto.ProductResponseDto;
import com.expiration_alert.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<String> addProduct(ProductRequestDto product);

    ResponseEntity<List<ProductResponseDto>> getAllProducts();

    ResponseEntity<ProductResponseDto> getProductById(Long id);

    ResponseEntity<ProductResponseDto> updateProduct(Long id, ProductRequestDto product);

    ResponseEntity<String> deleteProduct(Long id);

    ResponseEntity<List<Product>> searchProducts(String name);
}
