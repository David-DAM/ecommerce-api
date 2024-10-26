package com.david.ecommerceapi.product.infrastructure;

import com.david.ecommerceapi.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringProductRepository extends JpaRepository<Product, Long> {
    //Page<Product> findAll(Pageable pageable);
}
