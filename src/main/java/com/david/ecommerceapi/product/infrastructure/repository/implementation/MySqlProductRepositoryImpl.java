package com.david.ecommerceapi.product.infrastructure.repository.implementation;

import com.david.ecommerceapi.product.domain.Product;
import com.david.ecommerceapi.product.domain.ProductRepository;
import com.david.ecommerceapi.product.infrastructure.entity.ProductEntity;
import com.david.ecommerceapi.product.infrastructure.mapper.ProductMapper;
import com.david.ecommerceapi.product.infrastructure.repository.QueryProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MySqlProductRepositoryImpl implements ProductRepository {

    private final QueryProductRepository queryProductRepository;
    private final ProductMapper productMapper;

    @Override
    public Product save(Product product) {
        ProductEntity saved = queryProductRepository.save(productMapper.productToProductEntity(product));
        return productMapper.productEntityToProduct(saved);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return queryProductRepository.findById(id).map(productMapper::productEntityToProduct);
    }

    @Override
    public List<Product> findAll() {
        return queryProductRepository.findAll().stream().map(productMapper::productEntityToProduct).toList();
    }

    @Override
    public void deleteById(Long id) {
        queryProductRepository.deleteById(id);
    }

    @Override
    public void saveAll(List<Product> products) {
        queryProductRepository.saveAll(products.stream().map(productMapper::productToProductEntity).toList());
    }
}
