package com.david.ecommerceapi.assistant.infraestructure.mapper;

import com.david.ecommerceapi.assistant.infraestructure.dto.ProductSuggestionDTO;
import com.david.ecommerceapi.product.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SuggestionMapper {

    ProductSuggestionDTO productToProductSuggestionDTO(Product product);

    Product productSuggestionDTOToProduct(ProductSuggestionDTO productSuggestionDTO);
}
