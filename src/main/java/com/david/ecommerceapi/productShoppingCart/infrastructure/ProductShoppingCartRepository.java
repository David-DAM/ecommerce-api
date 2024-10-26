package com.david.ecommerceapi.productShoppingCart.infrastructure;

import com.david.ecommerceapi.productShoppingCart.domain.ProductShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductShoppingCartRepository extends JpaRepository<ProductShoppingCart, Long> {

}
