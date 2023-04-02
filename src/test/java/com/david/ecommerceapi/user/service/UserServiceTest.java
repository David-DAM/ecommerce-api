package com.david.ecommerceapi.user.service;

import com.david.ecommerceapi.user.domain.Role;
import com.david.ecommerceapi.user.domain.User;
import com.david.ecommerceapi.user.domain.UserDTO;
import com.david.ecommerceapi.user.domain.UserDTOMapper;
import com.david.ecommerceapi.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserDTOMapper userDTOMapper;
    @InjectMocks
    private  UserService userService;
    public User USER_PREPARED = new User(1L,"Pepe","Lopez","pepito@gmail.com","1",Role.USER,null);
    public User USER_MODIFIED_PREPARED = new User(1L,"Juan","Gonzalez","juanito@gmail.com","1",Role.USER,null);
    public UserDTO USERDTO_PREPARED = new UserDTO(1L,"Pepe","Lopez","pepito@gmail.com", Role.USER);


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void update() {

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(USER_PREPARED));

        Mockito.when(userRepository.save(USER_MODIFIED_PREPARED)).thenReturn(USER_MODIFIED_PREPARED);

        userRepository.save(USER_MODIFIED_PREPARED);

        assertEquals("Juan",USER_MODIFIED_PREPARED.getFirstname());
    }

    @Test
    void findAll() {

        List<User> userListPrepared = new ArrayList<>(Arrays.asList(USER_PREPARED));

        Mockito.when(userRepository.findAll()).thenReturn(userListPrepared);

        List<UserDTO> userList = userService.findAll();

        assertEquals(1,userList.size());
    }

    @Test
    void findById() {

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(USER_PREPARED));

        Mockito.when(userDTOMapper.apply(Mockito.any(User.class)))
                .thenReturn(USERDTO_PREPARED);

        Optional<UserDTO> userDB = userService.findById(1L);

        assertEquals(1,userDB.get().getId());
    }

    @Test
    void delete() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(USER_PREPARED));

        Optional<User> userDB = userRepository.findById(1L);

        userRepository.delete(userDB.get());

        Mockito.verify(userRepository, Mockito.times(1)).delete(userDB.get());

    }
}