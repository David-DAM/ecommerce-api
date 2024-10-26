package com.david.ecommerceapi.user.infrastructure;

import com.david.ecommerceapi.user.application.UserDTO;
import com.david.ecommerceapi.user.domain.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDTO userToUserDTO(User user);

}
