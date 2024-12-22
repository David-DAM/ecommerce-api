package com.david.ecommerceapi.user.infrastructure;

import com.david.ecommerceapi.user.infrastructure.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {

    ResponseEntity<UserDTO> findById(Long id);

    ResponseEntity<List<UserDTO>> findAll();

    ResponseEntity<UserDTO> update(UserDTO userDTO);
    
    ResponseEntity<Void> delete(Long id);
}
