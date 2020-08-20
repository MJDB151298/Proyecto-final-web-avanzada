package com.proyectofinal.usermicroservice.entities;

import org.hibernate.annotations.Generated;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String username;
    private String password;
    private String mail;

    public User(){}

    public User(String username, String password, String mail){
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
