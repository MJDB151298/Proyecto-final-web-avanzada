package com.proyectofinal.usermicroservice.repository;

import com.proyectofinal.usermicroservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}