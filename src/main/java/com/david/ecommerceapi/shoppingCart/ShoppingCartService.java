package com.david.ecommerceapi.shoppingCart;

import com.david.ecommerceapi.exception.domain.NotFoundException;
import com.david.ecommerceapi.productShoppingCart.ProductShoppingCart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public List<ProductShoppingCart> findAllProductsByShoppingCart(Long id){
        Optional<ShoppingCart> shoppingCart = this.shoppingCartRepository.findById(id);

        if(shoppingCart.isEmpty()) throw new NotFoundException("Carrito por el que buscar no encontrado");

        return shoppingCart.get().getProducts();
    }

}
