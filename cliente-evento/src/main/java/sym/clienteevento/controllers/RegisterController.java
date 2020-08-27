package sym.clienteevento.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import sym.clienteevento.entities.User;

@RequestMapping("/register")
@Controller
public class RegisterController {
    @RequestMapping("")
    public String register(){
        return "register";
    }

    @RequestMapping("/createUser")
    public String createUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("mail") String mail){
        User user = new User(username, password, mail, "CLIENT");
        final String uri = "http://localhost:8080/user-microservice/createUser";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, user, User.class);
        return "redirect:/login";
    }
}
