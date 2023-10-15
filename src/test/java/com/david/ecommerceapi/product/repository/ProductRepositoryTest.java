package com.david.ecommerceapi.product.repository;

import com.david.ecommerceapi.product.domain.Category;
import com.david.ecommerceapi.product.domain.Product;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Profile("dev")
@Disabled
class ProductRepositoryTest {
    @Autowired
    public ProductRepository productRepository;
    @Autowired
    public TestEntityManager testEntityManager;

    @Test
    void save(){
        Product product = new Product (null,"prueba","prueba",22,null,Category.COMPUTER,null);

        assertNull(product.getId());

        Long id = testEntityManager.persist(product).getId();

        assertNotNull(id);
    }
    @Test
    void find_all(){
        List<Product> productList = productRepository.findAll();

        assertNotNull(productList);
    }


}