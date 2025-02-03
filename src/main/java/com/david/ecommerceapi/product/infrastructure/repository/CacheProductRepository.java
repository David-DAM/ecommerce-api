package com.david.ecommerceapi.product.infrastructure.repository;

import com.david.ecommerceapi.product.infrastructure.entity.ProductCacheEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheProductRepository extends CrudRepository<ProductCacheEntity, Long> {
}
