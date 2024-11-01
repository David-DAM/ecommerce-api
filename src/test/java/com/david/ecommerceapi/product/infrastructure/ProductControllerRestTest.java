package com.david.ecommerceapi.product.infrastructure;

import com.david.ecommerceapi.auth.infrastructure.AuthenticationRequest;
import com.david.ecommerceapi.auth.infrastructure.AuthenticationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ProductControllerRestTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpEntity<String> entity;

    @BeforeEach
    void setUp() {

        ResponseEntity<AuthenticationResponse> response = restTemplate.postForEntity(
                "/api/auth/authenticate",
                AuthenticationRequest.builder()
                        .email("test@gmail.com")
                        .password("password")
                        .build(),
                AuthenticationResponse.class
        );

        String jwt = Objects.requireNonNull(response.getBody()).getToken();

        entity = new HttpEntity<>(null, createHeadersWithJwt(jwt));

    }

    private HttpHeaders createHeadersWithJwt(String jwt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwt);
        return headers;
    }

    @Test
    void save() {

    }

    @Test
    void findAll() {

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "/api/products",
                HttpMethod.GET,
                entity,
                String.class
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void findById() {

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "/api/product/1",
                HttpMethod.GET,
                entity,
                String.class
        );

        assertNotNull(responseEntity);
    }

    @Test
    void update() {


    }
}