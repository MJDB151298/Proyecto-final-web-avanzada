package com.proyectofinal.usermicroservice.controller;

import javax.servlet.http.HttpServletRequest;

import com.proyectofinal.usermicroservice.entities.User;
import com.proyectofinal.usermicroservice.services.UserServices;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired UserServices userService;

    @RequestMapping("/welcome")
    public String welcoming(HttpServletRequest request){
        return "Soy el micro servicio de estudiante desde el puerto: " + request.getLocalPort();
    }

    @RequestMapping("/createUser")
    public String createUser(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("mail") String mail){
        User user = new User(name, DigestUtils.md5Hex(password), mail);
        userService.createUser(user);
        userService.sendRegistrationEmail(user);
        return "";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("username") String username){
        userService.deleteUser(username);
        return "";
    }

    @RequestMapping("/updateUser")
    public String editUser(@RequestParam("username") String username){
        userService.editUser(userService.findUser(username));
        return "";
    }


}