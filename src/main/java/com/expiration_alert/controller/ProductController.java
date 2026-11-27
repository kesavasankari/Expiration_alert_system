package com.expiration_alert.controller;


import com.expiration_alert.dto.ProductRequestDto;
import com.expiration_alert.dto.ProductResponseDto;
import com.expiration_alert.entity.Product;
import com.expiration_alert.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@Valid @RequestBody ProductRequestDto dto){
        return productService.addProduct(dto);

    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDto product){
        return productService.updateProduct(id,product);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);

    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String name){
        return productService.searchProducts(name);
    }

}
