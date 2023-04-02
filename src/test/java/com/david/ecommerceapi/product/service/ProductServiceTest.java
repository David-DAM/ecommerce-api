package com.david.ecommerceapi.product.service;

import com.david.ecommerceapi.product.domain.Category;
import com.david.ecommerceapi.product.domain.Product;
import com.david.ecommerceapi.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    public Product PRODUCT_BASE_PREPARED = new Product(1L,"Samsung","Galaxy S3",23.34,null, Category.PHONE,null);
    public Product PRODUCT_MODIFIED_PREPARED = new Product(1L,"Samsung","Galaxy S3",23.34,null, Category.COMPUTER,null);

    public MultipartFile multipartFile = new MockMultipartFile("prueba","algi","prueba","alo".getBytes(StandardCharsets.UTF_8));

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void save() throws IOException {

        Mockito.when(productRepository.save(PRODUCT_BASE_PREPARED)).thenReturn(PRODUCT_BASE_PREPARED);

        Product product = productService.save(PRODUCT_BASE_PREPARED, multipartFile);

        assertNotNull(product);

        assertEquals(Category.PHONE, product.getCategory());

    }

    @Test
    void findAll() {
        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(PRODUCT_BASE_PREPARED));

        List<Product> productList = productService.findAll();

        assertNotNull(productList);

        assertEquals(1,productList.size());
    }

    @Test
    void findById() {

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(PRODUCT_BASE_PREPARED));

        Optional<Product> product = productService.findById(1L);

        assertNotNull(product);

        assertEquals(1,product.get().getId());
    }

    @Test
    void update() throws IOException {

        Mockito.when(productRepository.save(PRODUCT_MODIFIED_PREPARED)).thenReturn(PRODUCT_MODIFIED_PREPARED);

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(PRODUCT_BASE_PREPARED));

        Product product = productService.update(PRODUCT_MODIFIED_PREPARED, Optional.empty());

        assertNotNull(product);

        assertEquals(Category.COMPUTER,product.getCategory());

    }

    @Test
    void delete() {

        Mockito.when(productRepository.findById(PRODUCT_BASE_PREPARED.getId())).thenReturn(Optional.ofNullable(PRODUCT_BASE_PREPARED));

        productService.delete(PRODUCT_BASE_PREPARED.getId());

        Mockito.verify(productRepository, Mockito.times(1)).delete(PRODUCT_BASE_PREPARED);
    }
}