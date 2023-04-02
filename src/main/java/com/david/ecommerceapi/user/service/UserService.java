package com.david.ecommerceapi.user.service;

import com.david.ecommerceapi.user.domain.User;
import com.david.ecommerceapi.user.domain.UserDTO;
import com.david.ecommerceapi.user.domain.UserDTOMapper;
import com.david.ecommerceapi.user.repository.UserRepository;
import com.david.ecommerceapi.exception.domain.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;
    //TODO Refactor with validations in model
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

    public UserDTO update(User user){

        Optional<User> userDB = this.userRepository.findById(user.getId());

        //if (userDB.isEmpty()) throw new Exception("User not found");

        BeanUtils.copyProperties(user,userDB.get(),"role","password");

        this.userRepository.save(userDB.get());

        return userDTOMapper.apply(userDB.get());
    }

    public List<UserDTO> findAll(){
        return this.userRepository.findAll().stream().map(userDTOMapper).toList();
    }

    public Optional<UserDTO> findById(Long id) throws NotFoundException {

        Optional<User> user = this.userRepository.findById(id);

        if(user.isEmpty()) throw new NotFoundException("Usuario con id "+id+" no existe");

        return Optional.ofNullable(userDTOMapper.apply(user.get()));
    }

    public void delete(Long id){
        this.userRepository.deleteById(id);
    }
}
