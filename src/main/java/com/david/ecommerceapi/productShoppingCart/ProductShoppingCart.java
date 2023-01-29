package com.david.ecommerceapi.productShoppingCart;

import com.david.ecommerceapi.product.domain.Product;
import com.david.ecommerceapi.shoppingCart.ShoppingCart;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ProductShoppingCart {

    private Integer quantity;
    @ManyToOne()
    @JoinColumn()
    private Product product;
    @ManyToOne()
    @JoinColumn()
    private ShoppingCart shoppingCart;



}
