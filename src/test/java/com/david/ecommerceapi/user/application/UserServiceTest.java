package com.david.ecommerceapi.user.application;

import com.david.ecommerceapi.user.domain.Role;
import com.david.ecommerceapi.user.domain.User;
import com.david.ecommerceapi.user.infrastructure.SpringUserRepository;
import com.david.ecommerceapi.user.infrastructure.UserMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {
    @Mock
    private SpringUserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    private AutoCloseable autoCloseable;

    public User USER_PREPARED = new User(1L, "Pepe", "Lopez", "pepito@gmail.com", "1", Role.USER, null);

    public User USER_MODIFIED_PREPARED = new User(1L, "Juan", "Gonzalez", "juanito@gmail.com", "1", Role.USER, null);

    public UserDTO USER_DTO_PREPARED = new UserDTO(1L, "Pepe", "Lopez", "pepito@gmail.com", Role.USER);


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeMocks() throws Exception {
        autoCloseable.close();
    }

    @Test
    void update() {

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(USER_PREPARED));

        Mockito.when(userRepository.save(USER_MODIFIED_PREPARED)).thenReturn(USER_MODIFIED_PREPARED);

        userRepository.save(USER_MODIFIED_PREPARED);

        assertEquals("Juan", USER_MODIFIED_PREPARED.getFirstname());
    }

    @Test
    void findAll() {

        List<User> userListPrepared = new ArrayList<>(List.of(USER_PREPARED));

        Mockito.when(userRepository.findAll()).thenReturn(userListPrepared);

        List<UserDTO> userList = userService.findAll();

        assertEquals(1, userList.size());
    }

    @Test
    void findById() {

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(USER_PREPARED));

        Mockito.when(userMapper.userToUserDTO(Mockito.any(User.class)))
                .thenReturn(USER_DTO_PREPARED);

        UserDTO userDB = userService.findById(1L);

        assertEquals(1, userDB.getId());
    }

    @Test
    void delete() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(USER_PREPARED));

        Optional<User> userDB = userRepository.findById(1L);

        userRepository.delete(userDB.get());

        Mockito.verify(userRepository, Mockito.times(1)).delete(userDB.get());

    }
}