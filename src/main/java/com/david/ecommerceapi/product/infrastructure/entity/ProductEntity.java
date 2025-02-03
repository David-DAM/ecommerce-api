package com.david.ecommerceapi.product.infrastructure.entity;

import com.david.ecommerceapi.product.domain.Category;
import com.david.ecommerceapi.productShoppingCart.domain.ProductShoppingCart;
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
public class ProductEntity {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductShoppingCart> productShoppingCarts;
}


