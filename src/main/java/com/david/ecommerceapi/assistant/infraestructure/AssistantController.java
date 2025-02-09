package com.david.ecommerceapi.assistant.infraestructure;

import com.david.ecommerceapi.assistant.infraestructure.dto.ProductSuggestionDTO;
import org.springframework.http.ResponseEntity;

public interface AssistantController {

    ResponseEntity<ProductSuggestionDTO> getProductSuggestions(String message);

}
