package com.david.ecommerceapi.productShoppingCart.infrastructure;

import com.david.ecommerceapi.productShoppingCart.domain.ProductShoppingCart;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductShoppingCartDTOMapper implements Function<ProductShoppingCart, ProductShoppingCartDTO> {
    @Override
    public ProductShoppingCartDTO apply(ProductShoppingCart productShoppingCart) {
        return new ProductShoppingCartDTO.ProductShoppingCartDTOBuilder()
                .product_id(productShoppingCart.getProduct().getId())
                .quantity(productShoppingCart.getQuantity())
                .build();

    }
}
