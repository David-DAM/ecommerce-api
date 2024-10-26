package com.david.ecommerceapi.user.infrastructure;

import com.david.ecommerceapi.exception.domain.NotFoundException;
import com.david.ecommerceapi.user.application.UserDTO;
import com.david.ecommerceapi.user.application.UserService;
import com.david.ecommerceapi.user.domain.User;
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
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) throws NotFoundException {

        UserDTO userOpt = userService.findById(id);

        return ResponseEntity.ok(userOpt);
    }

    @Operation(summary = "List all users", description = "List all users")
    @GetMapping()
    //@PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<UserDTO>> findAll() {

        return ResponseEntity.ok(this.userService.findAll());
    }

    @PutMapping()
    public ResponseEntity<UserDTO> update(@RequestBody User user) {

        return ResponseEntity.ok(this.userService.update(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {

        this.userService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
