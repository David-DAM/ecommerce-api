package com.david.ecommerceapi.user.application;

import com.david.ecommerceapi.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDTO userToUserDTO(User user);

}
