package com.david.ecommerceapi.productShoppingCart.infrastructure;

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
