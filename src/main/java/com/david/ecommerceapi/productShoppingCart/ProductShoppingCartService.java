package com.david.ecommerceapi.productShoppingCart;

import com.david.ecommerceapi.exception.domain.NotFoundException;
import com.david.ecommerceapi.shoppingCart.ShoppingCart;
import com.david.ecommerceapi.shoppingCart.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductShoppingCartService {

    private final ProductShoppingCartRepository productShoppingCartRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public Optional<ProductShoppingCart> findByShoppingCart(Long id){

        Optional<ShoppingCart> shoppingCart = this.shoppingCartRepository.findById(id);

        if(shoppingCart.isEmpty()) throw new NotFoundException("Carrito por el que buscar no encontrado");

        return this.productShoppingCartRepository.findByShoppingCart(shoppingCart.get());
    }

}
