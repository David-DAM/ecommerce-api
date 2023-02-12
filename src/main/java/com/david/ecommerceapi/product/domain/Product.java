package com.david.ecommerceapi.product.domain;

import com.david.ecommerceapi.productShoppingCart.ProductShoppingCart;
import com.david.ecommerceapi.shoppingCart.ShoppingCart;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private String image;
    @Enumerated(EnumType.STRING)
    private Category category;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private List<ProductShoppingCart> productShoppingCarts;

}
