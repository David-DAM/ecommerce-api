package com.david.ecommerceapi.shoppingCart.domain;

import com.david.ecommerceapi.productShoppingCart.domain.ProductShoppingCart;
import com.david.ecommerceapi.user.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "shopping_carts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double total;
    private boolean payed;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private User user;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingCart")
    private List<ProductShoppingCart> products;

}
