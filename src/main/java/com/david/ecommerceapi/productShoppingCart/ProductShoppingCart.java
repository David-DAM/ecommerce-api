package com.david.ecommerceapi.productShoppingCart;

import com.david.ecommerceapi.product.domain.Product;
import com.david.ecommerceapi.shoppingCart.ShoppingCart;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Product product;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private ShoppingCart shoppingCart;

    public static class ProductShoppingCartBuilder
    {
        public ProductShoppingCartBuilder() { }

        // Lombok will fill in the fields and methods
    }



}
