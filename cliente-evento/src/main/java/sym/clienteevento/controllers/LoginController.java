package sym.clienteevento.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("")
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletResponse response, @CookieValue(value = "username", defaultValue = "nobody") String username){
        if(!username.equalsIgnoreCase("nobody")){
            Cookie cookie = new Cookie("username", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return "redirect:/login";
    }

    @RequestMapping("/validateUser")
    public String validateUser(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response){
        RestTemplate restTemplate = new RestTemplate();
        final String uri ="http://localhost:8080/user-microservice/validateUser?username=" + username + "&password=" + password;
        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(uri, null, Boolean.class);
        boolean result = responseEntity.getBody();
        if(result){
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(7 * 24 * 60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:/home";
        }
        else{
            return "redirect:/login";
        }

    }
}
