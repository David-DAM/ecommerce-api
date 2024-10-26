package com.david.ecommerceapi.productShoppingCart.infrastructure;

import com.david.ecommerceapi.productShoppingCart.application.ProductShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/products/shoppingCart")
public class ProductShoppingController {

    private final ProductShoppingCartService productShoppingCartService;


}
