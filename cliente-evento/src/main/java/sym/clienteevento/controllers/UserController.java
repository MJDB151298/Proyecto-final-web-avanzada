package sym.clienteevento.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import sym.clienteevento.entities.Role;
import sym.clienteevento.entities.User;

import java.util.Set;

@Controller
@RequestMapping("/users")
public class UserController {

    @RequestMapping("")
    public String users(Model model, @CookieValue(value = "username", defaultValue = "nobody") String username) {
        final String uri = "http://localhost:8080/user-microservice/getUsers";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(uri, User[].class);
        User[] users = responseEntity.getBody();
        model.addAttribute("listaUsuarios", users);

        if(username.equalsIgnoreCase("nobody")){
            return "redirect:/home";
        }

        final String uri_user = "http://localhost:8080/user-microservice/findUser?username=" + username;
        ResponseEntity<User> responseEntityUser = restTemplate.getForEntity(uri_user, User.class);
        User user = responseEntityUser.getBody();
        model.addAttribute("activeUser", user);
        return "users";
    }

    @RequestMapping("/create")
    public String create(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("mail") String mail, @RequestParam("role") String role){


        //Creating user
        final String uri = "http://localhost:8080/user-microservice/createUser";
        User user = new User(name, password, mail, role);
        RestTemplate restTemplate = new RestTemplate();
        User result = restTemplate.postForObject(uri, user, User.class);
        System.out.println(result);


        return "redirect:/users";
    }

}
