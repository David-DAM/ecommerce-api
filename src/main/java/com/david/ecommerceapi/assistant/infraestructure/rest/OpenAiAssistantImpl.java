package com.david.ecommerceapi.assistant.infraestructure.rest;

import com.david.ecommerceapi.assistant.domain.AssistantSuggestion;
import com.david.ecommerceapi.assistant.domain.PromptType;
import com.david.ecommerceapi.assistant.infraestructure.dto.ProductSuggestionDTO;
import com.david.ecommerceapi.assistant.infraestructure.mapper.SuggestionMapper;
import com.david.ecommerceapi.assistant.infraestructure.utility.PromptLoader;
import com.david.ecommerceapi.product.domain.Product;
import com.david.ecommerceapi.product.infrastructure.repository.implementation.PostgresProductRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.ResponseFormat;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class OpenAiAssistantImpl implements AssistantSuggestion {

    private final OpenAiChatModel openAiChatModel;
    private final PromptLoader promptLoader;
    private final SuggestionMapper suggestionMapper;
    private final VectorStore vectorStore;
    private final PostgresProductRepositoryImpl productRepository;

    @Override
    public Optional<Product> getProductSuggestion(String request) {

        SearchRequest searchRequest = SearchRequest.builder()
                .query(request)
                .topK(3)
                .build();

        List<Document> documents = vectorStore.similaritySearch(searchRequest);

        if (documents == null || documents.isEmpty()) {
            return Optional.empty();
        }

        List<Long> productIds = documents.stream().map(document -> Long.valueOf(document.getMetadata().get("productId").toString())).toList();

        List<Product> products = productRepository.findAllByIdIn(productIds);

        if (products.isEmpty()) {
            return Optional.empty();
        }

        Resource promptResource = promptLoader.getPromptResource(PromptType.PRODUCT_SUGGESTION);

        PromptTemplate promptTemplate = new PromptTemplate(promptResource);
        promptTemplate.add("request", request);
        promptTemplate.add("products", products);

        Message message = promptTemplate.createMessage();

        BeanOutputConverter<ProductSuggestionDTO> outputConverter = new BeanOutputConverter<>(ProductSuggestionDTO.class);

        String jsonSchema = outputConverter.getJsonSchema();

        Prompt prompt = new Prompt(message, OpenAiChatOptions.builder()
                .responseFormat(new ResponseFormat(ResponseFormat.Type.JSON_SCHEMA, jsonSchema))
                .build()
        );

        ChatResponse response = openAiChatModel.call(prompt);

        String content = response.getResult().getOutput().getText();

        ProductSuggestionDTO productSuggestionDTO = outputConverter.convert(content);

        return Optional.ofNullable(suggestionMapper.productSuggestionDTOToProduct(productSuggestionDTO));
    }
}
