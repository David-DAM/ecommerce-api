package com.david.ecommerceapi.shoppingCart.infrastructure;

import com.david.ecommerceapi.shoppingCart.domain.ShoppingCart;
import com.david.ecommerceapi.shoppingCart.domain.ShoppingCartRepository;
import com.david.ecommerceapi.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MySqlShoppingCartRepository implements ShoppingCartRepository {

    private final SpringShoppingCartRepository springShoppingCartRepository;

    @Override
    public Optional<ShoppingCart> findByUserAndPayed(User user, boolean payed) {
        return springShoppingCartRepository.findByUserAndPayed(user, payed);
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return springShoppingCartRepository.save(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> findById(Long id) {
        return springShoppingCartRepository.findById(id);
    }
}
