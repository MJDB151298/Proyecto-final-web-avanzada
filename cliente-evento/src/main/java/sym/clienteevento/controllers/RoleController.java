package sym.clienteevento.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @RequestMapping("")
    public String roles()
    {
        return "roles";
    }
}
