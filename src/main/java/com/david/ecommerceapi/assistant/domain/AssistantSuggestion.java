package com.david.ecommerceapi.assistant.domain;

import com.david.ecommerceapi.product.domain.Product;

import java.util.Optional;

public interface AssistantSuggestion {

    Optional<Product> getProductSuggestion(String message);

}
