package com.david.ecommerceapi.shoppingCart;

import com.david.ecommerceapi.exception.domain.NotFoundException;
import com.david.ecommerceapi.product.repository.ProductRepository;
import com.david.ecommerceapi.productShoppingCart.ProductShoppingCart;
import com.david.ecommerceapi.productShoppingCart.ProductShoppingCartDTO;
import com.david.ecommerceapi.productShoppingCart.ProductShoppingCartDTOMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;

    public List<ProductShoppingCart> findAllProductsByShoppingCart(Long id){

        ShoppingCart shoppingCart = this.shoppingCartRepository.getReferenceById(id);
        //TODO add .stream().collect(Collectors.groupingBy(w -> w.stud_location)); for repeated objects
        return shoppingCart.getProducts();
    }
    //TODO add transactional
    public ShoppingCart saveProducts(List<ProductShoppingCartDTO> productShoppingCartDTOList, Long id){
        ShoppingCart shoppingCart = this.shoppingCartRepository.getReferenceById(id);

        List<ProductShoppingCart> productShoppingCartListFromDTO = getProductShoppingCartListFromDTO(productShoppingCartDTOList, shoppingCart);

        List<ProductShoppingCart> productShoppingCartList = shoppingCart.getProducts();

        productShoppingCartList.addAll(productShoppingCartListFromDTO);

        shoppingCart.setTotal(
                shoppingCart.getTotal() + productShoppingCartList
                        .stream()
                        .mapToDouble(x -> x.getProduct().getPrice() * x.getQuantity())
                        .sum()
        );

        return this.shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart editProducts(List<ProductShoppingCartDTO> productShoppingCartDTOList,Long id){
        ShoppingCart shoppingCart = this.shoppingCartRepository.getReferenceById(id);

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

        return this.shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart deleteAllProducts(Long id){
        ShoppingCart shoppingCart = this.shoppingCartRepository.getReferenceById(id);

        List<ProductShoppingCart> productShoppingCartList = shoppingCart.getProducts();

        productShoppingCartList.clear();

        shoppingCart.setTotal(0);

        return this.shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart deleteProduct(ProductShoppingCart productShoppingCart,Long id){
        ShoppingCart shoppingCart = this.shoppingCartRepository.getReferenceById(id);

        List<ProductShoppingCart> productShoppingCartList = shoppingCart.getProducts();

        productShoppingCartList.remove(productShoppingCart);

        shoppingCart.setTotal(
                productShoppingCartList
                        .stream()
                        .mapToDouble(x -> x.getProduct().getPrice() * x.getQuantity())
                        .sum()
        );

        return this.shoppingCartRepository.save(shoppingCart);
    }

    public List<ProductShoppingCart> getProductShoppingCartListFromDTO(List<ProductShoppingCartDTO> productShoppingCartDTOList, ShoppingCart shoppingCart){

        return productShoppingCartDTOList
                .stream()
                .map(
                        x -> new ProductShoppingCart.ProductShoppingCartBuilder()
                                .quantity(x.getQuantity())
                                .product(
                                        this.productRepository.findById(x.getProduct_id()).get()
                                )
                                .shoppingCart(shoppingCart).build()
                ).toList();
    }
}
