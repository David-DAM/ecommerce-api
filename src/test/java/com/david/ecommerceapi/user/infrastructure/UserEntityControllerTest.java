package com.david.ecommerceapi.user.infrastructure;

import com.david.ecommerceapi.user.application.UserServiceImpl;
import com.david.ecommerceapi.user.domain.Role;
import com.david.ecommerceapi.user.domain.User;
import com.david.ecommerceapi.user.infrastructure.dto.UserDTO;
import com.david.ecommerceapi.user.infrastructure.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserEntityControllerTest {

    @Mock
    private UserServiceImpl userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserControllerImpl userControllerImpl;

    private final User USER = User.builder()
            .id(1L)
            .firstname("firstname")
            .lastname("lastname")
            .email("email")
            .role(Role.USER)
            .build();

    private final UserDTO userDTO = UserDTO.builder()
            .id(1L)
            .firstname("firstname")
            .lastname("lastname")
            .email("email")
            .role(Role.USER)
            .build();

    @Test
    void findById() {
        when(userService.findById(1L)).thenReturn(USER);
        when(userMapper.userToUserDTO(USER)).thenReturn(userDTO);

        ResponseEntity<UserDTO> responseEntity = userControllerImpl.findById(1L);

        assertEquals(1, responseEntity.getBody().getId());
        assertEquals(200, responseEntity.getStatusCode().value());

    }
}