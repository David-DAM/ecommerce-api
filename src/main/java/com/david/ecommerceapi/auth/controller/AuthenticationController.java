package com.david.ecommerceapi.auth.controller;

import com.david.ecommerceapi.auth.domain.AuthenticationRequest;
import com.david.ecommerceapi.auth.domain.AuthenticationResponse;
import com.david.ecommerceapi.auth.domain.RegisterRequest;
import com.david.ecommerceapi.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register( @RequestBody RegisterRequest request ) {//@Validated

      return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate( @RequestBody AuthenticationRequest request ) {

      return ResponseEntity.ok(service.authenticate(request));
  }


}
