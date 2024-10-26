package com.david.ecommerceapi.shoppingCart.infrastructure;

import com.david.ecommerceapi.shoppingCart.domain.ShoppingCart;
import com.david.ecommerceapi.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndPayed(User user, boolean payed);
}
