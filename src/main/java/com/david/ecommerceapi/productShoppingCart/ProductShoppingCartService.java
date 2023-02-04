package com.david.ecommerceapi.productShoppingCart;

import com.david.ecommerceapi.exception.domain.NotFoundException;
import com.david.ecommerceapi.shoppingCart.ShoppingCart;
import com.david.ecommerceapi.shoppingCart.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductShoppingCartService {

    private final ProductShoppingCartRepository productShoppingCartRepository;
    private final ShoppingCartRepository shoppingCartRepository;



}
