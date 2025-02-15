package com.david.ecommerceapi.user.domain;

import com.david.ecommerceapi.shoppingCart.domain.ShoppingCart;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
    private List<ShoppingCart> shoppingCarts;
}
