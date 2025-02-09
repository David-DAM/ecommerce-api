package com.david.ecommerceapi.assistant.infraestructure.utility;

import com.david.ecommerceapi.assistant.domain.PromptType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PromptLoader {

    private final ResourceLoader resourceLoader;

    public Resource getPromptResource(PromptType promptType) {

        return switch (promptType) {
            case PRODUCT_SUGGESTION -> resourceLoader.getResource("classpath:prompts/" + promptType.getValue());
            default -> throw new IllegalStateException("Unexpected value: " + promptType);
        };
    }

}
