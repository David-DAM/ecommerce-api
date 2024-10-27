package com.david.ecommerceapi.config;

import com.david.ecommerceapi.security.application.JwtService;
import com.david.ecommerceapi.user.domain.Role;
import com.david.ecommerceapi.user.domain.User;
import com.david.ecommerceapi.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class TestConfiguration {

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Bean
    public TestRestTemplate restTemplate() {

        User user = userRepository.findByEmail("test@gmail.com").orElseGet(() -> {
            User newUser = new User();
            newUser.setRole(Role.USER);
            newUser.setPassword(passwordEncoder.encode("password"));
            newUser.setEmail("test@gmail.com");
            newUser.setFirstname("test");
            return userRepository.save(newUser);
        });

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        "password"
                )
        );

        String token = jwtService.generateToken(user);

        System.out.println("Generated Token: " + token);

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                .rootUri("http://localhost:8080")
                .defaultHeader("Authorization", "Bearer " + token);

        return new TestRestTemplate(restTemplateBuilder);
    }

}
