package com.proyectofinal.usermicroservice.services;

import com.sendgrid.*;
import com.proyectofinal.usermicroservice.entities.User;
import com.proyectofinal.usermicroservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class UserServices {
    @Autowired UserRepository userRepository;

    public boolean sendRegistrationEmail(User user){
        Email desdeEmail = new Email("20160370@ce.pucmm.edu.do");
        String asuntoEmail = "Creacion de cuenta";
        Email paraEmail = new Email(user.getMail());
        Content cuerpoEmail = new Content("text/plain", "Su nombre de usuario es: " + user.getUsername());
        Mail email = new Mail(desdeEmail, asuntoEmail, paraEmail, cuerpoEmail);

        //Why is this null, tha heck
        File file = new File("SENDGRID_API_KEY.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println(System.getenv());
        //System.out.println("REEEEEEEEEE"+System.getenv("SENDGRID_API_KEY"));
        String value = sc.nextLine();
        System.out.println(value);
        SendGrid sg = new SendGrid(value);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(email.build());
            Response response = sg.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;

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
            return true;
        }
        return false;
    }
}
