package com.example.ecommerceapi.user.service;

import com.example.ecommerceapi.exception.domain.NotFoundException;
import com.example.ecommerceapi.user.domain.User;
import com.example.ecommerceapi.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean validateUser(User user){

        if (user == null) {
            return false;
        }

        if (user.getEmail().isEmpty()){
            return false;
        }

        if (this.userRepository.existsByEmail(user.getEmail() )){
            return false;
        }

        return true;

    }

    public User update(User user){

        Optional<User> userDB = this.userRepository.findById(user.getId());

        //if (userDB.isEmpty()) throw new Exception("User not found");

        BeanUtils.copyProperties(user,userDB);

        return this.userRepository.save(userDB.get());
    }

    public List<User> findAll(){
        return this.userRepository.findAll();
    }

    public Optional<User> findById(Long id) throws NotFoundException {
        Optional<User> user = this.userRepository.findById(id);

        if(user.isEmpty()) throw new NotFoundException("Usuario con id "+id+" no existe");

        return user;
    }

    public void delete(Long id){
        this.userRepository.deleteById(id);
    }
}
