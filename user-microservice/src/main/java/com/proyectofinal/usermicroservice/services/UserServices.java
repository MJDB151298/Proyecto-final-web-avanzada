package com.proyectofinal.usermicroservice.services;

import com.proyectofinal.usermicroservice.entities.User;
import com.proyectofinal.usermicroservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class UserServices {
    @Autowired UserRepository userRepository;

    @Transactional
    public boolean createUser(User user){
        if(userRepository.findByUsername(user.getUsername()) != null){
            return false;
        }
        userRepository.save(user);
        return true;
    }

    @Transactional
    public boolean deleteUser(String username){
        User user = userRepository.findByUsername(username);
        if(user != null){
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean editUser(User user){
        Optional<User> e = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));
        if(e.isPresent()){
            User newUser = e.get();
            newUser.setUsername(user.getUsername());
            newUser.setMail(user.getMail());
            newUser.setPassword(user.getPassword());
            return true;
        }
        return false;
    }
}
