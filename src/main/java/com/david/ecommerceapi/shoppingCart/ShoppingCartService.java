package com.david.ecommerceapi.shoppingCart;

import com.david.ecommerceapi.exception.domain.NotFoundException;
import com.david.ecommerceapi.productShoppingCart.ProductShoppingCart;
import com.david.ecommerceapi.productShoppingCart.ProductShoppingCartDTO;
import com.david.ecommerceapi.productShoppingCart.ProductShoppingCartDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public List<ProductShoppingCart> findAllProductsByShoppingCart(Long id){
        Optional<ShoppingCart> shoppingCart = this.shoppingCartRepository.findById(id);

        if(shoppingCart.isEmpty()) throw new NotFoundException("Carrito con Id "+ id +" no encontrado");
        //TODO add .stream().collect(Collectors.groupingBy(w -> w.stud_location)); for repeated objects
        return shoppingCart.get().getProducts();
    }

    public ShoppingCart saveProducts(List<ProductShoppingCart> productShoppingCartList, Long id){
        Optional<ShoppingCart> shoppingCart = this.shoppingCartRepository.findById(id);

        if(shoppingCart.isEmpty()) throw new NotFoundException("Carrito con Id "+ id +" no encontrado");

        shoppingCart.get().getProducts().addAll(productShoppingCartList);

        shoppingCart.get().setTotal(shoppingCart.get().calculateTotalPrice());

        return this.shoppingCartRepository.save(shoppingCart.get());
    }

    public ShoppingCart editProducts(List<ProductShoppingCart> productShoppingCartList,Long id){
        Optional<ShoppingCart> shoppingCart = this.shoppingCartRepository.findById(id);

        if(shoppingCart.isEmpty()) throw new NotFoundException("Carrito con Id "+ id +" no encontrado");

        shoppingCart.get().getProducts().clear();

        shoppingCart.get().getProducts().addAll(productShoppingCartList);

        shoppingCart.get().setTotal(shoppingCart.get().calculateTotalPrice());

        return this.shoppingCartRepository.save(shoppingCart.get());
    }

    public ShoppingCart deleteAllProducts(Long id){
        Optional<ShoppingCart> shoppingCart = this.shoppingCartRepository.findById(id);

        if(shoppingCart.isEmpty()) throw new NotFoundException("Carrito con Id "+ id +" no encontrado");

        shoppingCart.get().getProducts().clear();

        shoppingCart.get().setTotal(shoppingCart.get().calculateTotalPrice());

        return this.shoppingCartRepository.save(shoppingCart.get());
    }

    public ShoppingCart deleteProduct(ProductShoppingCart productShoppingCart,Long id){
        Optional<ShoppingCart> shoppingCart = this.shoppingCartRepository.findById(id);

        if(shoppingCart.isEmpty()) throw new NotFoundException("Carrito con Id "+ id +" no encontrado");

        shoppingCart.get().getProducts().remove(productShoppingCart);

        shoppingCart.get().setTotal(shoppingCart.get().calculateTotalPrice());

        return this.shoppingCartRepository.save(shoppingCart.get());
    }

}
