package com.david.ecommerceapi.productShoppingCart;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/products/shoppingCart")
public class ProductShoppingController {

    private final ProductShoppingCartService productShoppingCartService;


}
