package com.david.ecommerceapi.user.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    void deleteById(Long id);

}
