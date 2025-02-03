package com.david.ecommerceapi.product.infrastructure.entity;

import com.david.ecommerceapi.product.domain.Category;
import com.david.ecommerceapi.productShoppingCart.domain.ProductShoppingCart;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@RedisHash("Product")
@Data
public class ProductCacheEntity implements Serializable {

    @Id
    private Long id;

    private String name;

    private String description;

    private double price;

    private String image;

    private Category category;

    private List<ProductShoppingCart> productShoppingCarts;
}



