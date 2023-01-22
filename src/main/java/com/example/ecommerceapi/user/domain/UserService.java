package com.example.ecommerceapi.user.domain;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User save(User user) {

        if (!validateUser(user)) {
            return null;
        }

        User userDB = new User();

        //PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String passwordEncrypted = this.passwordEncoder.encode(user.getPassword());

        userDB.setPassword(passwordEncrypted);
        userDB.setName(user.getName());
        userDB.setEmail(user.getEmail());
        //userDB.setRole()

        this.userRepository.save(userDB);

        //Enviar notificacion por email

        return userDB;
    }

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

    public Optional<User> findById(Long id){
        return this.userRepository.findById(id);
    }

    public void delete(Long id){
        this.userRepository.deleteById(id);
    }
}
