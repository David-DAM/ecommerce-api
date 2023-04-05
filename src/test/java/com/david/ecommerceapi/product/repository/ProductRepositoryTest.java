package com.david.ecommerceapi.product.repository;

import com.david.ecommerceapi.product.domain.Category;
import com.david.ecommerceapi.product.domain.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Profile("dev")
class ProductRepositoryTest {
    @Autowired
    public ProductRepository productRepository;
    @Autowired
    public TestEntityManager testEntityManager;

    @Test
    void save(){
        Product product = new Product (null,"prueba","prueba",22,null,Category.COMPUTER,null);

        assertNull(product.getId());

        productRepository.save(product);

        assertNotNull(product.getId());
    }
    @Test
    void find_all(){
        List<Product> productList = productRepository.findAll();

        assertNotNull(productList);
    }


}