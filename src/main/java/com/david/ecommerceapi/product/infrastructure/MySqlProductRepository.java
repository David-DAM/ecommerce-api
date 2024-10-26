package com.david.ecommerceapi.product.infrastructure;

import com.david.ecommerceapi.product.domain.Product;
import com.david.ecommerceapi.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MySqlProductRepository implements ProductRepository {

    private final SpringProductRepository springProductRepository;

    @Override
    public Product save(Product product) {
        return springProductRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return springProductRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return springProductRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        springProductRepository.deleteById(id);
    }
}
