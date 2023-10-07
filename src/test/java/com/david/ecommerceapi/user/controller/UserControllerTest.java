package com.david.ecommerceapi.user.controller;

import com.david.ecommerceapi.user.domain.Role;
import com.david.ecommerceapi.user.domain.UserDTO;
import com.david.ecommerceapi.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;

    public Optional<UserDTO> USER_DTO = Optional.of(new UserDTO(1L, "David", "Jiménez", "david11jv@gmail.com", Role.USER));

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        when(userService.findById(1L)).thenReturn(USER_DTO);

        ResponseEntity<UserDTO> responseEntity = userController.findById(1L);

        assertEquals(1, responseEntity.getBody().getId());
        assertEquals(200, responseEntity.getStatusCode().value());

    }
}