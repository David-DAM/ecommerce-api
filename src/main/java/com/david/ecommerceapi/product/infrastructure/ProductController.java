package com.david.ecommerceapi.product.infrastructure;

import com.david.ecommerceapi.product.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductController {

    ResponseEntity<Product> save(Product product, MultipartFile photo) throws IOException;

    ResponseEntity<List<Product>> findAll();

    ResponseEntity<Product> findById(Long id);

    ResponseEntity<Product> deletedById(Long id);

    ResponseEntity<Product> update(Product product, Optional<MultipartFile> photo) throws IOException;

}
