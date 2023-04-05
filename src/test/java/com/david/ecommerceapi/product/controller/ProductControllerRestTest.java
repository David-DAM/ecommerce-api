package com.david.ecommerceapi.product.controller;

import com.david.ecommerceapi.product.domain.Category;
import com.david.ecommerceapi.product.domain.Product;
import com.david.ecommerceapi.product.service.ProductService;
import com.david.ecommerceapi.security.filter.JwtAuthFilter;
import com.david.ecommerceapi.security.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser
@ContextConfiguration(classes = {ProductController.class, JwtService.class, JwtAuthFilter.class})
class ProductControllerRestTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private ProductService productService;
    public Product PRODUCT_BASE_PREPARED = new Product(1L,"Samsung","Galaxy S3",23.34,"image.png", Category.PHONE,null);
    //@Test
    void save() throws Exception {
        Mockito.when(
                productService.save(
                        PRODUCT_BASE_PREPARED,
                        new MockMultipartFile("image.png", "image.png".getBytes(StandardCharsets.UTF_8))
                )).thenReturn(PRODUCT_BASE_PREPARED);
    }

    //@Test
    void findAll() throws Exception {
        Mockito.when(productService.findAll()).thenReturn(Arrays.asList(PRODUCT_BASE_PREPARED));

        ResponseEntity<Product> responseEntity =  restTemplate.getForEntity("api/product/1",Product.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    }

    //@Test
    void findById() throws Exception {
        Mockito.when(productService.findById(1L)).thenReturn(Optional.ofNullable(PRODUCT_BASE_PREPARED));

        ResponseEntity<Product> responseEntity =  restTemplate.getForEntity("api/product/1",Product.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    }

    //@Test
    void update() throws Exception {

        Mockito.when(
                productService.update(
                        PRODUCT_BASE_PREPARED,
                        Optional.empty()
                )).thenReturn(PRODUCT_BASE_PREPARED);

//        this.mockMvc.perform(
//                        put("/api/products")
//                                .content(new ObjectMapper().writeValueAsString(PRODUCT_BASE_PREPARED))
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.name").value("Samsung"));

    }
}