package com.david.ecommerceapi.product;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    @PostMapping()
    public ResponseEntity<Product> save(@ModelAttribute Product product, @RequestParam MultipartFile photo) throws IOException {

        return ResponseEntity.ok( this.productService.save(product, photo) );
    }

}
