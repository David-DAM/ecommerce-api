package com.example.ecommerceapi.user.controller;

import com.example.ecommerceapi.exception.domain.NotFoundException;
import com.example.ecommerceapi.security.service.JwtService;
import com.example.ecommerceapi.user.domain.User;
import com.example.ecommerceapi.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "User", description = "The User API. Contains all the operations that can be performed on a user.")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById( @PathVariable Long id) throws NotFoundException {
        Optional<User> userOpt = this.userService.findById(id);

        return ResponseEntity.ok(userOpt.get());
    }
    @Operation(summary = "List all users", description = "List all users")
    @GetMapping()
    public ResponseEntity<List<User>> findAll(){

        return ResponseEntity.ok(this.userService.findAll());
    }

    @PutMapping()
    public ResponseEntity<User> update(@RequestBody User user) {

        return ResponseEntity.ok(this.userService.update(user));
    }

    // delete one
    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id){

        this.userService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
