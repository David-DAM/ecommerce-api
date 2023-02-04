package com.david.ecommerceapi.shoppingCart;

import com.david.ecommerceapi.productShoppingCart.ProductShoppingCart;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/shopping_cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping("{id}/products")
    public ResponseEntity<List<ProductShoppingCart>> findAllProductsByShoppingCart(@PathVariable Long id){
        return ResponseEntity.ok(shoppingCartService.findAllProductsByShoppingCart(id));
    }
}
