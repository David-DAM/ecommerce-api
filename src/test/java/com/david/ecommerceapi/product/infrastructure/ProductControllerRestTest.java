package com.david.ecommerceapi.product.infrastructure;

import com.david.ecommerceapi.product.application.ProductService;
import com.david.ecommerceapi.product.domain.Category;
import com.david.ecommerceapi.product.domain.Product;
import com.david.ecommerceapi.security.application.JwtService;
import com.david.ecommerceapi.security.infrastructure.JwtAuthFilter;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

@WithMockUser
@ContextConfiguration(classes = {ProductController.class, JwtService.class, JwtAuthFilter.class})
@Disabled
class ProductControllerRestTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Mock
    private ProductService productService;
    public Product PRODUCT_BASE_PREPARED = new Product(1L, "Samsung", "Galaxy S3", 23.34, "image.png", Category.PHONE, null);
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @Disabled
    void save() throws Exception {
        Mockito.when(
                productService.save(
                        PRODUCT_BASE_PREPARED,
                        new MockMultipartFile("image.png", "image.png".getBytes(StandardCharsets.UTF_8))
                )).thenReturn(PRODUCT_BASE_PREPARED);
    }

    @Test
    @Disabled
    void findAll() throws Exception {
        Mockito.when(productService.findAll()).thenReturn(Arrays.asList(PRODUCT_BASE_PREPARED));

        ResponseEntity<Product> responseEntity = restTemplate.getForEntity("api/product/1", Product.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @Disabled
    void findById() throws Exception {
        Mockito.when(productService.findById(1L)).thenReturn(Optional.ofNullable(PRODUCT_BASE_PREPARED));

        ResponseEntity<Product> responseEntity = restTemplate.getForEntity("api/product/1", Product.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @Disabled
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