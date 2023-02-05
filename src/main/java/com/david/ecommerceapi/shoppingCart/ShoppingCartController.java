package com.david.ecommerceapi.shoppingCart;

import com.david.ecommerceapi.productShoppingCart.ProductShoppingCart;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/shopping_cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductShoppingCart>> findAllProductsByShoppingCart(@PathVariable Long id){
        return ResponseEntity.ok(shoppingCartService.findAllProductsByShoppingCart(id));
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<ShoppingCart> saveProducts(@RequestBody List<ProductShoppingCart> productShoppingCartList,@PathVariable Long id){
        return ResponseEntity.ok(this.shoppingCartService.saveProducts(productShoppingCartList, id));
    }
    @PutMapping("/{id}/products")
    public ResponseEntity<ShoppingCart> editProducts(@RequestBody List<ProductShoppingCart> productShoppingCartList,@PathVariable Long id){
        return ResponseEntity.ok(this.shoppingCartService.editProducts(productShoppingCartList, id));
    }
    @DeleteMapping("/{id}/products")
    public ResponseEntity<ShoppingCart> editProducts(@RequestBody ProductShoppingCart productShoppingCart,@PathVariable Long id){
        return ResponseEntity.ok(this.shoppingCartService.deleteProduct(productShoppingCart, id));
    }
    @DeleteMapping("/{id}/products/all")
    public ResponseEntity<ShoppingCart> deleteAllProducts(@RequestBody List<ProductShoppingCart> productShoppingCartList,@PathVariable Long id){
        return ResponseEntity.ok(this.shoppingCartService.deleteAllProducts(id));
    }
}
