package com.david.ecommerceapi.user.application;

import com.david.ecommerceapi.exception.domain.NotFoundException;
import com.david.ecommerceapi.user.domain.User;
import com.david.ecommerceapi.user.domain.UserRepository;
import com.david.ecommerceapi.user.domain.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User update(User updateUser) {

        User user = userRepository.findById(updateUser.getId()).orElseThrow(() -> new NotFoundException("User not found"));

        BeanUtils.copyProperties(updateUser, user, "role");

        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) throws NotFoundException {

        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
