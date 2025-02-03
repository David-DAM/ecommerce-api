package com.david.ecommerceapi.product.infrastructure;

import com.david.ecommerceapi.EcommerceApiApplication;
import com.david.ecommerceapi.auth.infrastructure.AuthenticationRequest;
import com.david.ecommerceapi.auth.infrastructure.AuthenticationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EcommerceApiApplication.class)
@AutoConfigureMockMvc(addFilters = false)
@Disabled
class ProductControllerMvcTest {
    @Autowired
    private MockMvc mockMvc;

    private String JWT = "";

    @BeforeEach
    void setUp() throws Exception {
        AuthenticationRequest authRequest = AuthenticationRequest.builder()
                .email("test@gmail.com")
                .password("123456789")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(authRequest);

        MvcResult result = mockMvc.perform(post("http://localhost:8080/api/auth/authenticate")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        AuthenticationResponse response = objectMapper.readValue(jsonResponse, AuthenticationResponse.class);

        JWT = response.getToken();
    }

    @Test
    void findAll() throws Exception {


        this.mockMvc.perform(get("http://localhost:8080/api/products")
                        .header("Authorization", "Bearer " + JWT))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Samsung"));
    }

    @Test
    void findById() throws Exception {

        this.mockMvc.perform(get("http://localhost:8080/api/products/1").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Samsung"));
    }

}