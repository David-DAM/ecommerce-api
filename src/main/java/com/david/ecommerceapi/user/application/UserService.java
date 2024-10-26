package com.david.ecommerceapi.user.application;

import com.david.ecommerceapi.exception.domain.NotFoundException;
import com.david.ecommerceapi.user.domain.User;
import com.david.ecommerceapi.user.domain.UserRepository;
import com.david.ecommerceapi.user.infrastructure.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    //TODO Refactor with validations in model
    public boolean validateUser(User user) {

        if (user == null || user.getEmail().isEmpty() || userRepository.existsByEmail(user.getEmail())) {
            return false;
        }

        return true;
    }

    public UserDTO update(User user) {

        User userDB = userRepository.findById(user.getId()).orElseThrow(() -> new NotFoundException("User not found"));

        BeanUtils.copyProperties(user, userDB, "role", "password");

        userRepository.save(userDB);

        return userMapper.userToUserDTO(userDB);
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(userMapper::userToUserDTO).toList();
    }

    public UserDTO findById(Long id) throws NotFoundException {

        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        return userMapper.userToUserDTO(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
