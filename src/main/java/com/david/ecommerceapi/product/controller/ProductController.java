package com.david.ecommerceapi.product.controller;

import com.david.ecommerceapi.product.service.ProductService;
import com.david.ecommerceapi.product.domain.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    @PostMapping()
    public ResponseEntity<Product> save(@ModelAttribute Product product, @RequestParam MultipartFile photo) throws IOException {

        return ResponseEntity.ok( this.productService.save(product, photo) );
    }
    @GetMapping()
    public ResponseEntity<List<Product>> findAll(){

        return ResponseEntity.ok(this.productService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findById(@RequestParam Long id ){
        return ResponseEntity.ok( this.productService.findById(id) );
    }

    @PutMapping()
    public ResponseEntity<Product> update(@ModelAttribute Product product, @RequestParam Optional<MultipartFile> photo ) throws IOException {
        return ResponseEntity.ok( this.productService.update(product,photo) );
    }

}
