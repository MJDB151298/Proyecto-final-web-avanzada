package com.proyectofinal.usermicroservice.controller;

import javax.servlet.http.HttpServletRequest;

import com.proyectofinal.usermicroservice.entities.User;
import com.proyectofinal.usermicroservice.services.UserServices;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired UserServices userService;

    @RequestMapping("/welcome")
    public String welcoming(HttpServletRequest request){
        return "Soy el micro servicio de estudiante desde el puerto: " + request.getLocalPort();
    }

    @RequestMapping("/getUsers")
    public @ResponseBody List<User> getUsers(){
        return userService.findAllUsers();
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user){
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
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