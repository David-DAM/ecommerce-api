package com.david.ecommerceapi.auth.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  @NotNull(message = "The firstname is mandatory")
  @NotBlank(message = "The firstname is mandatory")
  private String firstname;
  @NotNull(message = "The lastname is mandatory")
  @NotBlank(message = "The lastname is mandatory")
  private String lastname;
  @NotNull(message = "The email is mandatory")
  @NotBlank(message = "The email is mandatory")
  @Email(message = "The given email does not match the pattern")
  @UniqueElements()
  private String email;
  @NotNull(message = "The password is mandatory")
  @NotBlank(message = "The password is mandatory")
  @Length(min = 5, message = "The password should be at least of 5 characters of length")
  private String password;
}
