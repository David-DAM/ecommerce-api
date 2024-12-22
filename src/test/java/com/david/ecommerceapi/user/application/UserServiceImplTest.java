package com.david.ecommerceapi.user.application;

import com.david.ecommerceapi.user.domain.Role;
import com.david.ecommerceapi.user.domain.User;
import com.david.ecommerceapi.user.domain.UserRepository;
import com.david.ecommerceapi.user.infrastructure.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    public User USER_PREPARED = User.builder()
            .id(1L)
            .firstname("John")
            .lastname("Doe")
            .email("john@doe.com")
            .password("password")
            .role(Role.USER)
            .build();

    public User USER_MODIFIED_PREPARED = User.builder()
            .id(1L)
            .firstname("John")
            .lastname("Doe")
            .email("john@doe.com")
            .password("password")
            .role(Role.USER)
            .build();

    public UserDTO USER_DTO_PREPARED = new UserDTO(1L, "Pepe", "Lopez", "pepito@gmail.com", Role.USER);


    @Test
    void update() {

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(USER_PREPARED));
        when(userRepository.save(USER_PREPARED)).thenReturn(USER_MODIFIED_PREPARED);

        userService.update(USER_PREPARED);

        assertEquals("John", USER_MODIFIED_PREPARED.getFirstname());
    }

    @Test
    void findAll() {

        List<User> usersMock = new ArrayList<>(List.of(USER_PREPARED));

        when(userRepository.findAll()).thenReturn(usersMock);

        List<User> users = userService.findAll();

        assertEquals(1, users.size());
    }

    @Test
    void findById() {

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(USER_PREPARED));

        User userDB = userService.findById(1L);

        assertEquals(1, userDB.getId());
    }

    @Test
    void delete() {

        userService.delete(1L);

        verify(userRepository, times(1)).deleteById(1L);

    }
}