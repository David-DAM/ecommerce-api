package com.david.ecommerceapi.product.infrastructure.repository;

import com.david.ecommerceapi.product.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryProductRepository extends JpaRepository<ProductEntity, Long> {
    //Page<Product> findAll(Pageable pageable);
}
