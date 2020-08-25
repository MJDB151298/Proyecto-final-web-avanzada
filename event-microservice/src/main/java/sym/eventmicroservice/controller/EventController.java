package sym.eventmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sym.eventmicroservice.repository.EventRepository;
import sym.eventmicroservice.services.EventServices;

import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("/")
public class EventController {

    @Autowired
    EventServices eventServices;

    @RequestMapping("/createDefaultEvents")
    public String createDefaultEvents(){
        eventServices.generarPlanes();

        return "/home";
    }

    @RequestMapping("/procesarCompraPaypal")
    public String procesarCompra(Model model, @RequestParam Map<String,String> params)
    {
        eventServices.generarCompra(params.get("invoice"), params.get("txn_id"), params.get("item_name"), params.get("payment_status"), new BigDecimal(params.get("payment_gross")),
                new BigDecimal(params.get("handling_amount")), new BigDecimal(params.get("payment_fee")), new BigDecimal(params.get("shipping")), params.get("payer_email"), params.get("business"),
                params.get("address_city"), params.get("address_zip"), params.get("address_state"), params.get("address_name"), params.get("user"));

        return "/home";
    }

}
