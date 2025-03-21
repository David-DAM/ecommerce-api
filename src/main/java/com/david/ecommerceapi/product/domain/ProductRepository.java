package com.david.ecommerceapi.product.domain;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    List<Product> findAllByIdIn(List<Long> ids);

    void deleteById(Long id);

    void saveAll(List<Product> products);
}
