package com.david.ecommerceapi.shoppingCart.domain;

import com.david.ecommerceapi.user.infrastructure.entity.UserEntity;

import java.util.Optional;

public interface ShoppingCartRepository {

    Optional<ShoppingCart> findByUserAndPayed(UserEntity userEntity, boolean payed);

    ShoppingCart save(ShoppingCart shoppingCart);

    Optional<ShoppingCart> findById(Long id);

}
