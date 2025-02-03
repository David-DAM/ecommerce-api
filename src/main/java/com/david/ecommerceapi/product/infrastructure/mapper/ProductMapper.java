package com.david.ecommerceapi.product.infrastructure.mapper;

import com.david.ecommerceapi.product.domain.Product;
import com.david.ecommerceapi.product.infrastructure.entity.ProductCacheEntity;
import com.david.ecommerceapi.product.infrastructure.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    ProductEntity productToProductEntity(Product product);

    Product productEntityToProduct(ProductEntity product);

    ProductCacheEntity productToProductCacheEntity(Product product);

    Product productCacheEntityToProduct(ProductCacheEntity product);

}
