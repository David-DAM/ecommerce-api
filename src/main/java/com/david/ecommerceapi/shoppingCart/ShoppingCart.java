package com.david.ecommerceapi.shoppingCart;

import com.david.ecommerceapi.product.domain.Product;
import com.david.ecommerceapi.productShoppingCart.ProductShoppingCart;
import com.david.ecommerceapi.user.domain.User;
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
    @ManyToOne()
    @JoinColumn()
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingCart")
    private List<ProductShoppingCart> products;

}
