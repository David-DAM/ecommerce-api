package com.david.ecommerceapi.product.controller;

import com.david.ecommerceapi.product.domain.Category;
import com.david.ecommerceapi.product.domain.Product;
import com.david.ecommerceapi.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(ProductController.class)
class ProductControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private ProductService productService;
//
//    public Product PRODUCT_BASE_PREPARED = new Product(1L,"Samsung","Galaxy S3",23.34,null, Category.PHONE,null);
//
//    @BeforeEach
//    void setup(WebApplicationContext wac) {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//    }
//    @Test
//    void save() {
//    }
//
//    @Test
//    void findAll() throws Exception {
//        Mockito.when(productService.findAll()).thenReturn(Arrays.asList(PRODUCT_BASE_PREPARED));
//        this.mockMvc.perform(get("/api/products")).andExpect(status().isOk());
//    }
//
//    @Test
//    void findById() {
//    }
//
//    @Test
//    void update() {
//    }
}