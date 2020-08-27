package sym.clienteevento.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import sym.clienteevento.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("")
    public String home(Model model, @CookieValue(value = "username", defaultValue = "nobody") String username) {
        System.out.println(username);
        if(username.equalsIgnoreCase("nobody")){
            return "redirect:/login";
        }
        RestTemplate restTemplate = new RestTemplate();
        final String uri_user = "http://localhost:8080/user-microservice/findUser?username=" + username;
        ResponseEntity<User> responseEntityUser = restTemplate.getForEntity(uri_user, User.class);
        User user = responseEntityUser.getBody();
        model.addAttribute("activeUser", user);
        return "/home";
    }

}
