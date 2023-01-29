package com.david.ecommerceapi.productShoppingCart;

import com.david.ecommerceapi.shoppingCart.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductShoppingCartRepository extends JpaRepository<ProductShoppingCart,Long> {
     Optional<ProductShoppingCart> findByShoppingCart(ShoppingCart shoppingCart);
}
