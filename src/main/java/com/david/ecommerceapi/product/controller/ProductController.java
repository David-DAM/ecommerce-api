package com.david.ecommerceapi.product.controller;

import com.david.ecommerceapi.product.service.ProductService;
import com.david.ecommerceapi.product.domain.Product;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    @PostMapping()
    public ResponseEntity<Product> save(@ModelAttribute Product product, @RequestParam MultipartFile photo) throws IOException {

        return ResponseEntity.ok( this.productService.save(product, photo) );
    }
    @GetMapping()
    public ResponseEntity<List<Product>> findAll(
//            @RequestParam(defaultValue = "0") Integer page,
//            @RequestParam(defaultValue = "10") Integer pageSize,
//            @RequestParam(defaultValue = "id") String sortBy
    ){

        return ResponseEntity.ok(this.productService.findAll());//page, pageSize, sortBy
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findById(@RequestParam Long id ){
        return ResponseEntity.ok( this.productService.findById(id) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deletedById(@RequestParam Long id ){
        return ResponseEntity.ok( this.productService.delete(id) );
    }

    @PutMapping()
    public ResponseEntity<Product> update(@ModelAttribute Product product, @RequestParam Optional<MultipartFile> photo ) throws IOException {
        return ResponseEntity.ok( this.productService.update(product,photo) );
    }

}
