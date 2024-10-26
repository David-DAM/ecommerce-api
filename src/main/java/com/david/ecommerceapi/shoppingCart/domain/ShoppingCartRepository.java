package com.david.ecommerceapi.shoppingCart.domain;

import com.david.ecommerceapi.user.domain.User;

import java.util.Optional;

public interface ShoppingCartRepository {

    Optional<ShoppingCart> findByUserAndPayed(User user, boolean payed);

    ShoppingCart save(ShoppingCart shoppingCart);

    Optional<ShoppingCart> findById(Long id);

}
