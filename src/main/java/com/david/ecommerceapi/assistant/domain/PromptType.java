package com.david.ecommerceapi.assistant.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PromptType {

    PRODUCT_SUGGESTION("productSuggestionPrompt.txt");

    private final String value;

}
