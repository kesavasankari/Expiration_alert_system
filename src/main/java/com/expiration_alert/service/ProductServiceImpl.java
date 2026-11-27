package com.expiration_alert.service;
import com.expiration_alert.dto.ProductRequestDto;
import com.expiration_alert.dto.ProductResponseDto;
import com.expiration_alert.entity.Product;
import com.expiration_alert.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

  private final ProductRepository repo;

    @Override
    public ResponseEntity<String> addProduct( ProductRequestDto product) {
       Product prod=new Product();
       prod.setName(product.name());
       prod.setQuantity(product.quantity());
       prod.setExpiry_date(product.expiry_date());
      repo.save(prod);
       return ResponseEntity.ok("Product Saved Successfully!");
    }

    @Override
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
       List<Product> products=repo.findAll();

       List<ProductResponseDto> dtoList = products.stream()
               .map(p -> new ProductResponseDto(
                       p.getId(),
                       p.getName(),
                       p.getQuantity(),
                       p.getExpiry_date()
               ))
               .toList();
       return ResponseEntity.ok(dtoList);
    }

    @Override
    public ResponseEntity<ProductResponseDto> getProductById(Long id) {
      Product product = repo.findById(id)
              .orElseThrow(()-> new RuntimeException("Product Unavailable "));

       ProductResponseDto response= new ProductResponseDto(product.getId(), product.getName(), product.getQuantity(),product.getExpiry_date());

       return ResponseEntity.ok(response);


    }

    @Override
    public ResponseEntity<ProductResponseDto> updateProduct(Long id, ProductRequestDto product) {
       Product prod= repo.findById(id)
               .orElseThrow(()-> new RuntimeException("Product Unavailable With this ID: "+id));

       prod.setName(product.name());
       prod.setQuantity(product.quantity());
       prod.setExpiry_date(product.expiry_date());

     Product savedProd=  repo.save(prod);

     ProductResponseDto response=new ProductResponseDto(savedProd.getId(), savedProd.getName(), savedProd.getQuantity(), savedProd.getExpiry_date());

     return ResponseEntity.ok(response);

    }

    @Override
    public ResponseEntity<String> deleteProduct(Long id) {
        Product exsitingProduct = repo.findById(id)
                .orElseThrow(()->
                        new RuntimeException("product is not found "));

        repo.delete(exsitingProduct);
        return ResponseEntity.ok("product deleted ");

    }

    @Override
    public ResponseEntity<List<Product>> searchProducts(String name) {
        return ResponseEntity.ok( repo.findByNameContainingIgnoreCase(name));
    }
}

