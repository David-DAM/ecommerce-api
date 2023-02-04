package com.david.ecommerceapi.productShoppingCart;

import com.david.ecommerceapi.product.domain.Product;
import com.david.ecommerceapi.shoppingCart.ShoppingCart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products_shopping_cart")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    @ManyToOne()
    @JoinColumn()
    private Product product;
    @ManyToOne()
    @JoinColumn()
    private ShoppingCart shoppingCart;



}
