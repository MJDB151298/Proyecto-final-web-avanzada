package com.proyectofinal.usermicroservice.services;

import com.sendgrid.*;
import com.proyectofinal.usermicroservice.entities.User;
import com.proyectofinal.usermicroservice.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class UserServices {
    @Autowired UserRepository userRepository;

    public User findUser(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> findAllUsers() { return userRepository.findAll(); }

    public void createAdmin() {
        String username = "admin";
        String password = DigestUtils.md5Hex("admin");
        String email = "20160370@ce.pucmm.edu.do";
        String role = "ADMIN";
        if(userRepository.count() == 0){
            createUser(new User(username, password, email, role));
        }
    }

    public boolean validateUser(String username, String password){
        User user = findUser(username);
        if(user != null){
            if(user.getPassword().equals(DigestUtils.md5Hex(password))){
                return true;
            }
        }
        return false;
    }

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
            newUser.setRole(user.getRole());
            return true;
        }
        return false;
    }
}
