package com.david.ecommerceapi.shoppingCart.application;

import com.david.ecommerceapi.exception.domain.BadRequestException;
import com.david.ecommerceapi.exception.domain.NotFoundException;
import com.david.ecommerceapi.product.infrastructure.SpringProductRepository;
import com.david.ecommerceapi.productShoppingCart.domain.ProductShoppingCart;
import com.david.ecommerceapi.productShoppingCart.infrastructure.ProductShoppingCartDTO;
import com.david.ecommerceapi.shoppingCart.domain.ShoppingCart;
import com.david.ecommerceapi.shoppingCart.domain.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final SpringProductRepository productRepository;

    public List<ProductShoppingCart> findAllProductsByShoppingCart(Long id) {

        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(() -> new NotFoundException("Shopping cart not found"));
        //TODO add .stream().collect(Collectors.groupingBy(w -> w.stud_location)); for repeated objects
        return shoppingCart.getProducts();
    }

    //TODO add transactional
    public ShoppingCart saveProducts(List<ProductShoppingCartDTO> productShoppingCartDTOList, Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(() -> new NotFoundException("Shopping cart not found"));

        List<ProductShoppingCart> productShoppingCartListFromDTO = getProductShoppingCartListFromDTO(productShoppingCartDTOList, shoppingCart);

        isValidShoppingCartList(productShoppingCartListFromDTO);

        List<ProductShoppingCart> productShoppingCartList = shoppingCart.getProducts();

        productShoppingCartList.addAll(productShoppingCartListFromDTO);

        shoppingCart.setTotal(
                shoppingCart.getTotal() + productShoppingCartList
                        .stream()
                        .mapToDouble(x -> x.getProduct().getPrice() * x.getQuantity())
                        .sum()
        );

        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart editProducts(List<ProductShoppingCartDTO> productShoppingCartDTOList, Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(() -> new NotFoundException("Shopping cart not found"));

        List<ProductShoppingCart> productShoppingCartListFromDTO = getProductShoppingCartListFromDTO(productShoppingCartDTOList, shoppingCart);

        List<ProductShoppingCart> productShoppingCartList = shoppingCart.getProducts();

        productShoppingCartList.clear();

        productShoppingCartList.addAll(productShoppingCartListFromDTO);

        shoppingCart.setTotal(
                productShoppingCartList
                        .stream()
                        .mapToDouble(x -> x.getProduct().getPrice() * x.getQuantity())
                        .sum()
        );

        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart deleteAllProducts(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(() -> new NotFoundException("Shopping cart not found"));

        List<ProductShoppingCart> productShoppingCartList = shoppingCart.getProducts();

        productShoppingCartList.clear();

        shoppingCart.setTotal(0);

        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart deleteProduct(ProductShoppingCart productShoppingCart, Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(() -> new NotFoundException("Shopping cart not found"));

        List<ProductShoppingCart> productShoppingCartList = shoppingCart.getProducts();

        productShoppingCartList.remove(productShoppingCart);

        shoppingCart.setTotal(
                productShoppingCartList
                        .stream()
                        .mapToDouble(x -> x.getProduct().getPrice() * x.getQuantity())
                        .sum()
        );

        return shoppingCartRepository.save(shoppingCart);
    }

    public List<ProductShoppingCart> getProductShoppingCartListFromDTO(List<ProductShoppingCartDTO> productShoppingCartDTOList, ShoppingCart shoppingCart) {

        return productShoppingCartDTOList
                .stream()
                .map(
                        x -> new ProductShoppingCart.ProductShoppingCartBuilder()
                                .quantity(x.getQuantity())
                                .product(
                                        productRepository.findById(x.getProduct_id()).orElseThrow(() -> new NotFoundException("Shopping cart not found"))
                                )
                                .shoppingCart(shoppingCart).build()
                )
                .toList();
    }

    //TODO replace with better solution for each errror message
    public void isValidShoppingCartList(List<ProductShoppingCart> shoppingCartList) {
        shoppingCartList.stream()
                .filter(x -> x.getQuantity() > 0)
                .filter(x -> x.getProduct() != null)
                .findAny()
                .ifPresent(a -> {
                    throw new BadRequestException("Product list no valid");
                });
    }
}
