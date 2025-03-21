package com.david.ecommerceapi.assistant.domain;

import com.david.ecommerceapi.product.domain.Product;

import java.util.Optional;

public interface AssistantService {

    Optional<Product> getProductSuggestion(String message);
}
