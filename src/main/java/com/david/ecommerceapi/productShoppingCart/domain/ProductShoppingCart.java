package com.david.ecommerceapi.productShoppingCart.domain;

import com.david.ecommerceapi.product.infrastructure.entity.ProductEntity;
import com.david.ecommerceapi.shoppingCart.domain.ShoppingCart;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products_shopping_cart")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private ProductEntity product;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private ShoppingCart shoppingCart;

    public static class ProductShoppingCartBuilder {
        public ProductShoppingCartBuilder() {
        }

        // Lombok will fill in the fields and methods
    }


}
