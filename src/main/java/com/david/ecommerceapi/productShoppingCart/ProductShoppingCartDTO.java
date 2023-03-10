package com.david.ecommerceapi.productShoppingCart;

import com.david.ecommerceapi.product.domain.Product;
import com.david.ecommerceapi.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductShoppingCartDTO {

    private Long product_id;
    private Integer quantity;

}
