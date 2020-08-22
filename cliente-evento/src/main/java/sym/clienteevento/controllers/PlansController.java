package sym.clienteevento.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plans")
public class PlansController {

    @RequestMapping("")
    public String plans()
    {
        return "formPlanes";
    }

    @RequestMapping("/paypal")
    public String paypal() {
        //TODO: llamar el microservicio de eventos

        return "plans";
    }
}