package com.david.ecommerceapi.product.infrastructure;

import com.david.ecommerceapi.product.application.ProductService;
import com.david.ecommerceapi.product.domain.Category;
import com.david.ecommerceapi.product.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductControllerTest {

    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;

    public Product PRODUCT_BASE_PREPARED = new Product(1L, "Samsung", "Galaxy S3", 23.34, null, Category.PHONE, null);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {

        Mockito.when(productService.findAll()).thenReturn((Arrays.asList(PRODUCT_BASE_PREPARED)));

        ResponseEntity<List<Product>> responseEntity = productController.findAll();

        assertEquals(1, responseEntity.getBody().size());
        assertEquals(200, responseEntity.getStatusCode().value());
    }

    @Test
    @Disabled
    void update() {
    }

    @Test
    @Disabled
    void findById() {
    }

    @Test
    @Disabled
    void save() {
    }
}