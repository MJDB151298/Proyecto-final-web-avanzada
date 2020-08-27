package com.proyectofinal.usermicroservice.controller;

import javax.servlet.http.HttpServletRequest;

import com.proyectofinal.usermicroservice.entities.User;
import com.proyectofinal.usermicroservice.services.UserServices;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @RequestMapping("/findUser")
    public @ResponseBody User findUser(@RequestParam("username") String username){
        return userService.findUser(username);
    }

    @RequestMapping("/validateUser")
    public @ResponseBody boolean validateUser(@RequestParam("username") String username, @RequestParam("password") String password){
        return userService.validateUser(username, password);
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user){
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        userService.createUser(user);

        //Sending creation email
        RestTemplate restTemplate = new RestTemplate();
        final String email_uri ="http://localhost:8080/notifications-microservice/factura/sendAccountNotification?username=" + user.getUsername() + "&username_email=" + user.getMail();
        restTemplate.getForEntity(email_uri, null, (Object) null);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@RequestParam("username") String username){
        userService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.GONE);
    }

    @RequestMapping("/updateUser")
    public String editUser(@RequestParam("username") String username){
        userService.editUser(userService.findUser(username));
        return "";
    }


}