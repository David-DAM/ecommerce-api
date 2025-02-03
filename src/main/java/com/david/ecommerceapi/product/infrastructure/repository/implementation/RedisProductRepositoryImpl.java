package com.david.ecommerceapi.product.infrastructure.repository.implementation;

import com.david.ecommerceapi.product.domain.Product;
import com.david.ecommerceapi.product.domain.ProductRepository;
import com.david.ecommerceapi.product.infrastructure.entity.ProductCacheEntity;
import com.david.ecommerceapi.product.infrastructure.mapper.ProductMapper;
import com.david.ecommerceapi.product.infrastructure.repository.CacheProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Repository
public class RedisProductRepositoryImpl implements ProductRepository {

    private final CacheProductRepository cacheProductRepository;
    private final ProductMapper productMapper;

    @Override
    public Product save(Product product) {
        ProductCacheEntity saved = cacheProductRepository.save(productMapper.productToProductCacheEntity(product));
        return productMapper.productCacheEntityToProduct(saved);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return cacheProductRepository.findById(id).map(productMapper::productCacheEntityToProduct);
    }

    @Override
    public List<Product> findAll() {
        Iterable<ProductCacheEntity> all = cacheProductRepository.findAll();
        return StreamSupport.stream(all.spliterator(), false).map(productMapper::productCacheEntityToProduct).toList();
    }

    @Override
    public void deleteById(Long id) {
        cacheProductRepository.deleteById(id);
    }

    @Override
    public void saveAll(List<Product> products) {
        cacheProductRepository.saveAll(products.stream().map(productMapper::productToProductCacheEntity).toList());
    }
}
