package com.david.ecommerceapi.product.domain;

import com.david.ecommerceapi.productShoppingCart.domain.ProductShoppingCart;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Product {

    private Long id;

    private String name;

    private String description;

    private double price;

    private String image;

    private Category category;

    private List<ProductShoppingCart> productShoppingCarts;

}
