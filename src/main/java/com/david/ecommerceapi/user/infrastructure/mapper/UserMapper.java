package com.david.ecommerceapi.user.infrastructure.mapper;

import com.david.ecommerceapi.auth.infrastructure.RegisterRequest;
import com.david.ecommerceapi.user.domain.User;
import com.david.ecommerceapi.user.infrastructure.dto.UserDTO;
import com.david.ecommerceapi.user.infrastructure.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User userEntityToUser(UserEntity userEntity);

    UserEntity userToUserEntity(User user);

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);

    User registerRequestToUser(RegisterRequest registerRequest);
}
