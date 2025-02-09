package com.david.ecommerceapi.assistant.infraestructure;

import com.david.ecommerceapi.assistant.domain.AssistantService;
import com.david.ecommerceapi.assistant.infraestructure.dto.ProductSuggestionDTO;
import com.david.ecommerceapi.assistant.infraestructure.mapper.SuggestionMapper;
import com.david.ecommerceapi.product.domain.Product;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/assistant")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
public class AssistantControllerImpl implements AssistantController {

    private final AssistantService assistantService;
    private final SuggestionMapper suggestionMapper;

    @GetMapping("/products")
    public ResponseEntity<ProductSuggestionDTO> getProductSuggestions(@RequestParam String message) {
        Product productSuggestion = assistantService.getProductSuggestion(message);
        ProductSuggestionDTO productSuggestionDTO = suggestionMapper.productToProductSuggestionDTO(productSuggestion);
        return ResponseEntity.ok(productSuggestionDTO);
    }

}
