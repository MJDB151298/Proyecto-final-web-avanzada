package sym.clienteevento.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("")
    public String login(){
        return "login";
    }

    @RequestMapping("/validateUser")
    public String validateUser(@RequestParam("username") String username, @RequestParam("password") String password){
        RestTemplate restTemplate = new RestTemplate();
        final String uri ="http://localhost:8080/user-microservice/validateUser?username=" + username + "&password=" + password;
        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(uri, null, Boolean.class);
        boolean result = responseEntity.getBody();
        if(result){
            return "redirect:/home";
        }
        else{
            return "redirect:/login";
        }

    }
}
