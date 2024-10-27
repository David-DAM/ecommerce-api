package com.david.ecommerceapi.product.infrastructure;

import com.david.ecommerceapi.product.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Disabled
class ProductControllerRestTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void save() throws Exception {

    }

    @Test
    void findAll() throws Exception {

        ResponseEntity<Product> responseEntity = restTemplate.getForEntity("/api/products/1", Product.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void findById() throws Exception {

        ResponseEntity<Product> responseEntity = restTemplate.getForEntity("/api/product/1", Product.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void update() throws Exception {


    }
}