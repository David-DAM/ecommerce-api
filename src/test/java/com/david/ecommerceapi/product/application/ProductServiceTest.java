package com.david.ecommerceapi.product.application;

import com.david.ecommerceapi.exception.domain.NotFoundException;
import com.david.ecommerceapi.product.domain.Category;
import com.david.ecommerceapi.product.domain.Product;
import com.david.ecommerceapi.product.domain.ProductRepository;
import com.david.ecommerceapi.util.FileUploadUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.StringUtils;
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
    @Mock
    private FileUploadUtil fileUploadUtil;
    @Mock
    public MultipartFile MULTIPARTFILE_PREPARED = new MockMultipartFile("image.png", "image.png".getBytes(StandardCharsets.UTF_8));
    @InjectMocks
    private ProductService productService;

    public Product PRODUCT_BASE_PREPARED = new Product(1L, "Samsung", "Galaxy S3", 23.34, "image.png", Category.PHONE, null);
    public Product PRODUCT_MODIFIED_PREPARED = new Product(1L, "Samsung", "Galaxy S3", 23.34, "image.png", Category.COMPUTER, null);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void save() throws IOException {

        Mockito.when(productRepository.save(PRODUCT_BASE_PREPARED)).thenReturn(PRODUCT_BASE_PREPARED);

        Mockito.when(MULTIPARTFILE_PREPARED.getOriginalFilename()).thenReturn("image.png");

        Mockito.when(fileUploadUtil.saveFile(StringUtils.cleanPath(MULTIPARTFILE_PREPARED.getOriginalFilename()), MULTIPARTFILE_PREPARED)).thenReturn("2023");

        Product product = productService.save(PRODUCT_BASE_PREPARED, MULTIPARTFILE_PREPARED);

        assertNotNull(product);

        assertEquals(Category.PHONE, product.getCategory());

        Mockito.verify(fileUploadUtil, Mockito.times(1))
                .saveFile(StringUtils.cleanPath(MULTIPARTFILE_PREPARED.getOriginalFilename()), MULTIPARTFILE_PREPARED);

        assertEquals("2023-image.png", product.getImage());
    }

    @Test
    void find_all() {
        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(PRODUCT_BASE_PREPARED));

        List<Product> productList = productService.findAll();

        assertNotNull(productList);

        assertEquals(1, productList.size());
    }

    @Test
    void find_by_id() {

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(PRODUCT_BASE_PREPARED));

        Product product = productService.findById(1L);

        assertNotNull(product);

        assertEquals(1, product.getId());
    }

    @Test
    void update_without_image() throws IOException {

        Mockito.when(productRepository.save(PRODUCT_MODIFIED_PREPARED)).thenReturn(PRODUCT_MODIFIED_PREPARED);

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(PRODUCT_BASE_PREPARED));

        Product product = productService.update(PRODUCT_MODIFIED_PREPARED, Optional.empty());

        assertNotNull(product);

        assertEquals(Category.COMPUTER, product.getCategory());

        assertEquals("image.png", product.getImage());
    }

    @Test
    void update_with_image() throws IOException {

        Mockito.when(productRepository.save(PRODUCT_MODIFIED_PREPARED)).thenReturn(PRODUCT_MODIFIED_PREPARED);

        Mockito.when(MULTIPARTFILE_PREPARED.getOriginalFilename()).thenReturn("image.png");

        Mockito.when(fileUploadUtil.saveFile(StringUtils.cleanPath(MULTIPARTFILE_PREPARED.getOriginalFilename()), MULTIPARTFILE_PREPARED)).thenReturn("2024");

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(PRODUCT_BASE_PREPARED));

        Product product = productService.update(PRODUCT_MODIFIED_PREPARED, Optional.of(MULTIPARTFILE_PREPARED));

        assertNotNull(product);

        assertNotNull(product.getImage());

        assertEquals(Category.COMPUTER, product.getCategory());

        assertEquals("2024-image.png", product.getImage());
    }

    @Test
    void delete() {

        Mockito.when(productRepository.findById(PRODUCT_BASE_PREPARED.getId())).thenReturn(Optional.ofNullable(PRODUCT_BASE_PREPARED));

        Product product = productService.delete(PRODUCT_BASE_PREPARED.getId());

        Mockito.verify(productRepository, Mockito.times(1)).deleteById(PRODUCT_BASE_PREPARED.getId());

        assertNotNull(product);
    }

    @Test
    void delete_throw_not_found() {

        Mockito.when(productRepository.findById(PRODUCT_BASE_PREPARED.getId() - 1)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> productService.delete(PRODUCT_BASE_PREPARED.getId() - 1));
    }
}