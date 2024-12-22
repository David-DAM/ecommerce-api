package com.david.ecommerceapi.config;

import com.david.ecommerceapi.user.domain.Role;
import com.david.ecommerceapi.user.domain.User;
import com.david.ecommerceapi.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class TestConfiguration {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Bean
    public TestRestTemplate restTemplate() {

        userRepository.findByEmail("test@gmail.com").orElseGet(() -> {
            User user = User.builder()
                    .role(Role.USER)
                    .email("test@gmail.com")
                    .password(passwordEncoder.encode("password"))
                    .firstname("test")
                    .build();

            return userRepository.save(user);
        });

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                .rootUri("http://localhost:8080");

        return new TestRestTemplate(restTemplateBuilder);
    }

}
