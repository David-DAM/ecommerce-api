package com.david.ecommerceapi.assistant.domain;

import com.david.ecommerceapi.product.domain.Product;

public interface AssistantService {

    Product getProductSuggestion(String message);
}
