package com.david.ecommerceapi.user.domain;

import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class UserDTOMapper implements Function<User,UserDTO> {

    @Override
    public UserDTO apply(User user) {
        return new UserDTO.UserDTOBuilder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
