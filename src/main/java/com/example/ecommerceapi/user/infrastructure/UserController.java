package com.example.ecommerceapi.user.infrastructure;

import com.example.ecommerceapi.jwt.domain.JwtService;
import com.example.ecommerceapi.user.domain.AuthRequest;
import com.example.ecommerceapi.user.domain.User;
import com.example.ecommerceapi.user.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    @Autowired
    public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager=authenticationManager;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById( @PathVariable Long id) {
        Optional<User> userOpt = this.userService.findById(id);

        return ResponseEntity.ok(userOpt.get());
    }

    @GetMapping()
    public ResponseEntity<List<User>> findAll(){

        return ResponseEntity.ok(this.userService.findAll());
    }

    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user){

        return ResponseEntity.ok( this.userService.save(user) );
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
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));

        if (!authentication.isAuthenticated()) throw new UsernameNotFoundException("Invalid user request");

        return ResponseEntity.ok(jwtService.generateToken(authRequest.getUsername()));
    }

}
