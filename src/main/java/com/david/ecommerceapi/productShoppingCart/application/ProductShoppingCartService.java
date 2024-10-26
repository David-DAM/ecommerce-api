package com.david.ecommerceapi.productShoppingCart.application;

import com.david.ecommerceapi.productShoppingCart.infrastructure.ProductShoppingCartRepository;
import com.david.ecommerceapi.shoppingCart.infrastructure.SpringShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductShoppingCartService {

    private final ProductShoppingCartRepository productShoppingCartRepository;
    private final SpringShoppingCartRepository shoppingCartRepository;


}
