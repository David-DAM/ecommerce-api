package com.david.ecommerceapi.assistant.application;

import com.david.ecommerceapi.assistant.domain.AssistantService;
import com.david.ecommerceapi.assistant.domain.AssistantSuggestion;
import com.david.ecommerceapi.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssistantServiceImpl implements AssistantService {

    private final AssistantSuggestion assistantSuggestion;

    public Optional<Product> getProductSuggestion(String message) {
        return assistantSuggestion.getProductSuggestion(message);
    }
}
