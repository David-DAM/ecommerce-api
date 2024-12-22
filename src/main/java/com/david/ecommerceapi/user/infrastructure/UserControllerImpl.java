package com.david.ecommerceapi.user.infrastructure;

import com.david.ecommerceapi.exception.domain.NotFoundException;
import com.david.ecommerceapi.user.application.UserServiceImpl;
import com.david.ecommerceapi.user.domain.User;
import com.david.ecommerceapi.user.infrastructure.dto.UserDTO;
import com.david.ecommerceapi.user.infrastructure.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "User", description = "The User API. Contains all the operations that can be performed on a user.")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) throws NotFoundException {

        User user = userService.findById(id);

        UserDTO userDTO = userMapper.userToUserDTO(user);

        return ResponseEntity.ok(userDTO);
    }

    @Operation(summary = "List all users", description = "List all users")
    @GetMapping()
    //@PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<UserDTO>> findAll() {

        List<UserDTO> userDTOS = userService.findAll().stream().map(userMapper::userToUserDTO).toList();

        return ResponseEntity.ok(userDTOS);
    }

    @PutMapping()
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) {

        User user = userMapper.userDTOToUser(userDTO);

        User updated = userService.update(user);

        UserDTO updatedDTO = userMapper.userToUserDTO(updated);

        return ResponseEntity.ok(updatedDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        userService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
