package com.example.ecommerceapi.user.repository;

import com.example.ecommerceapi.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
