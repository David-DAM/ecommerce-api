package com.david.ecommerceapi.assistant.domain;

import com.david.ecommerceapi.product.domain.Product;

public interface AssistantSuggestion {

    Product getProductSuggestion(String message);

}
