package sym.clienteevento.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String users(Model model) {
        final String uri = "http://localhost:8080/user-microservice/getUsers";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(uri, User[].class);
        User[] users = responseEntity.getBody();
        model.addAttribute("listaUsuarios", users);
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

        //Sending creation email
        final String email_uri ="http://localhost:8080/notifications-microservice/factura/sendAccountNotification?username=" + name + "&username_email=" + mail;
        restTemplate.getForEntity(email_uri, null, (Object) null);

        return "redirect:/users";
    }

}
