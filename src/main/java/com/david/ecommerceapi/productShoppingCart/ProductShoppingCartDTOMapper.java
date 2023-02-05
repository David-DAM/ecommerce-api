package com.david.ecommerceapi.productShoppingCart;

import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ProductShoppingCartDTOMapper implements Function<ProductShoppingCart,ProductShoppingCartDTO> {
    @Override
    public ProductShoppingCartDTO apply(ProductShoppingCart productShoppingCart) {
        return new ProductShoppingCartDTO.ProductShoppingCartDTOBuilder()
                .product(productShoppingCart.getProduct())
                .quantity(productShoppingCart.getQuantity())
                .build();

    }
}
