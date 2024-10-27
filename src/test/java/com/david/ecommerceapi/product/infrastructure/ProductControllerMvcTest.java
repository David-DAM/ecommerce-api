package com.david.ecommerceapi.product.infrastructure;

import com.david.ecommerceapi.product.application.ProductService;
import com.david.ecommerceapi.product.domain.Category;
import com.david.ecommerceapi.product.domain.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WithMockUser
@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
//@ContextConfiguration(classes = {ProductController.class, JwtService.class, JwtAuthFilter.class})
@Disabled
class ProductControllerMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    public Product PRODUCT_BASE_PREPARED = new Product(1L, "Samsung", "Galaxy S3", 23.34, "image.png", Category.PHONE, null);

    @Test
    @Disabled
    void save() throws Exception {
        Mockito.when(
                productService.save(
                        PRODUCT_BASE_PREPARED,
                        new MockMultipartFile("image.png", "image.png".getBytes(StandardCharsets.UTF_8))
                )).thenReturn(PRODUCT_BASE_PREPARED);

        this.mockMvc.perform(
                        post("/api/products")
                                .content(new ObjectMapper().writeValueAsString(PRODUCT_BASE_PREPARED))
                        //.contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Samsung"));
    }

    @Test
    void findAll() throws Exception {
        Mockito.when(productService.findAll()).thenReturn(Arrays.asList(PRODUCT_BASE_PREPARED));

        this.mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Samsung"));
    }

    @Test
    void findById() throws Exception {
        Mockito.when(productService.findById(1L)).thenReturn(PRODUCT_BASE_PREPARED);

        this.mockMvc.perform(get("/api/products/1").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Samsung"));
    }

    @Test
    @Disabled
    void update() throws Exception {

        Mockito.when(
                productService.update(
                        PRODUCT_BASE_PREPARED,
                        Optional.empty()
                )).thenReturn(PRODUCT_BASE_PREPARED);

        this.mockMvc.perform(
                        put("/api/products")
                                .content(new ObjectMapper().writeValueAsString(PRODUCT_BASE_PREPARED))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Samsung"));

    }
}